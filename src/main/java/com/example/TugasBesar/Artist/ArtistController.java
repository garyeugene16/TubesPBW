package com.example.TugasBesar.Artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @PostMapping("/add-artist")
    public String addArtist(@RequestParam String artistName, HttpSession session, Model model) {
        String username = (String) session.getAttribute("username"); // Ambil username dari session

        if (username == null) {
            model.addAttribute("error", "You must be logged in to add an artist.");
            return "redirect:/login"; // Redirect ke login jika tidak ada session
        }
    
        boolean isSaved = artistService.createArtist(artistName, username); // Panggil metode save
    
        if (isSaved) {
            model.addAttribute("success", "Artist added successfully!");
        } else {
            model.addAttribute("error", "Failed to add artist.");
        }
        return "member/add-data";
    }
}
