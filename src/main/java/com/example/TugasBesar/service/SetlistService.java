package com.example.TugasBesar.service;

import com.example.TugasBesar.model.Setlist;
import com.example.TugasBesar.repository.SetlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetlistService {

    @Autowired
    private SetlistRepository setlistRepository;

    // Fungsi untuk mencari Setlist
    public List<Setlist> searchSetlists(String query) {
        return setlistRepository.findBySongTitleContainingIgnoreCase(query);
    }
}
