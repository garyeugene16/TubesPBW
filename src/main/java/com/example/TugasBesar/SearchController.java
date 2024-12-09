package com.example.TugasBesar;

import com.example.TugasBesar.Artist.Artist;
import com.example.TugasBesar.Artist.ArtistService;
import com.example.TugasBesar.Show.ShowWithArtist;
import com.example.TugasBesar.Show.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchController {
    @Autowired
    private ArtistService artistService;
    
    @Autowired
    private ShowService showService;

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String query, Model model) {
        if (query != null && !query.trim().isEmpty()) {
            String searchQuery = query.toLowerCase().trim();
            
            // Cari artist
            List<Artist> allArtists = artistService.getAllArtists();
            List<Artist> searchArtists = allArtists.stream()
                .filter(artist -> artist.getName().toLowerCase().contains(searchQuery))
                .collect(Collectors.toList());
            
            // Cari show (berdasarkan venue atau nama artist)
            List<ShowWithArtist> allShows = showService.getAllShowsWithArtist();
            List<ShowWithArtist> searchShows = allShows.stream()
                .filter(show -> 
                    show.getVenue().toLowerCase().contains(searchQuery) ||
                    show.getArtist_name().toLowerCase().contains(searchQuery))
                .collect(Collectors.toList());

            model.addAttribute("artists", searchArtists);
            model.addAttribute("shows", searchShows);
            model.addAttribute("query", query);
        }
        return "search";
    }
}
