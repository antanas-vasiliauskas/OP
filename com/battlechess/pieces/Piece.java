package pieces;
import java.util.ArrayList;

import game.GameState;
import game.Move;


abstract public class Piece implements Cloneable{
    
    private Coordinates coordinates; 
    private boolean white = true;

    @Override
    public Piece clone() throws CloneNotSupportedException {
        Piece clonedPiece = (Piece) super.clone();
        //clonedPiece.coordinates = this.coordinates.clone();
        return clonedPiece;
    }

    private static int piecesOnBoard = 0;
    public static int getPiecesOnBoard(){
        return piecesOnBoard;
    }

    // laukai, kuriems priega užtikrinama get/set metodais. Bent vienas laukas turi būti inicijuotas pradine reikšme.

    public int getRow(){
        return coordinates.row;
    }

    public int getColumn() {
        return coordinates.column;
    }

    public void setRow(int row) {
        this.coordinates.row = row;
    }
    public void setColumn(int column) {
        this.coordinates.column = column;
    }

    public Coordinates getCoordinates(){
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }
 
    public boolean isWhite() {
        return white;
    }
    public void setWhite(boolean white) {
        this.white = white;
    }

    // konstruktoriai, iš kurių vieną beargumentis, panaudoti this() konstrukciją
    public Piece(){
        this(4, 4 , true);
    }
    public Piece(int row, int column, boolean isWhite){
        piecesOnBoard++;
        this.coordinates = new Coordinates(row, column);
        this.white = isWhite;
    }

    // (nestatinius) metodus. Bent vienas metodas turi būti perkrautas (overloaded)
    public char getRowLetter(){
        return (char)(this.coordinates.row + 'a' - 1);
    }

    public boolean move(Coordinates moveTo, GameState gameState){
        
        boolean isCapture = false;
        gameState.isWhiteToMove = !gameState.isWhiteToMove;
        Piece p = getPieceOnSquare(moveTo, gameState);
        if(p != null){
            isCapture = true;
            gameState.pieces.remove(p);
            if(p.isWhite() && p.getRow() == 1 && p.getColumn() == 1 && p.getPieceName() == "ROOK")
                gameState.canLongCastle[0] = false;
            else if(p.isWhite() && p.getRow() == 1 && p.getColumn() == 8 && p.getPieceName() == "ROOK")
                gameState.canShortCastle[0] = false;
            else if(!p.isWhite() && p.getRow() == 8 && p.getColumn() == 1 && p.getPieceName() == "ROOK")
                gameState.canLongCastle[1] = false;
            else if(!p.isWhite() && p.getRow() == 8 && p.getColumn() == 8 && p.getPieceName() == "ROOK")
                gameState.canShortCastle[1] = false;
            

        }
        else if(getPieceName() == "PAWN" && getColumn() != moveTo.column){
            isCapture = true;
            // en passant
            Coordinates coo = gameState.moves.get(gameState.moves.size() - 2).afterCords;
            gameState.pieces.remove(getPieceOnSquare(coo, gameState));
        }
        int tempRow = getRow();
        int tempColumn = getColumn();
        setRow(moveTo.row);
        setColumn(moveTo.column);
        gameState.moves.add(new Move(new Coordinates(tempRow, tempColumn), moveTo, getPieceName(), isCapture, GameState.isInCheck(gameState)));
        return true;
    }    
    

    public abstract String getPieceName();

    public abstract ArrayList<Coordinates> getPossibleMoves(GameState gameState, boolean checkSafetyOn);

    public static final Piece getPieceOnSquare(Coordinates coordinates, GameState gameState){
        for(Piece piece: gameState.pieces){
            if(piece.getRow() == coordinates.row && piece.getColumn() == coordinates.column)
                return piece;
        }
        return null;
    }
}