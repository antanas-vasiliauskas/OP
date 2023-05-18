package game;

import pieces.Coordinates;

public class Move{
    public Coordinates beforeCords;
    public Coordinates afterCords;
    public String pieceType;
    public boolean isCapture;
    public boolean isCheck;
    public Move(Coordinates beforeCords, Coordinates afterCords, String pieceType, boolean isCapture, boolean isCheck){
        this.beforeCords = beforeCords;
        this.afterCords = afterCords;
        this.pieceType = pieceType;
        this.isCapture = isCapture;
    }
}