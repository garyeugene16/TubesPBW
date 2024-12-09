package com.example.TugasBesar.Show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ShowRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Show> findAll() {
        String sql = "SELECT * FROM shows";
        return jdbcTemplate.query(sql, this::mapRowToShow);
    }

    public Show findById(int id) {
        String sql = "SELECT * FROM shows WHERE show_id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToShow, id);
    }

    public int save(Show show) {
        String sql = "INSERT INTO shows (artist_id, venue, date, created_by, created_at) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, show.getArtist_id(), show.getVenue(), show.getDate(), show.getCreated_by(), show.getCreated_at());
    }

    public int update(Show show) {
        String sql = "UPDATE shows SET artist_id = ?, venue = ?, date = ?, created_by = ?, created_at = ? WHERE show_id = ?";
        return jdbcTemplate.update(sql, show.getArtist_id(), show.getVenue(), show.getDate(), show.getCreated_by(), show.getCreated_at(), show.getShow_id());
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM shows WHERE show_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public List<ShowWithArtist> findAllWithArtist() {
        String sql = "SELECT s.*, a.name as artist_name FROM shows s " +
                    "JOIN artists a ON s.artist_id = a.artist_id";
        return jdbcTemplate.query(sql, this::mapRowToShowWithArtist);
    }

    private ShowWithArtist mapRowToShowWithArtist(ResultSet resultSet, int rowNum) throws SQLException {
        return new ShowWithArtist(
            resultSet.getInt("show_id"),
            resultSet.getInt("artist_id"),
            resultSet.getString("artist_name"),
            resultSet.getString("venue"),
            resultSet.getString("date"),
            resultSet.getInt("created_by"),
            resultSet.getString("created_at")
        );
    }

    private Show mapRowToShow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Show(
            resultSet.getInt("show_id"),
            resultSet.getInt("artist_id"),
            resultSet.getString("venue"),
            resultSet.getString("date"),
            resultSet.getInt("created_by"),
            resultSet.getString("created_at")
        );
    }
}