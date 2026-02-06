package projeto.board.exception;

public class CardBlockNotFoundException extends RuntimeException{
    public CardBlockNotFoundException(Long cardBlockId) {
        super("Card block not found with id: " + cardBlockId);
    }
}
