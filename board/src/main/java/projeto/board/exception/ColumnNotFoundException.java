package projeto.board.exception;

public class ColumnNotFoundException extends RuntimeException {
    public ColumnNotFoundException(Long columnId) {
        super("Column not found with id: " + columnId);
    }

    public ColumnNotFoundException() {
        super("Column not found");
    }
}
