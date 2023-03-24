package battlechess.pieces;
import java.util.ArrayList;

public class King extends Piece {
    private boolean onCheck = false;
    private boolean moved = false;

    public void setMoved(boolean moved){
        this.moved = moved;
    }
    public boolean isMoved(){
        return moved;
    }

    public void setOnCheck(boolean onCheck){
        this.onCheck = onCheck;
    }
    public boolean isOnCheck(){
        return this.onCheck;
    }

    @Override
    public boolean move(int newColumn, int newRow, ArrayList<Piece> pieces) {
        if(!super.move(newColumn, newRow, pieces))
            return false;
        
        int deltaRow = Math.abs(getRow() - newRow);
        int deltaColumn = Math.abs(getColumn() - newColumn);
        if(deltaRow <= 1 && deltaColumn <= 1){
            setRow(newRow);
            setColumn(newColumn);
            return true;
        }

        return false;
    }

    @Override
    public String getPieceName() {
        return "King";
    }

    @Override
    public String toString() {
        return "K" + getRowLetter() + getColumn();
    }
}
