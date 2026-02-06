package projeto.board.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    void deleteByColumn_Id(Long columnId);
}
