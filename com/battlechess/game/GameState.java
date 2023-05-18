package game;

import java.util.ArrayList;

import pieces.*;

public class GameState {
    public ArrayList<Piece> pieces;
    public boolean isWhiteToMove = true;
    public boolean whiteCanCastle = true;
    public boolean blackCanCastle = true;
    public ArrayList<Move> moves;
    public GameState(){
        pieces = new ArrayList<Piece>();
        moves = new ArrayList<Move>();
        PieceFactory pieceFactory = new PieceFactory();
        pieces.add(pieceFactory.createPiece("ROOK", 1, 1, true));
        pieces.add(pieceFactory.createPiece("KNIGHT", 1, 2, true));
        pieces.add(pieceFactory.createPiece("BISHOP", 1, 3, true));
        pieces.add(pieceFactory.createPiece("QUEEN", 1, 4, true));
        pieces.add(pieceFactory.createPiece("KING", 1, 5, true));
        pieces.add(pieceFactory.createPiece("BISHOP", 1, 6, true));
        pieces.add(pieceFactory.createPiece("KNIGHT", 1, 7, true));
        pieces.add(pieceFactory.createPiece("ROOK", 1, 8, true));

        pieces.add(pieceFactory.createPiece("PAWN", 2, 1, true));
        pieces.add(pieceFactory.createPiece("PAWN", 2, 2, true));
        pieces.add(pieceFactory.createPiece("PAWN", 2, 3, true));
        pieces.add(pieceFactory.createPiece("PAWN", 2, 4, true));
        pieces.add(pieceFactory.createPiece("PAWN", 2, 5, true));
        pieces.add(pieceFactory.createPiece("PAWN", 2, 6, true));
        pieces.add(pieceFactory.createPiece("PAWN", 2, 7, true));
        pieces.add(pieceFactory.createPiece("PAWN", 2, 8, true));

        pieces.add(pieceFactory.createPiece("ROOK", 8, 1, false));
        pieces.add(pieceFactory.createPiece("KNIGHT", 8, 2, false));
        pieces.add(pieceFactory.createPiece("BISHOP", 8, 3, false));
        pieces.add(pieceFactory.createPiece("QUEEN", 8, 4, false));
        pieces.add(pieceFactory.createPiece("KING", 8, 5, false));
        pieces.add(pieceFactory.createPiece("BISHOP", 8, 6, false));
        pieces.add(pieceFactory.createPiece("KNIGHT", 8, 7, false));
        pieces.add(pieceFactory.createPiece("ROOK", 8, 8, false));

        pieces.add(pieceFactory.createPiece("PAWN", 7, 1, false));
        pieces.add(pieceFactory.createPiece("PAWN", 7, 2, false));
        pieces.add(pieceFactory.createPiece("PAWN", 7, 3, false));
        pieces.add(pieceFactory.createPiece("PAWN", 7, 4, false));
        pieces.add(pieceFactory.createPiece("PAWN", 7, 5, false));
        pieces.add(pieceFactory.createPiece("PAWN", 7, 6, false));
        pieces.add(pieceFactory.createPiece("PAWN", 7, 7, false));
        pieces.add(pieceFactory.createPiece("PAWN", 7, 8, false));
    }

    public static boolean isInCheck(Piece movingPiece, Coordinates moveToCords, GameState gameState){
        // piece moveToCords gamestate
        // Update piece cords temporarely
        // Get opposite color piece possibleMovesCords. Check if any of them have the same cords as this color king.
        Coordinates temp = movingPiece.getCoordinates();
        movingPiece.setCoordinates(moveToCords);
        Piece king = null;
        for(Piece piece: gameState.pieces){
            if(piece.isWhite() == gameState.isWhiteToMove && piece.getPieceName() == "KING"){
                king = piece;
                break;
            }
        }
        for(Piece piece: gameState.pieces){
            if(piece.isWhite() != gameState.isWhiteToMove){
                ArrayList<Coordinates> cords = piece.getPossibleMoves(gameState, false);
                for(Coordinates c: cords){
                    if(c.row == king.getRow() && c.column == king.getColumn()){
                        // is in check
                        movingPiece.setCoordinates(temp);
                        return true;
                    }
                        
                }
            }
        } 
        
        movingPiece.setCoordinates(temp);
        return false;
    }

    public static boolean cordsOutOfBounds(Coordinates cords){
        if(cords.row > 8 || cords.row < 1 || cords.column > 8 || cords.column < 1)
            return true;
        return false;
    }

    // moves_to_string
}


