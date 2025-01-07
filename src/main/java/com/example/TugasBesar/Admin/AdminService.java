package com.example.TugasBesar.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TugasBesar.Setlist.Setlist;
import com.example.TugasBesar.Show.Show;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Show> searchShowsByArtist(String artistName) {
        return adminRepository.findShowsByArtist(artistName);
    }

    public Show getShowDetails(int show_id) {
        return adminRepository.findShowDetails(show_id);
    }

    public List<Setlist> getSetlistByShowId(int show_id) {
        return adminRepository.findSetlistByShowId(show_id);
    }

    public List<Show> searchShowsByKeywordWithPagination(String keyword, int page, int size) {
        int offset = page * size;
        return adminRepository.findShowsByKeywordWithPagination(keyword, offset, size);
    }

    public int getTotalPages(String keyword, int size) {
        int totalRecords = adminRepository.countShowsByKeyword(keyword);
        return (int) Math.ceil((double) totalRecords / size);
    }
}
