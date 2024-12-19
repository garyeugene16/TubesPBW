package com.example.TugasBesar.Member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.TugasBesar.RequiredRole;
import com.example.TugasBesar.User.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
    // Akses ke Dashboard Khusus Member
    @GetMapping("/member/dashboard")
    @RequiredRole("member")
    public String memberDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login"; // Redirect ke login jika belum login
        }

        model.addAttribute("user", user);
        return "member/home-member"; // Pastikan ini sesuai dengan path template
    }

    // Kemampuan untuk mengakses halaman AddData apabila role = 'member'
    @GetMapping("/addData")
    @RequiredRole("member")
    public String addData(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login"; // Redirect ke login jika belum login
        }

        model.addAttribute("user", user);
        return "member/add_data"; // Path ini benar karena file berada di src/main/resources/templates/
    }
}
