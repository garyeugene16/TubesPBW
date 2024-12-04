package com.example.TugasBesar.Repository;

import com.example.TugasBesar.Model.Setlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetlistRepository extends JpaRepository<Setlist, Long> {
    List<Setlist> findBySongTitleContainingIgnoreCase(String songTitle);
}
