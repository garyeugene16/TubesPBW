public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    List<Artist> findByNameContainingIgnoreCase(String name);
}
