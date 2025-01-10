package com.example.TugasBesar.Show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class JdbcShowRepository implements ShowRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Show> findAll() {
        String sql = "SELECT * FROM shows";
        return jdbcTemplate.query(sql, this::mapRowToShow);
    }

    public List<Show> getShowsByArtistId(int artistId) {
        String sql = "SELECT * FROM shows WHERE artist_id = ?";
        return jdbcTemplate.query(sql, this::mapRowToShow);
    }

    public Show findById(int id) {
        String sql = "SELECT * FROM shows WHERE show_id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToShow, id);
    }

    public boolean save(int artistName, String venue, LocalDate date, String username) {
         // Query untuk menyimpan data ke tabel shows
        String sql = "INSERT INTO shows (artist_id, venue, date, created_by) VALUES (?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, artistName, venue, date, username);

        return rowsAffected > 0; // True jika insert berhasil
    }

    public int update(Show show) {
        String sql = "UPDATE shows SET artist_id = ?, venue = ?, date = ?, created_by = ?, created_at = ? WHERE show_id = ?";
        return jdbcTemplate.update(sql, show.getArtist_id(), show.getVenue(), show.getDate(), show.getCreated_by(), show.getCreated_at(), show.getShow_id());
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM shows WHERE show_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean isCreatedBy(int showId, String username) {
        String sql = "SELECT COUNT(*) FROM shows WHERE show_id = ? AND created_by = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, showId, username);
        return (count != null && count > 0);
    }

    

    private Show mapRowToShow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Show(
            resultSet.getInt("show_id"),
            resultSet.getInt("artist_id"),
            resultSet.getString("venue"),
            resultSet.getString("date"),
            resultSet.getString("created_by"),
            resultSet.getString("created_at"),
            resultSet.getString("name")
        );
    }

    @Override
    public void updateImagePath(int showId, String imagePath) {
        String sql = "UPDATE shows SET image_path = ? WHERE show_id = ?";

        // Eksekusi query UPDATE dengan parameter yang diberikan
        jdbcTemplate.update(sql, imagePath, showId);
    }
}