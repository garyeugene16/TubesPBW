package com.example.TugasBesar.Show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;

@Controller
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping("/add-show")
    public String addShow(@RequestParam int artistName, @RequestParam String venue, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, HttpSession session, Model model) {
        String username = (String) session.getAttribute("username"); // Ambil username dari session

        if (username == null) {
            model.addAttribute("error", "You must be logged in to add a show.");
            return "redirect:/login"; // Redirect ke login jika user belum login
        }

        boolean isSaved = showService.createShow(artistName, venue, date, username); // Panggil service

        if (isSaved) {
            model.addAttribute("success", "Show added successfully!");
        } else {
            model.addAttribute("error", "Failed to add show. Artist not found.");
        }
        return "member/add-data";
    }

}
