@RestController
@RequestMapping("/api/artists")
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @GetMapping("/search")
    public List<Artist> searchArtists(@RequestParam String name) {
        return artistService.searchArtists(name);
    }
}
