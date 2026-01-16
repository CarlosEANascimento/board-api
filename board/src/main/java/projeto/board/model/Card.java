package projeto.board.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cards")
public class Card {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private boolean blocked = false;

    @ManyToOne
    @JoinColumn(name = "column_id", nullable = false)
    private Columns column;
}
