package com.example.TugasBesar.Show;

import lombok.Data;

@Data
public class ShowWithArtist {
    private final int show_id;
    private final int artist_id;
    private final String artist_name;
    private final String venue;
    private final String date;
    private final int created_by;
    private final String created_at;
}
