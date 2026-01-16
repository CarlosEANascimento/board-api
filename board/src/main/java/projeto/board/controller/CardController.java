package projeto.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
public class CardController {

    // TODO: access to Board related services through API endpoints

    // create
    @PostMapping
    public ResponseEntity<Void> createCard() {
        // TODO: consume a card related service to create a card
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // move card to a specific card depending on endpoint
    @PatchMapping("/{cardId}/move")
    public ResponseEntity<Void> moveCard(@PathVariable Long cardId) {
        // TODO: consume a card related service to move a card from a initial column to another (it is not possible to move to 'CANCELADO' column using this endpoint)
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{cardId}/cancel")
    public ResponseEntity<Void> cancelCard(@PathVariable Long cardId) {
        // TODO: consume a card related service to move a card from a initial column to 'CANCELADO' column
        return ResponseEntity.noContent().build();
    }

    // interact with the block card functionality
    @PatchMapping("/{cardId}/block")
    public ResponseEntity<Void> blockCard(@PathVariable Long cardId) {
        // TODO: consume a card related service to block a card
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{cardId}/block")
    public ResponseEntity<Void> unblockCard(@PathVariable Long cardId) {
        // TODO: consume a card related service do unblock a card
        return ResponseEntity.noContent().build();
    }
}
