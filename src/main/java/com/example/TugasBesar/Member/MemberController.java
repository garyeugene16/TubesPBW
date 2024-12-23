package com.example.TugasBesar.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TugasBesar.RequiredRole;
import com.example.TugasBesar.User.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    // Akses ke Dashboard Khusus Member
    @GetMapping("/member/dashboard")
    @RequiredRole("member")
    public String memberDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login"; // Redirect ke login jika belum login
        }

        model.addAttribute("user", user);
        return "member/home-member"; // Pastikan ini sesuai dengan path template
    }

    @GetMapping("/member/search")
    @RequiredRole("member")
    public String memberSearch(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login"; // Redirect ke login jika belum login
        }

        model.addAttribute("user", user);
        return "member/search-member"; // Path ini benar karena file berada di src/main/resources/templates/
    }

    // Kemampuan untuk mengakses halaman AddData apabila role = 'member'
    @GetMapping("/addData")
    @RequiredRole("member")
    public String addData(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login"; // Redirect ke login jika belum login
        }

        model.addAttribute("user", user);
        return "member/add_data"; // Path ini benar karena file berada di src/main/resources/templates/
    }

    // Add Artist (GET)
    @GetMapping("/addArtist")
    @RequiredRole("member")
    public String addArtist(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login"; // Redirect ke login jika belum login
        }

        model.addAttribute("user", user);
        return "member/add_artist"; // Path ini benar karena file berada di src/main/resources/templates/
    }

    // Method to handle artist addition
    @PostMapping("/addArtist")
    @RequiredRole("member")
    public String addArtistSubmit(@RequestParam("name") String name, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login"; // Redirect to login if not logged in
        }

        String createdBy = user.getUsername();
        // Call the service to add an artist
        memberService.addArtist(name, createdBy);

        model.addAttribute("user", user);
        return "redirect:/member/dashboard"; // Redirect back to dashboard or another page
    }

    // Method to handle show addition
    @PostMapping("/addShow")
    @RequiredRole("member")
    public String addShowSubmit(@RequestParam("artistId") int artistId,
                                 @RequestParam("venue") String venue,
                                 @RequestParam("date") String date,
                                 HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login"; // Redirect to login if not logged in
        }

        String createdBy = user.getUsername();
        // Call the service to add a show
        memberService.addShow(artistId, venue, date, createdBy);

        model.addAttribute("user", user);
        return "redirect:/member/dashboard"; // Redirect back to dashboard or another page
    }

    // Method to handle setlist addition
    @PostMapping("/addSetlist")
    @RequiredRole("member")
    public String addSetlistSubmit(@RequestParam("showId") int showId,
                                   @RequestParam("songOrder") int songOrder,
                                   @RequestParam("songTitle") String songTitle,
                                   HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login"; // Redirect to login if not logged in
        }

        String createdBy = user.getUsername();
        // Call the service to add a setlist
        memberService.addSetlist(showId, songOrder, songTitle, createdBy);

        model.addAttribute("user", user);
        return "redirect:/member/dashboard"; // Redirect back to dashboard or another page
    }
}
