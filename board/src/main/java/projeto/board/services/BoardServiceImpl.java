package projeto.board.services;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.board.exception.BoardNotFoundException;
import projeto.board.model.*;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardBlockedRepository cardBlockedRepository;

    @Override
    @Transactional
    public Board createBoard(String name) {
        log.info("Creating board with name: {}", name);

        Board board = new Board();
        board.setName(name.trim());

        board.addColumn(new Columns("Inicial", 1, Status.INICIAL, board));
        board.addColumn(new Columns("Pendente", 2, Status.PENDENTE, board));
        board.addColumn(new Columns("Final", 3, Status.FINAL, board));
        board.addColumn(new Columns("Cancelado", 4, Status.CANCELADO, board));

        Board saved = boardRepository.save(board);
        log.info("Board created successfully with id: {}", saved.getId());
        return saved;
    }

    @Override
    public Page<Board> selectAllBoards(Pageable pageable) {
        log.info("Fetching boards with pagination: page={}, size={}, sort={}", pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
        return boardRepository.findAll(pageable);
    }

    @Override
    public Board selectBoardById(Long boardId) {
        log.debug("Fetching board with id: {}", boardId);
        
        return boardRepository.findById(boardId).orElseThrow(() -> {
            log.warn("Board not found with id: {}", boardId);
            return new BoardNotFoundException(boardId);
        });
    }

    @Override
    @Transactional
    public void deleteBoard(Long boardId) {
        log.info("Deleting board with id: {}", boardId);

        if(boardRepository.existsById(boardId)) {
            List<Columns> columns = columnRepository.findByBoard_Id(boardId);

            columns.forEach(col -> {
                col.getCards().forEach(card -> {
                    cardBlockedRepository.deleteByCard_Id(card.getId());
                });

                cardRepository.deleteByColumn_Id(col.getId());
            });

            columnRepository.deleteByBoard_Id(boardId);
            boardRepository.deleteById(boardId);
            log.info("Board deleted successfully with id: {}", boardId);
        } else {
            log.info("Attempted to delete non-existent board with id: {}", boardId);
        }
    }
}
