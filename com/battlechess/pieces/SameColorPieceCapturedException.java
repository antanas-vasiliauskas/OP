package pieces;



/**
 * Extended InvalidMoveException class defining custom behavior, when Piece.move(...)
 * tries to capture same color piece.
 */
public class SameColorPieceCapturedException extends InvalidMoveException {

    private Piece pieceOnDestinationSquare;

    public SameColorPieceCapturedException(String message, Piece pieceOnDestinationSquare) {
        super(message);
        this.pieceOnDestinationSquare = pieceOnDestinationSquare;
    }

    public Piece getPieceOnDestinationSquare() {
        return pieceOnDestinationSquare;
    }
}