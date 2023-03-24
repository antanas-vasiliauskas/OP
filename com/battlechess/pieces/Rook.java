package battlechess.pieces;
import java.util.ArrayList;

public class Rook extends Piece {

    private boolean moved = false;

    public void setMoved(boolean moved){
        this.moved = moved;
    }
    public boolean isMoved(){
        return moved;
    }

    @Override
    public boolean move(int newColumn, int newRow, ArrayList<Piece> pieces) {
        if(!super.move(newColumn, newRow, pieces))
            return false;
        
        if(getColumn() == newColumn ^ getRow() == newRow){
            setRow(newRow);
            setColumn(newColumn);
            return true;
        }

        return false;
    }

    @Override
    public String getPieceName() {
        return "Rook";
    }

    @Override
    public String toString() {
        return "R" + getRowLetter() + getColumn();
    }
}
