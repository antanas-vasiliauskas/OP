package pieces;
import java.util.ArrayList;
import game.GameState;


public class Knight extends Piece {

    public Knight(int row, int column, boolean isWhite){
        super(row, column, isWhite);
    }

    @Override
    public String getPieceName() {
        return "KNIGHT";
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
