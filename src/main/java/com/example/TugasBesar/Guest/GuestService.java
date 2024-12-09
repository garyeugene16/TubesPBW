package com.example.TugasBesar.Guest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TugasBesar.Setlist.Setlist;
import com.example.TugasBesar.Show.Show;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    public List<Show> searchShowsByArtist(String artistName) {
        return guestRepository.findShowsByArtist(artistName);
    }

    public Show getShowDetails(int show_id) {
        return guestRepository.findShowDetails(show_id);
    }

    public List<Setlist> getSetlistByShowId(int show_id) {
        return guestRepository.findSetlistByShowId(show_id);
    }
}

