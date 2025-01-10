package com.example.TugasBesar.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.TugasBesar.User.User;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.util.Map;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import com.example.TugasBesar.Show.*;
import com.example.TugasBesar.Setlist.*;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<User> getAllUsers() {
        return adminRepository.findAllUsers();
    }

    public User getUserByUsername(String username) {
        return adminRepository.findUserByUsername(username);
    }

    public void updateUser(User user) {
        adminRepository.updateUser(user);
    }

    public byte[] generateShowReport(String startDate, String endDate) throws DocumentException {
        List<Show> shows = adminRepository.findShowsByDateRange(startDate, endDate);

        if (shows.isEmpty()) {
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            document.open();
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            document.add(new Paragraph("Laporan Show dan Setlist", titleFont));
            document.add(new Paragraph("Periode: " + startDate + " sampai " + endDate + "\n\n"));
            document.add(new Paragraph("Tidak ada show yang ditemukan dalam periode ini."));
            document.close();

            return baos.toByteArray();
        }

        List<Integer> showIds = shows.stream()
                .map(Show::getShow_id)
                .collect(Collectors.toList());
        List<Setlist> setlists = adminRepository.findSetlistsByShowIds(showIds);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);

        document.open();
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        Font normalFont = new Font(Font.FontFamily.HELVETICA, 12);

        document.add(new Paragraph("Laporan Show dan Setlist", titleFont));
        document.add(new Paragraph("Periode: " + startDate + " sampai " + endDate + "\n\n"));

        for (Show show : shows) {
            document.add(new Paragraph("Show: " + show.getName(), headerFont));
            document.add(new Paragraph("Venue: " + show.getVenue(), normalFont));
            document.add(new Paragraph("Tanggal: " + show.getDate() + "\n", normalFont));

            document.add(new Paragraph("Setlist:", headerFont));
            List<Setlist> showSetlists = setlists.stream()
                    .filter(s -> s.getShow_id() == show.getShow_id())
                    .collect(Collectors.toList());

            for (Setlist setlist : showSetlists) {
                document.add(new Paragraph(setlist.getSong_order() + ". " + setlist.getSong_title(), normalFont));
            }
            document.add(new Paragraph("\n"));
        }

        document.close();
        return baos.toByteArray();
    }

    public byte[] generateUserReport() throws DocumentException {
        List<User> users = getAllUsers();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);

        document.open();
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        Font normalFont = new Font(Font.FontFamily.HELVETICA, 12);

        document.add(new Paragraph("Laporan Pengguna", titleFont));
        document.add(new Paragraph(
                "Tanggal: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\n\n"));

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);

        table.addCell(new PdfPCell(new Phrase("Username", headerFont)));
        table.addCell(new PdfPCell(new Phrase("Nama", headerFont)));
        table.addCell(new PdfPCell(new Phrase("Role", headerFont)));

        for (User user : users) {
            table.addCell(new PdfPCell(new Phrase(user.getUsername(), normalFont)));
            table.addCell(new PdfPCell(new Phrase(user.getName(), normalFont)));
            table.addCell(new PdfPCell(new Phrase(user.getRole(), normalFont)));
        }

        document.add(table);
        document.close();
        return baos.toByteArray();
    }

    public List<Map<String, Object>> getShowStatistics() {
        return adminRepository.getShowCountPerMonth();
    }

    public List<Map<String, Object>> getSetlistStatistics() {
        return adminRepository.getSetlistCountPerMonth();
    }

}
