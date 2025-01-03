// package com.example.TugasBesar.Setlist;

// import lombok.Data;

// @Data
// public class Setlist {
//     private final int setlist_id;
//     private final int show_id;
//     private final int song_order;
//     private final String song_title;
//     private final String created_by;
//     private final String created_at;
// }

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

    public Setlist(int setlist_id, int show_id, int song_order, String song_title, String created_by, String created_at) {
        this.setlist_id = setlist_id;
        this.show_id = show_id;
        this.song_order = song_order;
        this.song_title = song_title;
        this.created_by = created_by;
        this.created_at = created_at;
    }
}
