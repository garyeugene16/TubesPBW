package com.example.TugasBesar.Member;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TugasBesar.Artist.Artist;
import com.example.TugasBesar.Artist.ArtistService;
import com.example.TugasBesar.Setlist.Setlist;
import com.example.TugasBesar.Show.Show;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private ArtistService artistService;

    @GetMapping("/home-member")
    public String home() {
        return "member/home-member";
    }

    // @GetMapping("/")
    // public String index() {
    //     return "member/home-member";
    // }

    // Endpoint pencarian berdasarkan nama artis
    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                         @RequestParam(value = "page", defaultValue = "0") int page,
                         Model model) {
        int pageSize = 9; // Jumlah item per halaman

        // Jika keyword kosong, jangan ambil data shows
        List<Show> shows = new ArrayList<>();
        int totalPages = 0;

        if (!keyword.isEmpty()) {
            shows = memberService.searchShowsByKeywordWithPagination(keyword, page, pageSize);
            totalPages = memberService.getTotalPages(keyword, pageSize);
        }

        model.addAttribute("keyword", keyword);
        model.addAttribute("shows", shows);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "member/search-member"; // Mengarah ke template untuk member
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
        List<Show> shows = memberService.searchShowsByKeywordWithPagination(keyword, page, pageSize);
        int totalPages = memberService.getTotalPages(keyword, pageSize);

        model.addAttribute("keyword", keyword);
        model.addAttribute("shows", shows);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "redirect:/search-member";
    }
     
    @GetMapping("/show")
    public String getShowDetails(@RequestParam("id") int show_id, Model model) {
        Show show = memberService.getShowDetails(show_id); // Memanggil layanan untuk mendapatkan show
        List<Setlist> setlist = memberService.getSetlistByShowId(show_id); // Mendapatkan setlist show
        model.addAttribute("show", show);
        model.addAttribute("setlist", setlist);
        return "member/show-detail-member"; // Template untuk detail show
    }

    // @GetMapping("/add-data")
    // public String addData(Model model) {
    //     return "member/add-data"; // Template untuk hasil pencarian
    // }

     @GetMapping("/add-data")
    public String addData(Model model) {
        List<Artist> artists = artistService.getAllArtists(); // Ambil daftar artis dari service
        model.addAttribute("artists", artists); // Tambahkan ke model
        return "member/add-data"; // Template untuk hasil pencarian
    }

    @GetMapping("/setlist/edit")
    public String editSetlist(@RequestParam("id") int show_id, Model model) {
        Show show = memberService.getShowDetails(show_id);
        List<Setlist> setlist = memberService.getSetlistByShowId(show_id);
        model.addAttribute("show", show);
        model.addAttribute("setlist", setlist);
        return "member/edit-setlist";
    }
}
