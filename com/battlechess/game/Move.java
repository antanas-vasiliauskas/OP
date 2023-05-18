package game;

import pieces.Coordinates;

public class Move{
    public Coordinates beforeCords;
    public Coordinates afterCords;
    public String pieceType;
    public Move(Coordinates beforeCords, Coordinates afterCords, String pieceType){
        this.beforeCords = beforeCords;
        this.afterCords = afterCords;
        this.pieceType = pieceType;
    }
}