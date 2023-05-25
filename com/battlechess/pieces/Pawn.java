package pieces;
import java.util.ArrayList;
import game.GameState;


/**
 * Extended Piece class defining chess Pawn behavior.
 */
public class Pawn extends Piece {
    public Pawn(int row, int column, boolean isWhite){
        super(row, column, isWhite);
    }

    @Override
    public String getPieceName() {
        return "PAWN";
    }
    @Override
    public boolean move(Coordinates moveTo, GameState gameState) {
        boolean b = super.move(moveTo, gameState);
        if((moveTo.row == 8 && isWhite()) || (moveTo.row == 1 && !isWhite())){
            gameState.pieces.add(new Queen(moveTo.row, moveTo.column, isWhite()));
            gameState.pieces.remove(this);
        }
        return b;
    };

    @Override
    public String toString() {
        return "" + getRowLetter() + getColumn();
    }

    @Override
    public ArrayList<Coordinates> getPossibleMoves(GameState gameState, boolean checkSafetyOn) {
        ArrayList<Coordinates> possibleMoves = new ArrayList<>();
        Coordinates cord1 = new Coordinates(getRow() + (isWhite() ? 1: -1), getColumn());
        if(getPieceOnSquare(cord1, gameState) == null && !GameState.cordsOutOfBounds(cord1)){
            if(!checkSafetyOn || !GameState.isInCheck(this, cord1, gameState))
                possibleMoves.add(cord1);
        }
        Coordinates cord2 = new Coordinates(getRow() + (isWhite() ? 2: -2), getColumn());
        if(getPieceOnSquare(cord2, gameState) == null && ((isWhite() && getRow() == 2) || (!isWhite() && getRow() == 7)) && !GameState.cordsOutOfBounds(cord2) 
        && getPieceOnSquare(cord1, gameState) == null){
            if(!checkSafetyOn || !GameState.isInCheck(this, cord2, gameState))
                possibleMoves.add(cord2);
        }

        Coordinates cord3 = new Coordinates(getRow() + (isWhite() ? 1: -1), getColumn() + 1);
        Coordinates cord4 = new Coordinates(getRow() + (isWhite() ? 1: -1), getColumn() - 1);

        Piece p = getPieceOnSquare(cord3, gameState);
        if(p != null && p.isWhite() != isWhite() && !GameState.cordsOutOfBounds(cord3)){
            if(!checkSafetyOn || !GameState.isInCheck(this, cord3, gameState))
                possibleMoves.add(cord3);
        }
        p = getPieceOnSquare(cord4, gameState);
        if(p != null && p.isWhite() != isWhite() && !GameState.cordsOutOfBounds(cord4)){
            if(!checkSafetyOn || !GameState.isInCheck(this, cord4, gameState))
                possibleMoves.add(cord4);
        }

        if(((getRow() == 5 && isWhite()) || (getRow() == 4 && !isWhite())) && gameState.moves.get(gameState.moves.size() - 1).pieceType == "PAWN" 
        && gameState.moves.get(gameState.moves.size() - 1).beforeCords.column == getColumn() + 1 && gameState.moves.get(gameState.moves.size() - 1).beforeCords.row == (isWhite() ? 7 : 2)
        && gameState.moves.get(gameState.moves.size() - 1).afterCords.column == getColumn() + 1 && gameState.moves.get(gameState.moves.size() - 1).afterCords.row == (isWhite() ? 5 : 4)
        && !GameState.cordsOutOfBounds(cord3)){
            if(!checkSafetyOn || !GameState.isInCheck(this, cord3, gameState))
                possibleMoves.add(cord3);
        }
        
        if(((getRow() == 5 && isWhite()) || (getRow() == 4 && !isWhite())) && gameState.moves.get(gameState.moves.size() - 1).pieceType == "PAWN" 
        && gameState.moves.get(gameState.moves.size() - 1).beforeCords.column == getColumn() - 1 && gameState.moves.get(gameState.moves.size() - 1).beforeCords.row == (isWhite() ? 7 : 2)
        && gameState.moves.get(gameState.moves.size() - 1).afterCords.column == getColumn() - 1 && gameState.moves.get(gameState.moves.size() - 1).afterCords.row == (isWhite() ? 5 : 4)
        && !GameState.cordsOutOfBounds(cord4)){
            if(!checkSafetyOn || !GameState.isInCheck(this, cord4, gameState)){
                possibleMoves.add(cord4);  
            }
        }

        return possibleMoves;
    }
}
