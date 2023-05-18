package pieces;

import java.util.ArrayList;
import game.GameState;

public class Bishop extends Piece {
    public Bishop(int row, int column, boolean isWhite){
        super(row, column, isWhite);
    }

    @Override
    public String getPieceName() {
        return "BISHOP";
    }

    @Override
    public String toString() {
        return "B" + getRowLetter() + getColumn();
    }

    @Override
    public ArrayList<Coordinates> getPossibleMoves(GameState gameState, boolean checkSafetyOn) {
        ArrayList<Coordinates> possibleMoves = new ArrayList<>(); 
        return possibleMoves;
    }
}
