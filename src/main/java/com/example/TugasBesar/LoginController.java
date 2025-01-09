package com.example.TugasBesar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TugasBesar.User.User;
import com.example.TugasBesar.User.UserService;

import jakarta.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginView(HttpSession session){
        if (session.getAttribute("user") != null) {
            String role = (String) session.getAttribute("role");
            if ("admin".equals(role)) {
                return "redirect:/admin/home-admin";
            }
            return "redirect:/member/home-member";
        }
        return "login";
    }
    
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());
            
            // Redirect berdasarkan role
            if ("admin".equals(user.getRole())) {
                logger.info("Admin Login...");
                return "redirect:/admin/home-admin";
            } else {
                return "redirect:/member/home-member";
            }
        } else {
            model.addAttribute("status", "failed");
            return "login";
        }
    }



    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Menghapus session
        return "redirect:/home"; // Redirect ke halaman login setelah logout
    }

}
