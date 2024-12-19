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

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    // @GetMapping("/login")
    // public String loginView(HttpSession session){
    //     if (session.getAttribute("user") != null) {
    //         return "redirect:/dashboard";
    //     }
    //     return "login";
    // }

    @GetMapping("/login")
    public String loginView(HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user != null && "member".equals(user.getRole())) {
            return "redirect:/member/dashboard"; // Jika sudah login sebagai member, otomatis redirect ke dashboard member apabila menekan tombol 'Login'
        }

        return "login"; // Tampilkan halaman login untuk user yang belum login
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("user", user); // Menyimpan user di session jika login berhasil
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());
            return "redirect:/dashboard";
        } else {
            model.addAttribute("status", "failed"); // Menambahkan status gagal jika login gagal
            return "login"; // Kembali ke halaman login
        }
    }

    // @GetMapping("/dashboard")
    // @RequiredRole("*")
    // public String index (HttpSession session, Model model){
    //     User user = (User) session.getAttribute("user");
    //     model.addAttribute("user", user);
    //     return "home";
    // }
    
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null || "guest".equals(user.getRole())) {
            return "home"; // Redirect ke halaman guest untuk user tanpa login
        }

        // Redirect berdasarkan role
        if ("member".equals(user.getRole())) {
            return "redirect:/member/dashboard"; // Redirect ke dashboard member
        }

        // Role lain, arahkan ke halaman default
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Menghapus session
        return "redirect:/login"; // Redirect ke halaman login setelah logout
    }

}
