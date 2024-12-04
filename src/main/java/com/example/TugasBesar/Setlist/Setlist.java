@Entity
@Table(name = "setlists")
public class Setlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer setlistId;

    @ManyToOne
    @JoinColumn(name = "show_id", referencedColumnName = "show_id", nullable = false)
    private Show show;

    private Integer songOrder;

    private String songTitle;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "user_id", nullable = true)
    private User createdBy;

    private Timestamp createdAt;

    // Getters and setters
}
