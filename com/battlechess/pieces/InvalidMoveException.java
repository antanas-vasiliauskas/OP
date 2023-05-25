package pieces;

import java.lang.Exception;


/**
 * Exception that is raised when Piece.move(..) method is called, but the move is illegal.
 */
public class InvalidMoveException extends Exception {
    public InvalidMoveException(String message) {
        super(message);
    }
}