package com.example.TugasBesar.Admin;

import java.util.List;

import com.example.TugasBesar.Setlist.Setlist;
import com.example.TugasBesar.Show.Show;

public interface AdminRepository {
    List<Show> findShowsByArtist(String artistName);

    Show findShowDetails(int show_id);

    List<Setlist> findSetlistByShowId(int showId);

    List<Show> findShowsByKeywordWithPagination(String keyword, int offset, int limit);

    int countShowsByKeyword(String keyword);
}
