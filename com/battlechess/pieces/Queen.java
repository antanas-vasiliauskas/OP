package pieces;
import java.util.ArrayList;
import game.GameState;


public class Queen extends Piece {
    public Queen(int row, int column, boolean isWhite){
        super(row, column, isWhite);
    }

    @Override
    public String getPieceName() {
        return "QUEEN";
    }

    @Override
    public String toString() {
        return "Q" + getRowLetter() + getColumn();
    }

    @Override
    public ArrayList<Coordinates> getPossibleMoves(GameState gameState, boolean checkSafetyOn) {
        ArrayList<Coordinates> possibleMoves = new ArrayList<>(); 
        return possibleMoves;
    }
}
