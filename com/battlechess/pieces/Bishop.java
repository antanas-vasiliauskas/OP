package battlechess.pieces;

import java.util.ArrayList;

public class Bishop extends Piece {
    @Override
    public boolean move(int newColumn, int newRow, ArrayList<Piece> pieces) {
        if(!super.move(newColumn, newRow, pieces))
            return false;
        
        int deltaRow = Math.abs(getRow() - newRow);
        int deltaColumn = Math.abs(getColumn() - newColumn);
        if(deltaRow == deltaColumn || deltaRow != 0){
            setRow(newRow);
            setColumn(newColumn);
            return true;
        }

        return false;
    }

    @Override
    public String getPieceName() {
        return "Bishop";
    }

    @Override
    public String toString() {
        return "B" + getRowLetter() + getColumn();
    }
}
