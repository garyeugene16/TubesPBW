package com.example.TugasBesar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TugasBesar.User.User;
import com.example.TugasBesar.User.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginView(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/member/home-member";
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpSession session,
            Model model) {
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("user", user); // Menyimpan user di session jika login berhasil
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());
            logger.info("User logged in: " + username + " with role: " + user.getRole());

            // Cek Role User yang login
            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                logger.info("Redirecting admin user to home-admin");
                return "redirect:/admin/home-admin";
            } else if ("MEMBER".equalsIgnoreCase(user.getRole())) {
                return "redirect:/member/home-member";
            } else {
                model.addAttribute("status", "failed"); // Role tidak dikenali
                return "login";
            }
        } else {
            model.addAttribute("status", "failed"); // Menambahkan status gagal jika login gagal
            logger.warn("Login failed for user: " + username);
            return "login"; // Kembali ke halaman login
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Menghapus session
        return "redirect:/home"; // Redirect ke halaman login setelah logout
    }

}
