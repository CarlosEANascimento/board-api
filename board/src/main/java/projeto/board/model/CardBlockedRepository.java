package projeto.board.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardBlockedRepository extends JpaRepository<CardBlocked, Long> {
    CardBlocked findByCard_IdAndUnblockedAtIsNull(Long cardId);
    List<CardBlocked> findByCard_IdOrderByBlockedAtAsc(Long cardId);
    void deleteByCard_Id(Long cardId);
}
