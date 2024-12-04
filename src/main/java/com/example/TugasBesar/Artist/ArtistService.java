@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    public List<Artist> searchArtists(String name) {
        return artistRepository.findByNameContainingIgnoreCase(name);
    }
}
