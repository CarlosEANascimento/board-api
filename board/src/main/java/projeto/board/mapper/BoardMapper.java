package projeto.board.mapper;

import org.springframework.stereotype.Component;
import projeto.board.dto.BoardResponseDTO;
import projeto.board.dto.CardBlockedResponseDTO;
import projeto.board.dto.CardResponseDTO;
import projeto.board.dto.ColumnsResponseDTO;
import projeto.board.model.Board;
import projeto.board.model.Card;
import projeto.board.model.CardBlocked;
import projeto.board.model.Columns;

@Component
public class BoardMapper {
    public BoardResponseDTO toBoardResponseDTO(Board board) {
        BoardResponseDTO dto = new BoardResponseDTO();

        dto.setId(board.getId());
        dto.setName(board.getName());
        dto.setColumnsResponses(board.getColumns().stream().map(this::toColumnsResponseDTO).toList());

        return dto;
    }

    public ColumnsResponseDTO toColumnsResponseDTO (Columns columns) {
        ColumnsResponseDTO dto = new ColumnsResponseDTO();

        dto.setId(columns.getId());
        dto.setName(columns.getName());
        dto.setPosition(columns.getPosition());
        dto.setStatus(columns.getStatus());
        dto.setCardResponseDTO(
                columns.getCards().stream().map(this::toCardResponseDTO).toList()
        );

        return dto;
    }

    public CardResponseDTO toCardResponseDTO(Card card) {
        CardResponseDTO dto = new CardResponseDTO();

        dto.setId(card.getId());
        dto.setTitle(card.getTitle());
        dto.setDescription(card.getDescription());
        dto.setBlocked(card.isBlocked());
        dto.setStatus(card.getColumn().getStatus());

        return dto;
    }

    public CardBlockedResponseDTO toCardBlockedResponseDTO(CardBlocked cardBlocked) {
        CardBlockedResponseDTO dto = new CardBlockedResponseDTO();

        dto.setBlockedAt(cardBlocked.getBlockedAt());
        dto.setBlockReason(cardBlocked.getBlockReason());
        dto.setUnblockedAt(cardBlocked.getUnblockedAt());
        dto.setUnblockReason(cardBlocked.getUnblockReason());

        return dto;
    }
}
