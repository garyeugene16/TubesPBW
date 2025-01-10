package com.example.TugasBesar.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.TugasBesar.Setlist.Setlist;
import com.example.TugasBesar.Show.Show;
import com.example.TugasBesar.User.User;
import com.example.TugasBesar.Member.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@Repository
public class JdbcAdminRepository implements AdminRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, this::mapRowToUser);
    }

    @Override
    public User findUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToUser, username);
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE users SET name = ?, role = ? WHERE username = ?";
        jdbcTemplate.update(sql, user.getName(), user.getRole(), user.getUsername());
    }

    private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("password"),
                rs.getString("role"),
                rs.getString("name"));
    }

    // fitur member :
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

    // @Override
    // public List<Setlist> findSetlistByShowId(int show_id) {
    // String sql = "SELECT * FROM setlists WHERE show_id = ?";
    // return jdbcTemplate.query(sql, this::mapRowToSetlist, show_id);
    // }

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

    @Override
    public List<Setlist> findSetlistByShowId(int show_id) {
        // Tambahkan ORDER BY song_order ASC agar urutan lagu sesuai kolom song_order
        String sql = "SELECT * FROM setlists WHERE show_id = ? ORDER BY song_order ASC";
        return jdbcTemplate.query(sql, this::mapRowToSetlist, show_id);
    }

    @Override
    public List<Show> findShowsByDateRange(String startDate, String endDate) {
        String sql = "SELECT s.show_id, s.artist_id, s.venue, s.date, s.created_by, s.created_at, a.name " +
                "FROM shows s JOIN artists a ON s.artist_id = a.artist_id " +
                "WHERE s.date BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD') ORDER BY s.date";
        return jdbcTemplate.query(sql, this::mapRowToShow, startDate, endDate);
    }

    @Override
    public List<Setlist> findSetlistsByShowIds(List<Integer> showIds) {
        // Jika list kosong, kembalikan list kosong
        if (showIds.isEmpty()) {
            return new ArrayList<>();
        }

        String sql = "SELECT * FROM setlists WHERE show_id IN (:showIds) ORDER BY show_id, song_order";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("showIds", showIds);

        return new NamedParameterJdbcTemplate(jdbcTemplate).query(
                sql,
                parameters,
                this::mapRowToSetlist);
    }

    private Member mapRowToMember(ResultSet resultSet, int rowNum) throws SQLException {
        return new Member(
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("password"),
                resultSet.getString("name"),
                resultSet.getString("role"));
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

    private Setlist mapRowToSetlist(ResultSet resultSet, int rowNum) throws SQLException {
        return new Setlist(
                resultSet.getInt("setlist_id"),
                resultSet.getInt("show_id"),
                resultSet.getInt("song_order"),
                resultSet.getString("song_title"),
                resultSet.getString("created_by"),
                resultSet.getString("created_at"));
    }

    @Override
    public List<Map<String, Object>> getShowCountPerMonth() {
        String sql = "SELECT TO_CHAR(date, 'YYYY-MM') as month, " +
                "COUNT(*) as count FROM shows " +
                "GROUP BY TO_CHAR(date, 'YYYY-MM') " +
                "ORDER BY month DESC LIMIT 12";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getSetlistCountPerMonth() {
        String sql = "SELECT TO_CHAR(created_at, 'YYYY-MM') as month, " +
                "COUNT(*) as count FROM setlists " +
                "GROUP BY TO_CHAR(created_at, 'YYYY-MM') " +
                "ORDER BY month DESC LIMIT 12";
        return jdbcTemplate.queryForList(sql);
    }
}