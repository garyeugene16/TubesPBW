package com.example.TugasBesar.Setlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcSetlistRepository implements SetlistRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Setlist setlist) {
        String sql = "INSERT INTO setlists (show_id, song_order, song_title, created_by, youtube_url) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, setlist.getShow_id(), setlist.getSong_order(), setlist.getSong_title(), setlist.getCreated_by(), setlist.getYoutube_url());
    }

    @Override
    public void deleteById(int setlistId) {
        String sql = "DELETE FROM setlists WHERE setlist_id = ?";
        jdbcTemplate.update(sql, setlistId);
    }

    @Override
    public Setlist findById(int setlistId) {
        String sql = "SELECT * FROM setlists WHERE setlist_id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToSetlist, setlistId);
    }

    @Override
    public void update(Setlist setlist) {
        String sql = "UPDATE setlists SET song_order = ?, youtube_url = ? WHERE setlist_id = ?";
        jdbcTemplate.update(sql, setlist.getSong_order(), setlist.getYoutube_url(), setlist.getSetlist_id());
    }

    @Override
    public boolean isCreatedBy(int showId, String username) {
        String sql = "SELECT COUNT(*) FROM setlists WHERE show_id = ? AND created_by = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, showId, username);
        return count != null && count > 0;
    }

    // @Override
    // public List<Setlist> findByShowId(int showId) {
    //     String sql = "SELECT * FROM setlists WHERE show_id = ?";
    //     return jdbcTemplate.query(sql, this::mapRowToSetlist, showId);
    // }

    @Override
    public List<Setlist> findByShowId(int showId) {
        // Tambahkan ORDER BY song_order ASC agar urutan lagu sesuai kolom song_order
        String sql = "SELECT * FROM setlists WHERE show_id = ? ORDER BY song_order ASC";
        return jdbcTemplate.query(sql, this::mapRowToSetlist, showId);
    }


    private Setlist mapRowToSetlist(ResultSet rs, int rowNum) throws SQLException {
        return new Setlist(
                rs.getInt("setlist_id"),
                rs.getInt("show_id"),
                rs.getInt("song_order"),
                rs.getString("song_title"),
                rs.getString("created_by"),
                rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime().toString() : null,
                rs.getString("youtube_url")
        );
    }
}