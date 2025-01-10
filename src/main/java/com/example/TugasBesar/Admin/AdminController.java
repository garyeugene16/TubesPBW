package com.example.TugasBesar.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.TugasBesar.User.User;
import com.example.TugasBesar.Artist.Artist;
import com.example.TugasBesar.Member.MemberService;
import com.example.TugasBesar.Setlist.Setlist;
import com.example.TugasBesar.Show.Show;
import com.example.TugasBesar.Show.ShowService;
import com.example.TugasBesar.Artist.ArtistService;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private ShowService showService;

    @GetMapping("/home-admin")
    public String home() {
        logger.info("Showing Admin Pages!");
        return "admin/home-admin";
    }

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    // Menampilkan daftar user
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = adminService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user-list";
    }

    // Form edit user
    @GetMapping("/users/edit/{username}")
    public String editUserForm(@PathVariable String username, Model model) {
        User user = adminService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "admin/edit-user";
    }

    // Proses update user
    @PostMapping("/users/update")
    public String updateUser(@ModelAttribute User user) {
        adminService.updateUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/search-admin")
    public String showSearch(
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            Model model) {

        int pageSize = 9;
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

        return "admin/search-admin";
    }

    @PostMapping("/search-admin")
    public String searchByKeyword(
            @RequestParam("keyword") String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            Model model) {

        return "redirect:/admin/search-admin?keyword=" + keyword + "&page=" + page;
    }

    @GetMapping("/show")
    public String getShowDetails(@RequestParam("id") int show_id, Model model) {
        Show show = memberService.getShowDetails(show_id); // Memanggil layanan untuk mendapatkan show
        List<Setlist> setlist = memberService.getSetlistByShowId(show_id); // Mendapatkan setlist show
        model.addAttribute("show", show);
        model.addAttribute("setlist", setlist);
        return "admin/show-detail-member"; // Template untuk detail show
    }

    @GetMapping("/add-data")
    public String addData(Model model) {
        List<Artist> artists = artistService.getAllArtists(); // Ambil daftar artis dari service
        model.addAttribute("artists", artists); // Tambahkan ke model
        return "admin/add-data"; // Template untuk hasil pencarian
    }

    @GetMapping("/setlist/edit")
    public String editSetlist(@RequestParam("id") int show_id, Model model) {
        Show show = memberService.getShowDetails(show_id);
        List<Setlist> setlist = memberService.getSetlistByShowId(show_id);
        model.addAttribute("show", show);
        model.addAttribute("setlist", setlist);
        return "admin/edit-setlist";
    }

    @GetMapping("/reports")
    public String showReportsPage() {
        return "admin/reports";
    }

    @GetMapping("/reports/generate")
    public ResponseEntity<byte[]> generateReport(
            @RequestParam String reportType,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        try {
            byte[] pdfContent;
            String filename;

            if ("shows".equals(reportType)) {
                logger.info("Generating show report with dates: {} to {}", startDate, endDate);

                if (startDate == null || endDate == null) {
                    throw new IllegalArgumentException("Tanggal awal dan akhir harus diisi");
                }

                pdfContent = adminService.generateShowReport(startDate, endDate);
                if (pdfContent == null || pdfContent.length == 0) {
                    throw new RuntimeException("Konten PDF kosong");
                }

                filename = "laporan_show.pdf";
            } else {
                pdfContent = adminService.generateUserReport();
                filename = "laporan_pengguna.pdf";
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", filename);

            logger.info("Successfully generated {} report", reportType);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfContent);
        } catch (Exception e) {
            logger.error("Error generating report: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/statistics")
    public String showStatistics(Model model) {
        model.addAttribute("showStats", adminService.getShowStatistics());
        model.addAttribute("setlistStats", adminService.getSetlistStatistics());
        return "admin/statistics";
    }

    @PostMapping("/upload-image")
    public String uploadImage(@RequestParam("image") MultipartFile image, @RequestParam("showId") int showId) throws IOException {
        // Mendapatkan direktori kerja aplikasi dan menentukan folder upload di dalam folder static
        String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/upload/";

        // Membuat folder jika belum ada
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs(); // Membuat folder jika belum ada
        }

        // Menentukan jalur untuk menyimpan gambar
        String imagePath = "/upload/" + image.getOriginalFilename();

        // Menyimpan gambar ke folder server
        File destinationFile = new File(uploadDir + image.getOriginalFilename());
        image.transferTo(destinationFile);

        // Hanya memperbarui path gambar di database
        showService.updateImagePath(showId, imagePath);

        // Redirect ke halaman detail show setelah upload
        return "redirect:/admin/show?id=" + showId;
    }
}
