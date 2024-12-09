package com.example.TugasBesar.Show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {
    @Autowired
    private ShowService showService;

    @GetMapping
    public ResponseEntity<List<Show>> getAllShows() {
        return ResponseEntity.ok(showService.getAllShows());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Show> getShowById(@PathVariable int id) {
        return ResponseEntity.ok(showService.getShowById(id));
    }

    @PostMapping
    public ResponseEntity<String> createShow(@RequestBody Show show) {
        int result = showService.createShow(show);
        return result > 0 ? ResponseEntity.ok("Show created successfully") : ResponseEntity.badRequest().body("Failed to create show");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShow(@PathVariable int id) {
        int result = showService.deleteShow(id);
        return result > 0 ? ResponseEntity.ok("Show deleted successfully") : ResponseEntity.badRequest().body("Failed to delete show");
    }
}
