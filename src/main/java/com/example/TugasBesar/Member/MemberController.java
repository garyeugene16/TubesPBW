package com.example.TugasBesar.Member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TugasBesar.Setlist.Setlist;
import com.example.TugasBesar.Show.Show;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/home-member")
    public String home() {
        return "member/home-member";
    }

    @GetMapping("/")
    public String index() {
        return "member/home-member";
    }

    // Endpoint pencarian berdasarkan nama artis
    @GetMapping("/search")
    public String searchByArtist(Model model) {
        return "member/search"; // Template untuk hasil pencarian
    }

    @PostMapping("/search")
    public String searchByKeyword(@RequestParam("keyword") String keyword, Model model) {
        List<Show> shows = memberService.searchShowsByArtist(keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("shows", shows);
        return "member/search"; // Template untuk hasil pencarian
    }
     
    @GetMapping("/show")
    public String getShowDetails(@RequestParam("id") int show_id, Model model) {
        Show show = memberService.getShowDetails(show_id); // Memanggil layanan untuk mendapatkan show
        List<Setlist> setlist = memberService.getSetlistByShowId(show_id); // Mendapatkan setlist show
        model.addAttribute("show", show);
        model.addAttribute("setlist", setlist);
        return "member/show-detail"; // Template untuk detail show
    }

    @GetMapping("/add-data")
    public String addData(Model model) {
        return "member/add-data"; // Template untuk hasil pencarian
    }

}
