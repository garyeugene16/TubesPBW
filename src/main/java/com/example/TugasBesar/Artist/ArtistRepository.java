package com.example.TugasBesar.Artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ArtistRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Find all artists
    public List<Artist> findAll() {
        String sql = "SELECT * FROM artists";
        return jdbcTemplate.query(sql, this::mapRowToArtist);
    }

    // Find artist by ID
    public Artist findById(int id) {
        String sql = "SELECT * FROM artists WHERE artist_id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToArtist, id);
    }

    // Save a new artist
    public int save(Artist artist) {
        String sql = "INSERT INTO artists (name, created_by, created_at) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, artist.getName(), artist.getCreated_by(), artist.getCreated_at());
    }

    // Update an existing artist
    public int update(Artist artist) {
        String sql = "UPDATE artists SET name = ?, created_by = ?, created_at = ? WHERE artist_id = ?";
        return jdbcTemplate.update(sql, artist.getName(), artist.getCreated_by(), artist.getCreated_at(), artist.getArtist_id());
    }

    // Delete an artist by ID
    public int deleteById(int id) {
        String sql = "DELETE FROM artists WHERE artist_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Map ResultSet to Artist object
    private Artist mapRowToArtist(ResultSet resultSet, int rowNum) throws SQLException {
        return new Artist(
            resultSet.getInt("artist_id"),
            resultSet.getString("name"),
            resultSet.getInt("created_by"),
            resultSet.getString("created_at")
        );
    }
}
