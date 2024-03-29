

package game;

import java.io.Serializable;
import java.util.ArrayList;

import pieces.*;

/**
 * This class is where all the variables concerning game state are stored.
 * This class alone is sufficient to recreate the game from scratch.
 */
public class GameState implements Serializable, Cloneable{
    public ArrayList<Piece> pieces;
    public boolean isWhiteToMove = true;
    public ArrayList<Move> moves;
    public boolean[] canShortCastle = {true, true};
    public boolean[] canLongCastle = {true, true};
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

    @Override
    public GameState clone() throws CloneNotSupportedException {
        GameState clonedGameState = (GameState) super.clone();
        clonedGameState.pieces = new ArrayList<>();
        for(Piece piece: this.pieces){
            clonedGameState.pieces.add(piece.clone());
        }
            
        clonedGameState.moves = new ArrayList<>();
        for(Move m: this.moves)
            clonedGameState.moves.add(m.clone());
        clonedGameState.canLongCastle = this.canLongCastle.clone();
        clonedGameState.canShortCastle = this.canShortCastle.clone();
        return clonedGameState;
    }

    public int movesAvailable(){
        int nMoves = 0;
        for(int i = 0; i < pieces.size(); i++){
            if(pieces.get(i).isWhite() == isWhiteToMove)
                nMoves += pieces.get(i).getPossibleMoves(this, true).size();
        }
        return nMoves;
    }

    public ArrayList<Move> getAvailableMoves(){
        ArrayList<Move> moves = new ArrayList<>();
        for(int i = 0; i < pieces.size(); i++){  
            Piece piece = pieces.get(i); // Access by index - AL = O(1), LL = O(n)
            if(piece.isWhite() == isWhiteToMove)
                for(Coordinates coo: piece.getPossibleMoves(this, true)){
                    moves.add(new Move(piece.getCoordinates(), coo, piece.getPieceName(), false, false)); // Add to the end of the list LL = O(1), AL = O(1)
                }
        }
        return moves;
    }

    public int getPositionEvaluation(){
        int points = 0;
        for(Piece piece: pieces){
            if(piece.getPieceName() == "PAWN") points += (isWhiteToMove == piece.isWhite() ? 1 : -1);
            if(piece.getPieceName() == "KNIGHT") points += (isWhiteToMove == piece.isWhite() ? 3 : -3);
            if(piece.getPieceName() == "BISHOP") points += (isWhiteToMove == piece.isWhite() ? 3 : -3);
            if(piece.getPieceName() == "ROOK") points += (isWhiteToMove == piece.isWhite() ? 5 : -5);
            if(piece.getPieceName() == "QUEEN") points += (isWhiteToMove == piece.isWhite() ? 10 : -10);
        }
        return points;
    }

    public int evalDiff(Move o1){
        int points;
        int evalBefore = getPositionEvaluation();
        Piece piece = Piece.getPieceOnSquare(o1.beforeCords, this);
        piece.setRow(o1.afterCords.row);
        piece.setColumn(o1.afterCords.column);
        int evalAfter = getPositionEvaluation();
        piece.setRow(o1.beforeCords.row);
        piece.setColumn(o1.beforeCords.column);
        return evalAfter-evalBefore;
    }

    public static boolean isInCheck(GameState gameState){
        Piece king = null;
        for(Piece piece: gameState.pieces){
            
            if(piece.isWhite() == gameState.isWhiteToMove && piece.getPieceName() == "KING"){
                king = piece;
                break;
            }
        }
        return isSquareUnderAttack(king.getCoordinates(), gameState, !gameState.isWhiteToMove);
    }

    public static boolean isInCheck(Piece movingPiece, Coordinates moveToCords, GameState gameState){
        // piece moveToCords gamestate
        // Update piece cords temporarely
        // Get opposite color piece possibleMovesCords. Check if any of them have the same cords as this color king.
        Coordinates temp = movingPiece.getCoordinates();
        Piece tempPiece = Piece.getPieceOnSquare(moveToCords, gameState);
        movingPiece.setCoordinates(moveToCords);
        if(tempPiece != null)
            gameState.pieces.remove(tempPiece);
        
        Piece king = null;
        for(Piece piece: gameState.pieces){
            if(piece.isWhite() == gameState.isWhiteToMove && piece.getPieceName() == "KING"){
                king = piece;
                break;
            }
        }
        if(isSquareUnderAttack(king.getCoordinates(), gameState, !gameState.isWhiteToMove)){
            // is in check
            movingPiece.setCoordinates(temp);
            if(tempPiece != null)
                gameState.pieces.add(tempPiece);
            return true;
        }
        
        if(tempPiece != null)
            gameState.pieces.add(tempPiece);
        movingPiece.setCoordinates(temp);
        return false;
    }

    public static boolean cordsOutOfBounds(Coordinates cords){
        if(cords.row > 8 || cords.row < 1 || cords.column > 8 || cords.column < 1)
            return true;
        return false;
    }

    public static boolean isSquareUnderAttack(Coordinates coo, GameState gameState, boolean byWhite){
        for(Piece piece: gameState.pieces){
            if(piece.isWhite() == byWhite){
                ArrayList<Coordinates> cords = piece.getPossibleMoves(gameState, false);
                for(Coordinates c: cords){
                    if(c.row == coo.row && c.column == coo.column){
                        return true;
                    }
                        
                }
            }
        }
        return false;
    }

    

    public static String movesToString(ArrayList<Move> moves){
        int moveCount = 0;
        StringBuilder strbr = new StringBuilder();
        for(int i = 0; i < moves.size(); i++){
            if(i % 2 == 0){
                moveCount++;
                strbr.append("" + moveCount + ". ");
            } 
            if(moves.get(i).pieceType == "PAWN"){
                if(moves.get(i).isCapture)
                    strbr.append("" + ((char)(moves.get(i).beforeCords.column + 'a' - 1)) + "x" + ((char)(moves.get(i).afterCords.column + 'a' - 1)) + moves.get(i).afterCords.row );
                else{
                    strbr.append("" + ((char) (moves.get(i).afterCords.column + 'a' - 1) ));
                    strbr.append(moves.get(i).afterCords.row);
                }
            }
            else{
                if(moves.get(i).pieceType == "KING" && moves.get(i).beforeCords.column - moves.get(i).afterCords.column == -2){
                    strbr.append("O-O");
                }
                else if(moves.get(i).pieceType == "KING" && moves.get(i).beforeCords.column - moves.get(i).afterCords.column == 2){
                    strbr.append("O-O-O");
                }
                else{
                    strbr.append(moves.get(i).pieceType.charAt(moves.get(i).pieceType == "KNIGHT"?1:0));
                    if(moves.get(i).isCapture)
                        strbr.append("x");
                    strbr.append("" + ((char) (moves.get(i).afterCords.column + 'a' - 1) ));
                    strbr.append(moves.get(i).afterCords.row);
                }
            }
            if(moves.get(i).isCheck)
                strbr.append("+");
            strbr.append(" ");
        }
        
        return strbr.toString();
    }
}




