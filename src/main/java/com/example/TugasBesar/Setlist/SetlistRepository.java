package com.example.TugasBesar.Setlist;

import java.util.List;

public interface SetlistRepository {
    void save(Setlist setlist);

    void deleteById(int setlistId);

    Setlist findById(int setlistId);

    void update(Setlist setlist);

    boolean isCreatedBy(int showId, String username);

    List<Setlist> findByShowId(int showId);
}
