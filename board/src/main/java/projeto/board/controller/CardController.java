package projeto.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import projeto.board.dto.BlockRequestDTO;
import projeto.board.dto.CardBlockedResponseDTO;
import projeto.board.dto.CardRequestDTO;
import projeto.board.dto.CardResponseDTO;
import projeto.board.mapper.BoardMapper;
import projeto.board.model.Card;
import projeto.board.model.CardRepository;
import projeto.board.services.CardService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardRepository cardRepository;

    @Autowired
    private CardService cardService;

    @Autowired
    private BoardMapper boardMapper;

    CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    // create
    @PostMapping("/{boardId}/new")
    public ResponseEntity<CardResponseDTO> newCard(@PathVariable Long boardId , @Valid @RequestBody CardRequestDTO dto) {
        log.info("POST /cards/{}/new - Creating new card with title: {}", boardId, dto.getTitle());
        Card card = cardService.createCard(boardId, dto);
        log.debug("Card created with id: {}", card.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(boardMapper.toCardResponseDTO(card));
    }

    // read
    @GetMapping("/{cardId}/block-history")
    public ResponseEntity<List<CardBlockedResponseDTO>> getBlockHistory(@PathVariable Long cardId) {
        log.info("GET /cards/{}/block-history - Fetching block history", cardId);
        List<CardBlockedResponseDTO> response = cardService.getCardBlockHistory(cardId).stream().map(cb -> boardMapper.toCardBlockedResponseDTO(cb)).toList();
        log.debug("Found {} block history records", response.size());
        return ResponseEntity.ok(response);
    }

    // edit
    @PatchMapping("/{cardId}/move")
    public ResponseEntity<Void> moveCard(@PathVariable Long cardId) {
        log.info("PATCH /cards/{}/move - Moving card to next column", cardId);
        cardService.moveCardToNextColumn(cardId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{cardId}/cancel")
    public ResponseEntity<Void> cancelCard(@PathVariable Long cardId) {
        log.info("PATCH /cards/{}/cancel - Canceling card", cardId);
        cardService.cancelCard(cardId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{cardId}/block")
    public ResponseEntity<Void> blockCard(@PathVariable Long cardId, @Valid @RequestBody BlockRequestDTO dto) {
        log.info("PATCH /cards/{}/block - Blocking card with reason: {}", cardId, dto.getReason());
        cardService.blockCard(cardId, dto.getReason());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{cardId}/unblock")
    public ResponseEntity<Void> unblockCard(@PathVariable Long cardId, @Valid @RequestBody BlockRequestDTO dto) {
        log.info("PATCH /cards/{}/unblock - Unblocking card with reason: {}", cardId, dto.getReason());
        cardService.unblockCard(cardId, dto.getReason());
        return ResponseEntity.noContent().build();
    }
}
