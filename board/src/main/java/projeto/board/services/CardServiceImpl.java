package projeto.board.services;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.board.dto.CardRequestDTO;
import projeto.board.exception.BoardNotFoundException;
import projeto.board.exception.CardNotFoundException;
import projeto.board.exception.ColumnNotFoundException;
import projeto.board.model.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CardServiceImpl implements CardService{
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardBlockedRepository cardBlockedRepository;

    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Override
    @Transactional
    public Card createCard(Long boardId, CardRequestDTO dto) {
        log.info("Creating card with title: {} in board: {}", dto.getTitle(), boardId);

        Board board = boardRepository.findById(boardId).orElseThrow(() -> {
            log.warn("Boar not found with id: {}", boardId);
            return new BoardNotFoundException(boardId);
        });

        Columns initialColumn = board.getColumns().stream()
            .filter(c -> c.getStatus() == Status.INICIAL)
            .findFirst()
            .orElseThrow(() -> {
                log.error("Initial column not found in board: {}", boardId);
                return new ColumnNotFoundException();
            });

        Card card = new Card();

        card.setTitle(dto.getTitle());
        card.setDescription(dto.getDescription());
        card.setBlocked(false);
        card.setColumn(initialColumn);
        initialColumn.getCards().add(card);

        Card saved = cardRepository.save(card);
        log.info("Card created successfully with id: {}", saved.getId());
        return saved;
    }

    @Override
    public List<CardBlocked> getCardBlockHistory(Long cardId) {
        log.debug("Fetching block in history for card: {}", cardId);

        Card card = cardRepository.findById(cardId).orElseThrow(() -> {
            log.warn("Card not found with id: {}", cardId);
            return new CardNotFoundException(cardId);
        });

        List<CardBlocked> history = cardBlockedRepository.findByCard_IdOrderByBlockedAtAsc(card.getId());
        log.info("Found {} block history records for card: {}", history.size(), cardId);
        return history;
    }

    @Override
    @Transactional
    public void moveCardToNextColumn(Long cardId) {
        log.info("Moving card: {} to next column", cardId);

        Card card = cardRepository.findById(cardId).orElseThrow(() -> {
            log.warn("Card not found with id: {}", cardId);
            return new CardNotFoundException(cardId);
        });

        Columns currentColumn = card.getColumn();

        if(currentColumn == null) {
            log.error("Card {} has no column assigned", cardId);
            throw new ColumnNotFoundException();
        }

        Integer nextPosition = currentColumn.getPosition() + 1;
        Columns nextColumn = columnRepository.findByPositionAndBoard(nextPosition, currentColumn.getBoard());

        if(nextColumn == null) {
            log.warn("Next column not found for position: {} in board: {}", nextPosition, currentColumn.getBoard().getId());
            throw new ColumnNotFoundException();
        }

        if(nextColumn.getStatus() != Status.CANCELADO) {
            card.setColumn(nextColumn);
            cardRepository.save(card);
            log.info("Card {} moved to column: {} ({})", cardId, nextColumn.getId(), nextColumn.getPosition());
        } else {
            log.warn("Attempted to move card {} to CANCELADO column, operation blocked", cardId);
        }
    }

    @Override
    @Transactional
    public void cancelCard(Long cardId) {
        log.info("Cancelling card: {}", cardId);

        Card card = cardRepository.findById(cardId).orElseThrow(() -> {
            log.warn("Card not found with id: {}", cardId);
            return new CardNotFoundException(cardId);
        });

        Board board = card.getColumn().getBoard();
        Columns cancelledColumn = columnRepository.findByStatusAndBoard(Status.CANCELADO, board);

        if(cancelledColumn == null) {
            log.error("Cancelled column not found in board: {}", board.getId());
            throw new ColumnNotFoundException();
        }

        card.setColumn(cancelledColumn);
        cardRepository.save(card);
        log.info("Card {} moved to CANCELADO column");
    }

    @Override
    @Transactional
    public void blockCard(Long cardId, String reason) {
        log.info("Blocking card: {} with reason: {}", cardId, reason);

        Card card = cardRepository.findById(cardId).orElseThrow(() -> {
            log.warn("Card not found with id: {}", cardId);
            return new CardNotFoundException(cardId);
        });

        card.setBlocked(true);
        cardRepository.save(card);

        CardBlocked cardBlocked = new CardBlocked();
        cardBlocked.setCard(card);
        cardBlocked.setBlockReason(reason);
        cardBlocked.setBlockedAt(LocalDateTime.now());

        cardBlockedRepository.save(cardBlocked);
        log.info("Card {} blocked successfully", cardId);
    }

    @Override
    public void unblockCard(Long cardId, String reason) {
        log.info("Unblocking card: {} with reason: {}", cardId, reason);

        Card card = cardRepository.findById(cardId).orElseThrow(() -> {
            log.warn("Card not found with id: {}", cardId);
            return new CardNotFoundException(cardId);
        });

        card.setBlocked(false);
        cardRepository.save(card);

        CardBlocked active = cardBlockedRepository.findByCard_IdAndUnblockedAtIsNull(cardId);

        if(active != null) {
            active.setUnblockedAt(LocalDateTime.now());
            active.setUnblockReason(reason);
            cardBlockedRepository.save(active);
            log.info("Card {} unblocked successfully", cardId);
        } else {
            log.warn("No active block found for card: {}", cardId);
        }
    }
}
