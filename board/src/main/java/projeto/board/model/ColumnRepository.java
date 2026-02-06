package projeto.board.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnRepository extends JpaRepository<Columns, Long> {
    Columns findByPositionAndBoard(Integer position, Board board);
    Columns findByStatusAndBoard(Status status, Board board);
    Columns findByStatusAndBoard_Id(Status status, Long boardId);
    List<Columns> findByBoard_Id(Long boardId);
    void deleteByBoard_Id(Long boardId);
}
