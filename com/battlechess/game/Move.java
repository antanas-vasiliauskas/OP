package game;

import java.io.Serializable;

import pieces.Coordinates;
/**
 * This class stores all the data defining a chess move.
 */
public class Move implements Serializable, Cloneable{
    public Coordinates beforeCords;
    public Coordinates afterCords;
    public String pieceType;
    public boolean isCapture;
    public boolean isCheck;
    public float eval;
    public Move(Coordinates beforeCords, Coordinates afterCords, String pieceType, boolean isCapture, boolean isCheck){
        this.beforeCords = beforeCords;
        this.afterCords = afterCords;
        this.pieceType = pieceType;
        this.isCapture = isCapture;
        this.isCheck = isCheck;
    }
    
    @Override
    public Move clone() throws CloneNotSupportedException {
        Move clonedMove = (Move) super.clone();
        clonedMove.beforeCords= this.beforeCords.clone();
        clonedMove.afterCords= this.afterCords.clone();
        return clonedMove;
    }
}