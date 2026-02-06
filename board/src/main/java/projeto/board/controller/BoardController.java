package projeto.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.board.dto.BoardRequestDTO;
import projeto.board.dto.BoardResponseDTO;
import projeto.board.mapper.BoardMapper;
import projeto.board.model.Board;
import projeto.board.services.BoardServiceImpl;

@Slf4j
@RestController
@RequestMapping("/boards")
public class BoardController {
    @Autowired
    BoardServiceImpl boardService;

    @Autowired
    BoardMapper boardMapper;

    // create
    @PostMapping("/new")
    public ResponseEntity<BoardResponseDTO> newBoard(@RequestBody BoardRequestDTO dto) {
        log.info("POST /boards/new - Creating new board with name: {}", dto.getName());
        Board newBoard = boardService.createBoard(dto.getName());
        log.debug("Board created with id: {}", newBoard.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(boardMapper.toBoardResponseDTO(newBoard));
    }

    // read
    @GetMapping
    public ResponseEntity<Page<BoardResponseDTO>> listBoards(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        log.info("GET /boards - Listing boards with pagination: page = {}, size = {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<BoardResponseDTO> response = boardService.selectAllBoards(pageable).map(board -> boardMapper.toBoardResponseDTO(board));
        log.debug("Found {} boards", response.getTotalElements());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDTO> getBoard(@PathVariable Long boardId) {
        log.info("GET /boards/{} - Fetching board", boardId);
        Board board = boardService.selectBoardById(boardId);
        return ResponseEntity.ok(boardMapper.toBoardResponseDTO(board));
    }

    // delete
    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable long boardId) {
        log.info("DELETE /boards/{} - Deleting board", boardId);
        boardService.deleteBoard(boardId);
        return ResponseEntity.noContent().build();
    }
}
