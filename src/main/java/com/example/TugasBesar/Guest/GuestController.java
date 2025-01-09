package com.example.TugasBesar.Guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TugasBesar.Setlist.Setlist;
import com.example.TugasBesar.Show.Show;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GuestController {

    @Autowired
    private GuestService guestService;

    @GetMapping("/home")
    public String home() {
        return "guest/home"; // Menampilkan halaman home untuk guest
    }

     // Endpoint pencarian berdasarkan nama artis
    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                         @RequestParam(value = "page", defaultValue = "0") int page,
            Model model) {
        int pageSize = 8; // Jumlah item per halaman

        // Jika keyword kosong, jangan ambil data shows
        List<Show> shows = new ArrayList<>();
        int totalPages = 0;

        if (!keyword.isEmpty()) {
            shows = guestService.searchShowsByKeywordWithPagination(keyword, page, pageSize);
            totalPages = guestService.getTotalPages(keyword, pageSize);
        }

        model.addAttribute("keyword", keyword);
        model.addAttribute("shows", shows);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "guest/search";
    }

    @PostMapping("/search")
    public String searchByKeyword(
            @RequestParam("keyword") String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            Model model) {

        if (page < 0) {
            page = 0; // Reset ke halaman pertama jika page negatif
        }

        int pageSize = 8; // Jumlah item per halaman
        List<Show> shows = guestService.searchShowsByKeywordWithPagination(keyword, page, pageSize);
        int totalPages = guestService.getTotalPages(keyword, pageSize);

        model.addAttribute("keyword", keyword);
        model.addAttribute("shows", shows);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "redirect:/search";
    }
     
    @GetMapping("/show")
    public String getShowDetails(@RequestParam("id") int show_id, Model model) {
        Show show = guestService.getShowDetails(show_id); // Memanggil layanan untuk mendapatkan show
        List<Setlist> setlist = guestService.getSetlistByShowId(show_id); // Mendapatkan setlist show
        model.addAttribute("show", show);
        model.addAttribute("setlist", setlist);
        return "guest/show-detail"; // Template untuk detail show
    }


}
