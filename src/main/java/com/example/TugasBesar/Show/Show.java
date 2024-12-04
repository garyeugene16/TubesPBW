@Entity
@Table(name = "shows")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showId;

    @ManyToOne
    @JoinColumn(name = "artist_id", referencedColumnName = "artist_id", nullable = false)
    private Artist artist;

    private String venue;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "user_id", nullable = true)
    private User createdBy;

    private Timestamp createdAt;

    // Getters and setters
}
