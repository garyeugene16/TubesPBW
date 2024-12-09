package com.example.TugasBesar.Guest;

import java.util.List;

import com.example.TugasBesar.Setlist.Setlist;
import com.example.TugasBesar.Show.Show;

public interface GuestRepository {
    List<Show> findShowsByArtist(String artistName);
    Show findShowDetails(int show_id);
    List<Setlist> findSetlistByShowId(int showId);
}

