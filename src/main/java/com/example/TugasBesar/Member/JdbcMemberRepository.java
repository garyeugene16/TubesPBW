package com.example.TugasBesar.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcMemberRepository implements MemberRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addArtist(String name, String createdBy) {
        String sql = "INSERT INTO artists (name, created_by) VALUES (?, ?)";
        jdbcTemplate.update(sql, name, createdBy);
    }

    @Override
    public void addShow(int artistId, String venue, String date, String createdBy) {
        String sql = "INSERT INTO shows (artist_id, venue, date, created_by) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, artistId, venue, date, createdBy);
    }

    @Override
    public void addSetlist(int showId, int songOrder, String songTitle, String createdBy) {
        String sql = "INSERT INTO setlists (show_id, song_order, song_title, created_by) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, showId, songOrder, songTitle, createdBy);
    }
}