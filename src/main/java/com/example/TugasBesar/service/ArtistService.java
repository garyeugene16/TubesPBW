package com.example.TugasBesar.service;

import com.example.TugasBesar.model.Artist;
import com.example.TugasBesar.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    // Fungsi untuk mencari Artis
    public List<Artist> searchArtists(String query) {
        return artistRepository.findByNameContainingIgnoreCase(query);
    }
}
