package main.java.com.example.TugasBesar.Admin;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import com.example.TugasBesar.Admin.AdminService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @Autowired
    private ArtistService artistService;

    @GetMapping("/home-admin")
    public String homeAdmin(HttpSession session, Model model) {
        logger.info("Accessing home-admin page"); // Log untuk memastikan metode dipanggil
        String role = (String) session.getAttribute("role");
        if (role == null || !"ADMIN".equalsIgnoreCase(role)) {
            logger.warn("User is not an admin, redirecting to login");
            return "redirect:/login"; // Redirect ke login jika bukan admin
        }
        model.addAttribute("username", session.getAttribute("username"));
        return "admin/home-admin"; // Arahkan ke view 'admin/home-admin.html'
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
            shows = adminService.searchShowsByKeywordWithPagination(keyword, page, pageSize);
            totalPages = adminService.getTotalPages(keyword, pageSize);
        }

        model.addAttribute("keyword", keyword);
        model.addAttribute("shows", shows);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "admin/search-admin"; // Mengarah ke template untuk admin
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
        List<Show> shows = adminService.searchShowsByKeywordWithPagination(keyword, page, pageSize);
        int totalPages = adminService.getTotalPages(keyword, pageSize);

        model.addAttribute("keyword", keyword);
        model.addAttribute("shows", shows);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "redirect:/search-admin";
    }

    @GetMapping("/show")
    public String getShowDetails(@RequestParam("id") int show_id, Model model) {
        Show show = adminService.getShowDetails(show_id); // Memanggil layanan untuk mendapatkan show
        List<Setlist> setlist = adminService.getSetlistByShowId(show_id); // Mendapatkan setlist show
        model.addAttribute("show", show);
        model.addAttribute("setlist", setlist);
        return "admin/show-detail-admin"; // Template untuk detail show
    }

    // @GetMapping("/add-data")
    // public String addData(Model model) {
    // return "admin/add-data"; // Template untuk hasil pencarian
    // }

    @GetMapping("/add-data")
    public String addData(Model model) {
        List<Artist> artists = artistService.getAllArtists(); // Ambil daftar artis dari service
        model.addAttribute("artists", artists); // Tambahkan ke model
        return "admin/add-data"; // Template untuk hasil pencarian
    }

    @GetMapping("/setlist/edit")
    public String editSetlist(@RequestParam("id") int show_id, Model model) {
        Show show = adminService.getShowDetails(show_id);
        List<Setlist> setlist = adminService.getSetlistByShowId(show_id);
        model.addAttribute("show", show);
        model.addAttribute("setlist", setlist);
        return "admin/edit-setlist";
    }
}
