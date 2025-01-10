package com.example.TugasBesar.Setlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetlistService {

    @Autowired
    private JdbcSetlistRepository setlistRepository;

    @Autowired
    private com.example.TugasBesar.Show.ShowRepository showRepository;

    public void addSong(int showId, String songTitle, int songOrder, String youtubeUrl, String username, String role) {
        if (!canEditSetlist(showId, username, role)) {
            throw new IllegalArgumentException("You do not have permission to edit this setlist.");
        }
        Setlist setlist = new Setlist(0, showId, songOrder, songTitle, username, null, youtubeUrl);
        setlistRepository.save(setlist);
    }

    public void deleteSong(int setlistId, String username, String role) {
        Setlist setlist = setlistRepository.findById(setlistId);
        if (!canEditSetlist(setlist.getShow_id(), username, role)) {
            throw new IllegalArgumentException("You do not have permission to delete this song.");
        }
        setlistRepository.deleteById(setlistId);
    }

    // Update Lagu Individual
    public void updateSongOrder(int setlistId, int songOrder, String username, String role) {
        Setlist setlist = setlistRepository.findById(setlistId);
        if (!canEditSetlist(setlist.getShow_id(), username, role)) {
            throw new IllegalArgumentException("You do not have permission to update this song.");
        }
        setlist.setSong_order(songOrder);
        setlistRepository.update(setlist);
    }

    // Update Lagu Semua
    public void updateAllSongOrders(List<Integer> setlistIds, List<Integer> songOrders, String username, String role) {
        if (setlistIds.size() != songOrders.size()) {
            throw new IllegalArgumentException("Mismatch between setlist IDs and song orders.");
        }
        for (int i = 0; i < setlistIds.size(); i++) {
            updateSongOrder(setlistIds.get(i), songOrders.get(i), username, role);
        }
    }

    // Yang Bisa Melakukan Edit hanya Admin / Member Pembuat Setlist Tersebut
    // private boolean canEditSetlist(int showId, String username, String role) {
    //     return role.equals("admin") || 
    //            (role.equals("member") && setlistRepository.isCreatedBy(showId, username));
    // }

    // Yang Bisa Melakukan Edit hanya Admin / Member Pembuat Setlist / Member Pembuat Show
    private boolean canEditSetlist(int showId, String username, String role) {
        return role.equals("admin")
            || (
                role.equals("member") 
                && (
                    setlistRepository.isCreatedBy(showId, username)  // pembuat setlist
                    || showRepository.isCreatedBy(showId, username)  // pembuat show
                )
            );
    }
}
