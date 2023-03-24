package battlechess.game;
import java.util.ArrayList;

public class ChessGame {
    public static void main(String[] args) {
        ArrayList<Piece> pieces = new ArrayList<>();

        Piece.getPiecesOnBoard();

        pieces.add(new Rook(Piece.ROOK, 1, 1, true));
        pieces.add(new Knight(Piece.KNIGHT, 1, 2, true));
        pieces.add(new Bishop(Piece.BISHOP, 1, 3, true));
        pieces.add(new King(Piece.KING, 1, 4, true));
        pieces.add(new Queen(Piece.QUEEN, 1, 5, true));
        pieces.add(new Bishop(Piece.BISHOP, 1, 6, true));
        pieces.add(new Knight(Piece.KNIGHT, 1, 7, true));
        pieces.add(new Rook(Piece.ROOK, 1, 8, true));

        pieces.add(new Pawn(Piece.PAWN, 2, 1, true));
        pieces.add(new Pawn(Piece.PAWN, 2, 2, true));
        pieces.add(new Pawn(Piece.PAWN, 2, 3, true));
        pieces.add(new Pawn(Piece.PAWN, 2, 4, true));
        pieces.add(new Pawn(Piece.PAWN, 2, 5, true));
        pieces.add(new Pawn(Piece.PAWN, 2, 6, true));
        pieces.add(new Pawn(Piece.PAWN, 2, 7, true));
        pieces.add(new Pawn(Piece.PAWN, 2, 8, true));

        pieces.add(new Rook(Piece.ROOK, 8, 1, false));
        pieces.add(new Knight(Piece.KNIGHT, 8, 2, false));
        pieces.add(new Bishop(Piece.BISHOP, 8, 3, false));
        pieces.add(new King(Piece.KING, 8, 4, false));
        pieces.add(new Queen(Piece.QUEEN, 8, 5, false));
        pieces.add(new Bishop(Piece.BISHOP, 8, 6, false));
        pieces.add(new Knigh(Piece.KNIGHT, 8, 7, false));
        pieces.add(new Rook(Piece.ROOK, 8, 8, false));

        pieces.add(new Pawn(Piece.PAWN, 7, 1, false));
        pieces.add(new Pawn(Piece.PAWN, 7, 2, false));
        pieces.add(new Pawn(Piece.PAWN, 7, 3, false));
        pieces.add(new Pawn(Piece.PAWN, 7, 4, false));
        pieces.add(new Pawn(Piece.PAWN, 7, 5, false));
        pieces.add(new Pawn(Piece.PAWN, 7, 6, false));
        pieces.add(new Pawn(Piece.PAWN, 7, 7, false));
        pieces.add(new Pawn(Piece.PAWN, 7, 8, false));

        pieces[10].move(5, 10, pieces);
        for(Piece piece : pieces){
            piece.println();
        }

    }
}
