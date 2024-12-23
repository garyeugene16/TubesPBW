package com.example.TugasBesar.Member;

public interface MemberRepository {
    // Method to add an artist
    void addArtist(String name, String createdBy);

    // Method to add a show
    void addShow(int artistId, String venue, String date, String createdBy);

    // Method to add a setlist
    void addSetlist(int showId, int songOrder, String songTitle, String createdBy);
}