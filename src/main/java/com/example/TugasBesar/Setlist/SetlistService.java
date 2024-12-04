@Service
public class SetlistService {
    @Autowired
    private SetlistRepository setlistRepository;

    public List<Setlist> getSetlistsByShow(Integer showId) {
        return setlistRepository.findByShow_ShowId(showId);
    }
}
