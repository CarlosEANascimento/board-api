package projeto.board.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardBlockedRepository extends JpaRepository<Card, Long> {
    
}
