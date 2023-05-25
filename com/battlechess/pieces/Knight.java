package pieces;
import java.util.ArrayList;
import game.GameState;


/**
 * Extended Piece class defining chess Knight behavior.
 */
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
        possibleMoves.add(new Coordinates(getRow() + 1, getColumn() + 2));
        possibleMoves.add(new Coordinates(getRow() + 1, getColumn() - 2));
        possibleMoves.add(new Coordinates(getRow() - 1, getColumn() + 2));
        possibleMoves.add(new Coordinates(getRow() - 1, getColumn() - 2));
        possibleMoves.add(new Coordinates(getRow() + 2, getColumn() + 1));
        possibleMoves.add(new Coordinates(getRow() + 2, getColumn() - 1));
        possibleMoves.add(new Coordinates(getRow() - 2, getColumn() + 1));
        possibleMoves.add(new Coordinates(getRow() - 2, getColumn() - 1));
    
        ArrayList<Coordinates> toRemove = new ArrayList<>();
        for(Coordinates move: possibleMoves){
            if(GameState.cordsOutOfBounds(move) || (getPieceOnSquare(move, gameState) != null && getPieceOnSquare(move, gameState).isWhite() == isWhite()) || (checkSafetyOn && GameState.isInCheck(this, move, gameState))){
                toRemove.add(move);
            }
        }
        for(Coordinates move: toRemove){
            possibleMoves.remove(move);
        }
        return possibleMoves;
    }
}
