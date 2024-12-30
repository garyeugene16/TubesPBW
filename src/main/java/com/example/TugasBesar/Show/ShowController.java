package com.example.TugasBesar.Show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
// import java.util.List;

// @RestController
// @RequestMapping("/api/shows")
@Controller
public class ShowController {
    @Autowired
    private ShowService showService;

    // @GetMapping
    // public ResponseEntity<List<Show>> getAllShows() {
    //     return ResponseEntity.ok(showService.getAllShows());
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<Show> getShowById(@PathVariable int id) {
    //     return ResponseEntity.ok(showService.getShowById(id));
    // }

    // @PostMapping
    // public ResponseEntity<String> createShow(@RequestBody Show show) {
    //     int result = showService.createShow(show);
    //     return result > 0 ? ResponseEntity.ok("Show created successfully") : ResponseEntity.badRequest().body("Failed to create show");
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<String> deleteShow(@PathVariable int id) {
    //     int result = showService.deleteShow(id);
    //     return result > 0 ? ResponseEntity.ok("Show deleted successfully") : ResponseEntity.badRequest().body("Failed to delete show");
    // }

    // @PostMapping("/add-show")
    // public String addShow(@RequestParam int artistId, @RequestParam String venue, @RequestParam String date, HttpSession session, Model model
    // ) {
    //     String username = (String) session.getAttribute("username");
    //     if (username == null) {
    //         model.addAttribute("error", "You must be logged in to add a show.");
    //         return "redirect:/login";
    //     }

    //     boolean isSaved = showService.createShow(artistId, venue, date, username);

    //     if (isSaved) {
    //         model.addAttribute("success", "Show added successfully!");
    //     } else {
    //         model.addAttribute("error", "Failed to add show.");
    //     }

    //     return "member/add-show";
    // }

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
