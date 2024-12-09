package com.example.TugasBesar.Setlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/setlists")
public class SetlistController {
    @Autowired
    private SetlistService setlistService;

    @GetMapping
    public ResponseEntity<List<Setlist>> getAllSetlists() {
        return ResponseEntity.ok(setlistService.getAllSetlists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Setlist> getSetlistById(@PathVariable int id) {
        return ResponseEntity.ok(setlistService.getSetlistById(id));
    }

    @PostMapping
    public ResponseEntity<String> createSetlist(@RequestBody Setlist setlist) {
        int result = setlistService.createSetlist(setlist);
        return result > 0 ? ResponseEntity.ok("Setlist created successfully") : ResponseEntity.badRequest().body("Failed to create setlist");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSetlist(@PathVariable int id) {
        int result = setlistService.deleteSetlist(id);
        return result > 0 ? ResponseEntity.ok("Setlist deleted successfully") : ResponseEntity.badRequest().body("Failed to delete setlist");
    }
}
