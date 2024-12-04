public interface SetlistRepository extends JpaRepository<Setlist, Integer> {
    List<Setlist> findByShow_ShowId(Integer showId);
}
