package pieces;

/**
 * Class dedicated to creation of Piece objects implementing Factory Design Patern.
 */
public class PieceFactory {
    public Piece createPiece(String type, int row, int column, boolean isWhite){
        if(type == "KING"){
            return new King(row, column, isWhite);
        }
        else if(type == "QUEEN"){
            return new Queen(row, column, isWhite);
        }
        else if(type == "ROOK"){
            return new Rook(row, column, isWhite);
        }
        else if(type == "BISHOP"){
            return new Bishop(row, column, isWhite);
        }
        else if (type == "KNIGHT"){
            return new Knight(row, column, isWhite);
        }
        else if(type == "PAWN"){
            return new Pawn(row, column, isWhite);
        }
        else{
            return null;
        }
    }
}
