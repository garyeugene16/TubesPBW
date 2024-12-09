package com.example.TugasBesar.Setlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetlistService {
    @Autowired
    private SetlistRepository setlistRepository;

    public List<Setlist> getAllSetlists() {
        return setlistRepository.findAll();
    }

    public Setlist getSetlistById(int id) {
        return setlistRepository.findById(id);
    }

    public int createSetlist(Setlist setlist) {
        return setlistRepository.save(setlist);
    }

    public int updateSetlist(Setlist setlist) {
        return setlistRepository.update(setlist);
    }

    public int deleteSetlist(int id) {
        return setlistRepository.deleteById(id);
    }
}
