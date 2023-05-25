package pieces;
import java.util.ArrayList;
import game.GameState;



/**
 * Extended Piece class defining chess King behavior.
 */
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
    public boolean move(Coordinates moveTo, GameState gameState) {
        gameState.canShortCastle[isWhite()?0:1] = false;
        gameState.canLongCastle[isWhite()?0:1] = false;
        if(Math.abs(moveTo.column-getColumn()) > 1){
            // Castle.
            if(moveTo.column == 7 && moveTo.row == 1){
                // White short
                getPieceOnSquare(new Coordinates(1, 8), gameState).setColumn(6);
            }
            else if(moveTo.column == 3 && moveTo.row == 1){
                // White long
                getPieceOnSquare(new Coordinates(1, 1), gameState).setColumn(4);
            }
            else if(moveTo.column == 7 && moveTo.row == 8){
                // Black short
                getPieceOnSquare(new Coordinates(8, 8), gameState).setColumn(6);
            }
            else{
                // Black long
                getPieceOnSquare(new Coordinates(8, 1), gameState).setColumn(4);
            }
        }
        return super.move(moveTo, gameState);
    };

    @Override
    public ArrayList<Coordinates> getPossibleMoves(GameState gameState, boolean checkSafetyOn) {
        ArrayList<Coordinates> possibleMoves = new ArrayList<>();
        possibleMoves.add(new Coordinates(getRow()    , getColumn() + 1));
        possibleMoves.add(new Coordinates(getRow() + 1, getColumn() + 1));
        possibleMoves.add(new Coordinates(getRow() - 1, getColumn() + 1));
        possibleMoves.add(new Coordinates(getRow() + 1, getColumn()    ));
        possibleMoves.add(new Coordinates(getRow() - 1, getColumn()    ));
        possibleMoves.add(new Coordinates(getRow()    , getColumn() - 1));
        possibleMoves.add(new Coordinates(getRow() + 1, getColumn() - 1));
        possibleMoves.add(new Coordinates(getRow() - 1, getColumn() - 1));

        // Castle
        if(gameState.canShortCastle[isWhite()?0:1] 
        && getPieceOnSquare(new Coordinates(getRow(), getColumn() + 1), gameState) == null && getPieceOnSquare(new Coordinates(getRow(), getColumn() + 2), gameState) == null
        && (!checkSafetyOn || !GameState.isSquareUnderAttack(new Coordinates(getRow(), getColumn() + 1), gameState, !isWhite()) ) 
        && (!checkSafetyOn || !GameState.isSquareUnderAttack(new Coordinates(getRow(), getColumn() + 2), gameState, !isWhite()) )){
            possibleMoves.add(new Coordinates(getRow(), getColumn() + 2));
        }

        if(gameState.canLongCastle[isWhite()?0:1] 
        && getPieceOnSquare(new Coordinates(getRow(), getColumn() - 1), gameState) == null && getPieceOnSquare(new Coordinates(getRow(), getColumn() - 2), gameState) == null && getPieceOnSquare(new Coordinates(getRow(), getColumn() - 3), gameState) == null
        && (!checkSafetyOn || !GameState.isSquareUnderAttack(new Coordinates(getRow(), getColumn() - 1), gameState, !isWhite()) ) 
        && (!checkSafetyOn || !GameState.isSquareUnderAttack(new Coordinates(getRow(), getColumn() - 2), gameState, !isWhite()) )){
            possibleMoves.add(new Coordinates(getRow(), getColumn() - 2));
        }
    
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
