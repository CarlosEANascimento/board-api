package projeto.board.services;

import projeto.board.dto.CardRequestDTO;
import projeto.board.model.Card;
import projeto.board.model.CardBlocked;

import java.util.List;

public interface CardService {
    Card createCard(Long boardId, CardRequestDTO dto);
    List<CardBlocked> getCardBlockHistory(Long cardId);
    void moveCardToNextColumn(Long cardId);
    void cancelCard(Long cardId);
    void blockCard(Long cardId, String reason);
    void unblockCard(Long cardId, String reason);
}