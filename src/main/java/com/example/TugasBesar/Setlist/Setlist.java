package com.example.TugasBesar.Setlist;

import lombok.Data;

@Data
public class Setlist {
    private final int setlist_id;
    private final int show_id;
    private final int song_order;
    private final String song_title;
    private final String created_by;
    private final String created_at;
}
