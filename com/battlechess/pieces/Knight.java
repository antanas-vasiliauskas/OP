package battlechess.pieces;
import java.util.ArrayList;

public class Knight extends Piece {
    @Override
    public boolean move(int newColumn, int newRow, ArrayList<Piece> pieces) {
        if(!super.move(newColumn, newRow, pieces))
            return false;
        
        if(
            (getColumn() == newColumn + 2 && getRow() == newRow + 1) || 
            (getColumn() == newColumn + 2 && getRow() == newRow - 1) || 
            (getColumn() == newColumn + 1 && getRow() == newRow + 2) || 
            (getColumn() == newColumn + 1 && getRow() == newRow - 2) || 
            (getColumn() == newColumn - 1 && getRow() == newRow + 2) || 
            (getColumn() == newColumn - 1 && getRow() == newRow - 2) || 
            (getColumn() == newColumn - 2 && getRow() == newRow + 1) || 
            (getColumn() == newColumn - 2 && getRow() == newRow - 1) 
        ){
            setRow(newRow);
            setColumn(newColumn);
            return true;
        }

        return false;
    }

    @Override
    public String getPieceName() {
        return "Knight";
    }

    @Override
    public String toString() {
        return "K" + getRowLetter() + getColumn();
    }
}
