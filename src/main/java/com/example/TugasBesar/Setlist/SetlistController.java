package com.example.TugasBesar.Setlist;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/setlist")
public class SetlistController {

    @Autowired
    private SetlistService setlistService;

    @PostMapping("/add")
    public String addSong(@RequestParam("showId") int showId,
                          @RequestParam("songTitle") String songTitle,
                          @RequestParam("songOrder") int songOrder,
                          @RequestParam("youtubeUrl") String youtubeUrl,
                          HttpSession session) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (role == null) {
            return "redirect:/login"; // Redirect ke login jika role null
        }
        setlistService.addSong(showId, songTitle, songOrder, youtubeUrl, username, role);
        return "redirect:/member/show?id=" + showId;
    }

    @PostMapping("/delete")
    public String deleteSong(@RequestParam("setlistId") int setlistId,
                             @RequestParam("showId") int showId,
                             HttpSession session) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (role == null) {
            return "redirect:/login"; // Redirect ke login jika role null
        }
        setlistService.deleteSong(setlistId, username, role);
        return "redirect:/member/show?id=" + showId;
    }

    @PostMapping("/update-all-orders")
    public String updateAllSongOrders(@RequestParam("setlistIds") List<Integer> setlistIds,
                                    @RequestParam("songOrders") List<Integer> songOrders,
                                    @RequestParam("showId") int showId,
                                    HttpSession session) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        if (role == null) {
            return "redirect:/login"; // Redirect ke login jika role null
        }
        setlistService.updateAllSongOrders(setlistIds, songOrders, username, role);
        return "redirect:/member/show?id=" + showId;
    }
}