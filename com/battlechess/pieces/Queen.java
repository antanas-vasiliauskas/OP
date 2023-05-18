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
        for(int i = getRow()+1, j = getColumn()+1; i < 9 && j < 9; i++, j++){
            Coordinates cords = new Coordinates(i, j);
            if(getPieceOnSquare(cords, gameState) == null){
                possibleMoves.add(cords);
            }
            else if(getPieceOnSquare(cords, gameState).isWhite() != isWhite()){
                possibleMoves.add(cords);
                break;
            }
            else break;
        }
        for(int i = getRow()-1, j = getColumn()+1; i > 0 && j < 9; i--, j++){
            Coordinates cords = new Coordinates(i, j);
            if(getPieceOnSquare(cords, gameState) == null){
                possibleMoves.add(cords);
            }
            else if(getPieceOnSquare(cords, gameState).isWhite() != isWhite()){
                possibleMoves.add(cords);
                break;
            }
            else break;
        }
        for(int i = getRow()+1, j = getColumn()-1; i < 9 && j > 0; i++, j--){
            Coordinates cords = new Coordinates(i, j);
            if(getPieceOnSquare(cords, gameState) == null){
                possibleMoves.add(cords);
            }
            else if(getPieceOnSquare(cords, gameState).isWhite() != isWhite()){
                possibleMoves.add(cords);
                break;
            }
            else break;
        }
        for(int i = getRow()-1, j = getColumn()-1; i > 0 && j > 0; i--, j--){
            Coordinates cords = new Coordinates(i, j);
            if(getPieceOnSquare(cords, gameState) == null){
                possibleMoves.add(cords);
            }
            else if(getPieceOnSquare(cords, gameState).isWhite() != isWhite()){
                possibleMoves.add(cords);
                break;
            }
            else break;
        }
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
