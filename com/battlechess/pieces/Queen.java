package battlechess.pieces;
import java.util.ArrayList;

public class Queen extends Piece {
    @Override
    public boolean move(int newColumn, int newRow, ArrayList<Piece> pieces) {
        if(!super.move(newColumn, newRow, pieces))
            return false;
        
        int deltaRow = Math.abs(getRow() - newRow);
        int deltaColumn = Math.abs(getColumn() - newColumn);
        if((deltaRow == deltaColumn || deltaRow != 0) || (getColumn() == newColumn ^ getRow() == newRow)){
            setRow(newRow) ;
            setColumn(newColumn);
            return true;
        }
        return false;
    }

    @Override
    public String getPieceName() {
        return "Queen";
    }

    @Override
    public String toString() {
        return "Q" + getRowLetter() + getColumn();
    }
}
