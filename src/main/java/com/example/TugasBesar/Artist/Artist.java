@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer artistId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "user_id", nullable = true)
    private User createdBy;

    private Timestamp createdAt;

    // Getters and setters
}
