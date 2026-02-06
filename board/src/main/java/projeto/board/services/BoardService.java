package projeto.board.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import projeto.board.model.Board;

public interface BoardService {
    Board createBoard(String name);
    Page<Board> selectAllBoards(Pageable pageable);
    Board selectBoardById(Long boardId);
    void deleteBoard(Long boardId);
}
