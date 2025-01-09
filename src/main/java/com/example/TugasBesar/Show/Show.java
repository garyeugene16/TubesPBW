package com.example.TugasBesar.Show;

import lombok.Data;

@Data
public class Show {
    private final int show_id;
    private final int artist_id;
    private final String venue;
    private final String date;
    private final String created_by;
    private final String created_at;
    private final String name;

    public int getShowId() {
        return show_id;
    }
}
