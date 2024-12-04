@RestController
@RequestMapping("/api/shows")
public class ShowController {
    @Autowired
    private ShowService showService;

    @GetMapping("/search")
    public List<Show> searchShows(@RequestParam String artistName) {
        return showService.searchShows(artistName);
    }
}
