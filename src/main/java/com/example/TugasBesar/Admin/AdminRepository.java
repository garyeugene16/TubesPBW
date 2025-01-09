package com.example.TugasBesar.Admin;

import java.util.List;
import java.util.Map;

import com.example.TugasBesar.Setlist.Setlist;
import com.example.TugasBesar.Show.Show;
import com.example.TugasBesar.User.User;

public interface AdminRepository {
    List<User> findAllUsers();

    User findUserByUsername(String username);

    void updateUser(User user);

    List<Show> findShowsByDateRange(String startDate, String endDate);

    List<Setlist> findSetlistsByShowIds(List<Integer> showIds);
    
    List<Map<String, Object>> getShowCountPerMonth();

    List<Map<String, Object>> getSetlistCountPerMonth();

    // from Member :
    List<Show> findShowsByArtist(String artistName);

    Show findShowDetails(int show_id);

    List<Setlist> findSetlistByShowId(int showId);

    List<Show> findShowsByKeywordWithPagination(String keyword, int offset, int limit);

    int countShowsByKeyword(String keyword);


}
