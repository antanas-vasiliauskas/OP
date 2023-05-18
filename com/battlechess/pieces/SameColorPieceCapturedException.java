package pieces;


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