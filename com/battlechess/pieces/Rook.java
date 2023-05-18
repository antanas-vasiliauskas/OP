package pieces;
import java.util.ArrayList;
import game.GameState;


public class Rook extends Piece {

    public Rook(int row, int column, boolean isWhite){
        super(row, column, isWhite);
    }


    @Override
    public String getPieceName() {
        return "ROOK";
    }

    @Override
    public String toString() {
        return "R" + getRowLetter() + getColumn();
    }

    @Override
    public ArrayList<Coordinates> getPossibleMoves(GameState gameState, boolean checkSafetyOn) {
        ArrayList<Coordinates> possibleMoves = new ArrayList<>(); 
        return possibleMoves;
    }
}
