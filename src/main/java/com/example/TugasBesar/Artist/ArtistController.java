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

    // @GetMapping
    // public ResponseEntity<List<Artist>> getAllArtists() {
    //     return ResponseEntity.ok(artistService.getAllArtists());
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<Artist> getArtistById(@PathVariable int id) {
    //     return ResponseEntity.ok(artistService.getArtistById(id));
    // }

    // @PostMapping
    // public ResponseEntity<String> createArtist(@RequestBody Artist artist) {
    //     int result = artistService.createArtist(artist);
    //     return result > 0 ? ResponseEntity.ok("Artist created successfully") : ResponseEntity.badRequest().body("Failed to create artist");
    // }

    // // @PutMapping("/{id}")
    // // public ResponseEntity<String> updateArtist(@PathVariable int id, @RequestBody Artist artist) {
    // //     artist.setArtist_id(id);
    // //     int result = artistService.updateArtist(artist);
    // //     return result > 0 ? ResponseEntity.ok("Artist updated successfully") : ResponseEntity.badRequest().body("Failed to update artist");
    // // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<String> deleteArtist(@PathVariable int id) {
    //     int result = artistService.deleteArtist(id);
    //     return result > 0 ? ResponseEntity.ok("Artist deleted successfully") : ResponseEntity.badRequest().body("Failed to delete artist");
    // }
}
