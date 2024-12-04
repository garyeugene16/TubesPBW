@RestController
@RequestMapping("/api/setlists")
public class SetlistController {
    @Autowired
    private SetlistService setlistService;

    @GetMapping("/{showId}")
    public List<Setlist> getSetlists(@PathVariable Integer showId) {
        return setlistService.getSetlistsByShow(showId);
    }
}
