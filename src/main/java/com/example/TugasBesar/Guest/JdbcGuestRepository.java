package com.example.TugasBesar.Guest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.TugasBesar.Show.Show;
import com.example.TugasBesar.Setlist.Setlist;

@Repository
public class JdbcGuestRepository implements GuestRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Show> findShowsByArtist(String artistName) {
        String sql = "SELECT s.show_id, s.artist_id, s.venue, s.date, s.created_by, s.created_at, a.name FROM shows s JOIN artists a ON s.artist_id = a.artist_id WHERE a.name ILIKE ?";
        String keyword = "%" + artistName + "%";
        List<Show> show = jdbcTemplate.query(sql, this::mapRowToShow, keyword);
        return show;
    }

    @Override
    public Show findShowDetails(int show_id) {
        // String sql = "SELECT show_id, artist_id, venue, date, created_by, created_at
        // FROM shows WHERE artist_id = ?";
        String sql = "SELECT s.show_id, s.artist_id, s.venue, s.date, s.created_by, s.created_at, a.name FROM shows s JOIN artists a ON s.artist_id = a.artist_id WHERE s.show_id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToShow, show_id);
    }

    @Override
    public List<Setlist> findSetlistByShowId(int show_id) {
        String sql = "SELECT * FROM setlists WHERE show_id = ?";
        return jdbcTemplate.query(sql, this::mapRowToSetlist, show_id);
    }

    @Override
    public List<Show> findShowsByKeywordWithPagination(String keyword, int offset, int limit) {
        String sql = "SELECT s.show_id, s.artist_id, s.venue, s.date, s.created_by, s.created_at, a.name " +
                "FROM shows s JOIN artists a ON s.artist_id = a.artist_id " +
                "WHERE s.venue ILIKE ? OR a.name ILIKE ? " +
                "ORDER BY s.date DESC LIMIT ? OFFSET ?";
        String searchKeyword = "%" + keyword + "%";
        return jdbcTemplate.query(sql, this::mapRowToShow, searchKeyword, searchKeyword, limit, offset);
    }

    @Override
    public int countShowsByKeyword(String keyword) {
        String sql = "SELECT COUNT(*) FROM shows s JOIN artists a ON s.artist_id = a.artist_id " +
                "WHERE s.venue ILIKE ? OR a.name ILIKE ?";
        String searchKeyword = "%" + keyword + "%";
        return jdbcTemplate.queryForObject(sql, Integer.class, searchKeyword, searchKeyword);
    }

    private Show mapRowToShow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Show(
                resultSet.getInt("show_id"),
                resultSet.getInt("artist_id"),
                resultSet.getString("venue"),
                resultSet.getString("date"),
                resultSet.getString("created_by"),
                resultSet.getString("created_at"),
                resultSet.getString("name"));
    }

    private Setlist mapRowToSetlist(ResultSet resultSet, int rowNum) throws SQLException {
        return new Setlist(
                resultSet.getInt("setlist_id"),
                resultSet.getInt("show_id"),
                resultSet.getInt("song_order"),
                resultSet.getString("song_title"),
                resultSet.getString("created_by"),
                resultSet.getString("created_at"));
    }
}
