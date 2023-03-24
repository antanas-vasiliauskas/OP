package battlechess.pieces;
import java.util.ArrayList;

public class Pawn extends Piece {
    @Override
    public boolean move(int newColumn, int newRow, ArrayList<Piece> pieces) {
        if(!super.move(newColumn, newRow, pieces))
            return false;
        
            if(isWhite() && getColumn() == newColumn && (getRow() == newRow + 1) || getRow() == newRow + 2){
                setRow(newRow);
                setColumn(newColumn);
                return true;
            }
            else if(!isWhite() && getColumn() == newColumn && (getRow() == newRow - 1) || getRow() == newRow - 2){
                setRow(newRow);
                setColumn(newColumn);
                return true;
            }

        return false;
    }

    @Override
    public String getPieceName() {
        return "Pawn";
    }

    @Override
    public String toString() {
        return "" + getRowLetter() + getColumn();
    }
}
