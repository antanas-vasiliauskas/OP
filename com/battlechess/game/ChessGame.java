package game;
import java.util.ArrayList;
import pieces.*;

public class ChessGame {
    public static void main(String[] args) {
        ArrayList<Piece> pieces = new ArrayList<>();
        PieceFactory pieceFactory = new PieceFactory();
        
        
        pieces.add(pieceFactory.createPiece("ROOK", 1, 1, true));
        pieces.add(pieceFactory.createPiece("KNIGHT", 1, 2, true));
        pieces.add(pieceFactory.createPiece("BISHOP", 1, 3, true));
        pieces.add(pieceFactory.createPiece("QUEEN", 1, 4, true));
        pieces.add(pieceFactory.createPiece("KING", 1, 5, true));
        pieces.add(pieceFactory.createPiece("BISHOP", 1, 6, true));
        pieces.add(pieceFactory.createPiece("KNIGHT", 1, 7, true));
        pieces.add(pieceFactory.createPiece("ROOK", 1, 8, true));

        pieces.add(pieceFactory.createPiece("PAWN", 2, 1, true));
        pieces.add(pieceFactory.createPiece("PAWN", 2, 2, true));
        pieces.add(pieceFactory.createPiece("PAWN", 2, 3, true));
        pieces.add(pieceFactory.createPiece("PAWN", 2, 4, true));
        pieces.add(pieceFactory.createPiece("PAWN", 2, 5, true));
        pieces.add(pieceFactory.createPiece("PAWN", 2, 6, true));
        pieces.add(pieceFactory.createPiece("PAWN", 2, 7, true));
        pieces.add(pieceFactory.createPiece("PAWN", 2, 8, true));

        pieces.add(pieceFactory.createPiece("ROOK", 8, 1, false));
        pieces.add(pieceFactory.createPiece("KNIGHT", 8, 2, false));
        pieces.add(pieceFactory.createPiece("BISHOP", 8, 3, false));
        pieces.add(pieceFactory.createPiece("QUEEN", 8, 4, false));
        pieces.add(pieceFactory.createPiece("KING", 8, 5, false));
        pieces.add(pieceFactory.createPiece("BISHOP", 8, 6, false));
        pieces.add(pieceFactory.createPiece("KNIGHT", 8, 7, false));
        pieces.add(pieceFactory.createPiece("ROOK", 8, 8, false));

        pieces.add(pieceFactory.createPiece("PAWN", 7, 1, false));
        pieces.add(pieceFactory.createPiece("PAWN", 7, 2, false));
        pieces.add(pieceFactory.createPiece("PAWN", 7, 3, false));
        pieces.add(pieceFactory.createPiece("PAWN", 7, 4, false));
        pieces.add(pieceFactory.createPiece("PAWN", 7, 5, false));
        pieces.add(pieceFactory.createPiece("PAWN", 7, 6, false));
        pieces.add(pieceFactory.createPiece("PAWN", 7, 7, false));
        pieces.add(pieceFactory.createPiece("PAWN", 7, 8, false));


        

    }
}
