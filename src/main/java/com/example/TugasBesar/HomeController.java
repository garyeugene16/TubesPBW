package com.example.TugasBesar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.TugasBesar.User.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    
    // @GetMapping("/")
    // public String index() {
    //     return "home";
    // }

    @GetMapping("/")
    public String homeRedirect(HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user != null && "member".equals(user.getRole())) {
            return "redirect:/member/dashboard"; // Redirect ke dashboard member jika user adalah member
        }

        return "home"; // Halaman utama untuk guest
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}