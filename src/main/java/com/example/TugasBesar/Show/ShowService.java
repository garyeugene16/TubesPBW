package com.example.TugasBesar.Show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    private JdbcShowRepository showRepository;

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    public List<Show> getShowsByArtistId(int artistId) {
        return showRepository.getShowsByArtistId(artistId);
    }

    public Show getShowById(int id) {
        return showRepository.findById(id);
    }

    public boolean createShow(int artistName, String venue, LocalDate date, String username) {
        return showRepository.save(artistName,venue, date, username);
    }

    public int updateShow(Show show) {
        return showRepository.update(show);
    }

    public int deleteShow(int id) {
        return showRepository.deleteById(id);
    }
}
