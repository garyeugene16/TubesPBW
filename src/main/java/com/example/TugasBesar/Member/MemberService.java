package com.example.TugasBesar.Member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TugasBesar.Setlist.Setlist;
import com.example.TugasBesar.Show.Show;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public List<Show> searchShowsByArtist(String artistName) {
        return memberRepository.findShowsByArtist(artistName);
    }

    public Show getShowDetails(int show_id) {
        return memberRepository.findShowDetails(show_id);
    }

    public List<Setlist> getSetlistByShowId(int show_id) {
        return memberRepository.findSetlistByShowId(show_id);
    }
}
