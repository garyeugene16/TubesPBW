package com.example.TugasBesar.Repository;

import com.example.TugasBesar.Model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByVenueContainingIgnoreCase(String venue);
}
