package com.example.TugasBesar.Show;

import java.time.LocalDate;
import java.util.List;

public interface ShowRepository {
    public List<Show> findAll();
    public List<Show> getShowsByArtistId(int artistId);
    public Show findById(int id);
    public boolean save(int artistName, String venue, LocalDate date, String username);
    public int update(Show show);
    public int deleteById(int id);
}
