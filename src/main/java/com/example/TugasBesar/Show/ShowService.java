package com.example.TugasBesar.Show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    public Show getShowById(int id) {
        return showRepository.findById(id);
    }

    public int createShow(Show show) {
        return showRepository.save(show);
    }

    public int updateShow(Show show) {
        return showRepository.update(show);
    }

    public int deleteShow(int id) {
        return showRepository.deleteById(id);
    }
}
