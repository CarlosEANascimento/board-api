package projeto.board.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "card_blocked")
public class CardbBocked {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @Column(nullable = false)
    private String blockReason;
    
    @Column(nullable = false)
    private LocalDateTime blockedAt;

    @Column(nullable = false)
    private LocalDateTime unblockedAt;

    @Column(columnDefinition = "TEXT")
    private String unblockReason;

    @Column
    private LocalDateTime unlocedkAt;
}
