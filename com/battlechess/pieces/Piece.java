package battlechess.pieces;
import java.util.ArrayList;

abstract public class Piece {

    private static int piecesOnBoard = 0;
    public static int getPiecesOnBoard(){
        return piecesOnBoard;
    }

    // laukai, kuriems priega užtikrinama get/set metodais. Bent vienas laukas turi būti inicijuotas pradine reikšme.
    private int row;
    private int column; 
    private boolean white = true;

    public int getRow(){
        return row;
    }
    public int getColumn() {
        return column;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setColumn(int column) {
        this.column = column;
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
    public Piece(int row, int collumn, boolean isWhite){
        piecesOnBoard++;
        this.row = row;
        this.column = collumn;
        this.white = isWhite;
    }

    // Apibrėžti metodą println(), kuris išveda objekto turinį į išvedimo srautą
    public void println(){
        System.out.println("type: " + getPieceName() + ", column: " + getColumn() + ", row: " + getRow() + ", isWhite: " + white);
    }

    // (nestatinius) metodus. Bent vienas metodas turi būti perkrautas (overloaded)
    public char getRowLetter(){
        return (char)(this.row + 'a' - 1);
    }

    public final boolean move(int deltaRow, int deltaCollumn, boolean deltaUp, boolean deltaLeft, ArrayList<Piece> pieces){
        int newCollumn = this.column + (deltaUp ? deltaCollumn : -deltaCollumn);
        int newRow = this.column + (deltaLeft ? deltaRow : -deltaRow);
        return move(newCollumn, newRow, pieces);
    }

    public boolean move(int newColumn, int newRow, ArrayList<Piece> pieces){
        if(newColumn > 8 || newColumn < 1 || newRow > 8 || newRow < 1){
            return false;
        }

        for(Piece piece: pieces){
            if(piece.white == this.white && piece.column == newColumn && piece.row == newRow)
                return false;
        }
        return true;
    }

    public abstract String getPieceName();

}