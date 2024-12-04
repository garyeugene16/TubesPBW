@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;

    public List<Show> searchShows(String artistName) {
        return showRepository.findByArtist_NameContainingIgnoreCase(artistName);
    }
}
