package com.example.TugasBesar.Setlist;

import lombok.Data;

@Data
public class Setlist {
    private int setlist_id;
    private int show_id;
    private int song_order;
    private String song_title;
    private String created_by;
    private String created_at;
    private String youtube_url;

    public Setlist(int setlist_id, int show_id, int song_order, String song_title, String created_by, String created_at, String youtube_url) {
        this.setlist_id = setlist_id;
        this.show_id = show_id;
        this.song_order = song_order;
        this.song_title = song_title;
        this.created_by = created_by;
        this.created_at = created_at;
        this.youtube_url = youtube_url;
    }
    
    public int getShowId() {
        return show_id;
    }
    public int getSongOrder() {
        return song_order;
    }
    public String getSongTitle() {
        return song_title;
    }
}
