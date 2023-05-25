package pieces;

import java.io.Serializable;


/**
 * Coordinates class for storing position on chess board.
 */
public class Coordinates implements Cloneable, Serializable {
    @Override
    public Coordinates clone() throws CloneNotSupportedException{
        return (Coordinates) super.clone();
    }

    public int row, column;
    public Coordinates(int row, int column){
        this.row = row;
        this.column = column;
    }
    
}
