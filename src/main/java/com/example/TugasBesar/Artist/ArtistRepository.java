package com.example.TugasBesar.Artist;

import java.util.List;

public interface ArtistRepository {
    public List<Artist> findAll();

    public Artist findById(int id);

    public boolean save(String artist, String username);

    public int update(Artist artist);

    public int deleteById(int id);
}
