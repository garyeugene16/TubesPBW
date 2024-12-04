package com.example.TugasBesar.Setlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SetlistRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Setlist> findAll() {
        String sql = "SELECT * FROM setlists";
        return jdbcTemplate.query(sql, this::mapRowToSetlist);
    }

    public Setlist findById(int id) {
        String sql = "SELECT * FROM setlists WHERE setlist_id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToSetlist, id);
    }

    public int save(Setlist setlist) {
        String sql = "INSERT INTO setlists (show_id, song_order, song_title, created_by, created_at) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, setlist.getShow_id(), setlist.getSong_order(), setlist.getSong_title(), setlist.getCreated_by(), setlist.getCreated_at());
    }

    public int update(Setlist setlist) {
        String sql = "UPDATE setlists SET show_id = ?, song_order = ?, song_title = ?, created_by = ?, created_at = ? WHERE setlist_id = ?";
        return jdbcTemplate.update(sql, setlist.getShow_id(), setlist.getSong_order(), setlist.getSong_title(), setlist.getCreated_by(), setlist.getCreated_at(), setlist.getSetlist_id());
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM setlists WHERE setlist_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private Setlist mapRowToSetlist(ResultSet resultSet, int rowNum) throws SQLException {
        return new Setlist(
            resultSet.getInt("setlist_id"),
            resultSet.getInt("show_id"),
            resultSet.getInt("song_order"),
            resultSet.getString("song_title"),
            resultSet.getInt("created_by"),
            resultSet.getString("created_at")
        );
    }
}
