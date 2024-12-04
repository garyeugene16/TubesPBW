package com.example.TugasBesar.Guest;

import com.example.TugasBesar.Repository.ArtistRepository;
import com.example.TugasBesar.Repository.ShowRepository;
import com.example.TugasBesar.Repository.SetlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GuestController {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private SetlistRepository setlistRepository;

    // Menampilkan halaman pencarian
    @GetMapping("/guest/search")
    public String searchPage() {
        return "guest/search";  // View untuk pencarian
    }

    // Pencarian artis
    @GetMapping("/guest/search/artists")
    public String searchArtists(@RequestParam("query") String query, Model model) {
        model.addAttribute("artists", artistRepository.findByNameContainingIgnoreCase(query));
        return "guest/search";  // Mengarah kembali ke halaman pencarian
    }

    // Pencarian show
    @GetMapping("/guest/search/shows")
    public String searchShows(@RequestParam("query") String query, Model model) {
        model.addAttribute("shows", showRepository.findByNameContainingIgnoreCase(query));
        return "guest/search";  // Mengarah kembali ke halaman pencarian
    }

    // Pencarian setlist
    @GetMapping("/guest/search/setlists")
    public String searchSetlists(@RequestParam("query") String query, Model model) {
        model.addAttribute("setlists", setlistRepository.findBySongTitleContainingIgnoreCase(query));
        return "guest/search";  // Mengarah kembali ke halaman pencarian
    }
}
