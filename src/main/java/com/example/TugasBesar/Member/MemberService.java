package com.example.TugasBesar.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;

    // Method to add an artist
    public void addArtist(String name, String createdBy) {
        memberRepository.addArtist(name, createdBy);
    }

    // Method to add a show
    public void addShow(int artistId, String venue, String date, String createdBy) {
        memberRepository.addShow(artistId, venue, date, createdBy);
    }

    // Method to add a setlist
    public void addSetlist(int showId, int songOrder, String songTitle, String createdBy) {
        memberRepository.addSetlist(showId, songOrder, songTitle, createdBy);
    }
}
