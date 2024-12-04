package com.example.TugasBesar.Artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Artist getArtistById(int id) {
        return artistRepository.findById(id);
    }

    public int createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public int updateArtist(Artist artist) {
        return artistRepository.update(artist);
    }

    public int deleteArtist(int id) {
        return artistRepository.deleteById(id);
    }
}
