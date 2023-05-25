package pieces;
import java.util.ArrayList;
import game.GameState;


/**
 * Extended Piece class defining chess Rook behavior.
 */
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
    public boolean move(Coordinates moveTo, GameState gameState) {
        if(getColumn() == 1){
            // long castle
            gameState.canLongCastle[isWhite()?0:1] = false;
        }
        else if(getColumn() == 8){
            // short castle
            gameState.canShortCastle[isWhite()?0:1] = false;
        }
        return super.move(moveTo, gameState);
    };

    @Override
    public ArrayList<Coordinates> getPossibleMoves(GameState gameState, boolean checkSafetyOn) {
        ArrayList<Coordinates> possibleMoves = new ArrayList<>(); 
        for(int i = getRow()+1; i < 9; i++){
            Coordinates cords = new Coordinates(i, getColumn());
            if(getPieceOnSquare(cords, gameState) == null){
                possibleMoves.add(cords);
            }
            else if(getPieceOnSquare(cords, gameState).isWhite() != isWhite()){
                possibleMoves.add(cords);
                break;
            }
            else break;
        }
        for(int i = getRow()-1; i > 0; i--){
            Coordinates cords = new Coordinates(i, getColumn());
            if(getPieceOnSquare(cords, gameState) == null){
                possibleMoves.add(cords);
            }
            else if(getPieceOnSquare(cords, gameState).isWhite() != isWhite()){
                possibleMoves.add(cords);
                break;
            }
            else break;
        }
        for(int i = getColumn()+1; i < 9; i++){
            Coordinates cords = new Coordinates(getRow(), i);
            if(getPieceOnSquare(cords, gameState) == null){
                possibleMoves.add(cords);
            }
            else if(getPieceOnSquare(cords, gameState).isWhite() != isWhite()){
                possibleMoves.add(cords);
                break;
            }
            else break;
        }
        for(int i = getColumn()-1; i > 0; i--){
            Coordinates cords = new Coordinates(getRow(), i);
            if(getPieceOnSquare(cords, gameState) == null){
                possibleMoves.add(cords);
            }
            else if(getPieceOnSquare(cords, gameState).isWhite() != isWhite()){
                possibleMoves.add(cords);
                break;
            }
            else break;
        }
        
        ArrayList<Coordinates> toRemove = new ArrayList<>();
        for(Coordinates move: possibleMoves){
            if(checkSafetyOn && GameState.isInCheck(this, move, gameState))
                toRemove.add(move);
        }
        for(Coordinates move: toRemove){
            possibleMoves.remove(move);
        }
        return possibleMoves;
    }
}
