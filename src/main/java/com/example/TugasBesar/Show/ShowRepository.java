public interface ShowRepository extends JpaRepository<Show, Integer> {
    List<Show> findByArtist_NameContainingIgnoreCase(String artistName);
}
