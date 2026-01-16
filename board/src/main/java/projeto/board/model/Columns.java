package projeto.board.model;

import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name = "columns")
public class Columns {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private Integer position;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @OneToMany(mappedBy = "column")
    private List<Card> cards = new ArrayList<>();
}

enum Status {
    INICIAL,
    PENDENTE,
    FINAL,
    CANCELADO
}