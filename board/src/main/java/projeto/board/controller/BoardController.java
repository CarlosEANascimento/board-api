package projeto.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.board.model.Board;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    // TODO: access to Board related services through API endpoints

    // create
    @PostMapping
    public ResponseEntity<Void> createBoard() {
        // TODO: consume a board related service to create a board
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // read
    @GetMapping
    public ResponseEntity<List<Board>> listBoards() {
        // TODO: consume a board related service to list all board
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<Board> getBoard(@PathVariable Long boardId) {
        // TODO: consume a board related service to get a specific board using it's id as a specifier
        return ResponseEntity.ok().build();
    }

    // delete
    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable long boardId) {
        // TODO: consume a board related service to delete a board using it's id as a specifier
        return ResponseEntity.noContent().build();
    }
}
