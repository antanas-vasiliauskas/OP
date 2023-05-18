package pieces;
import java.util.ArrayList;
import game.GameState;


public class King extends Piece {
    public King(int row, int column, boolean isWhite){
        super(row, column, isWhite);
    }

    @Override
    public String getPieceName() {
        return "KING";
    }

    @Override
    public String toString() {
        return "K" + getRowLetter() + getColumn();
    }

    @Override
    public ArrayList<Coordinates> getPossibleMoves(GameState gameState, boolean checkSafetyOn) {
        ArrayList<Coordinates> possibleMoves = new ArrayList<>(); 
        return possibleMoves;
    }
}
