package com.example.TugasBesar.Guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TugasBesar.Setlist.Setlist;
import com.example.TugasBesar.Show.Show;

import java.util.List;

@Controller
public class GuestController {

    @Autowired
    private GuestService guestService;

    @GetMapping("/")
    public String index () {
        return "home";
    }

    @GetMapping("/home")
    public String home () {
        return "home";
    }

     // Endpoint pencarian berdasarkan nama artis
    @GetMapping("/search")
    public String searchByArtist(Model model) {
        return "search"; // Template untuk hasil pencarian
    }

    @PostMapping("/search")
    public String searchByKeyword(@RequestParam("keyword") String keyword, Model model) {
        List<Show> shows = guestService.searchShowsByArtist(keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("shows", shows);
        return "search"; // Template untuk hasil pencarian
    }
     
    @GetMapping("/show")
    public String getShowDetails(@RequestParam("id") int show_id, Model model) {
        Show show = guestService.getShowDetails(show_id); // Memanggil layanan untuk mendapatkan show
        List<Setlist> setlist = guestService.getSetlistByShowId(show_id); // Mendapatkan setlist show
        model.addAttribute("show", show);
        model.addAttribute("setlist", setlist);
        return "show-detail"; // Template untuk detail show
    }

}
