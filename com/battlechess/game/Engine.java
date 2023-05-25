
package game;
import java.util.ArrayList;
import pieces.Piece;
import java.util.Random;

/**
 * This class is where all the Computer AI logic is implemented. 
 * It contains one public method - getBestMove(..),
 * that takes GameState object and number representing calculations depth
 * as it's parameters.
 */
public class Engine {
    public static Move getBestMove(GameState gameState, int depth){
        return getBestMove(gameState, null, depth);
    }
    private static Move getBestMove(GameState gameState, Move initialMove, int depth){
        Move bestMove = null;        
        if(depth > 0){
            ArrayList<Move> moves = gameState.getAvailableMoves();
            for(int i = 0; i < moves.size(); i++){
                Move move = moves.get(i); // Access by index - AL = O(1), LL = O(n)
                GameState gameStateCopy = null;
                try{gameStateCopy = gameState.clone();
                }catch(Exception e){e.printStackTrace();}
                Piece piece = Piece.getPieceOnSquare(move.beforeCords, gameStateCopy);
                piece.move(move.afterCords, gameStateCopy);
                Move branchMove = getBestMove(gameStateCopy, move, depth-1);
                if(branchMove == null){
                    continue;
                }
                if(bestMove == null 
                || (gameStateCopy.isWhiteToMove && branchMove.eval > bestMove.eval) 
                || (!gameStateCopy.isWhiteToMove && branchMove.eval < bestMove.eval)){
                    bestMove = move; // !!
                    bestMove.eval = branchMove.eval;
                }
                
            }
            return bestMove;
        }
        else{
            initialMove.eval = doEval(gameState);
            return initialMove;
        }
    }
    
    private static float doEval(GameState gameState){
        float score = 0;
        for(Piece piece: gameState.pieces){
            if(piece.getPieceName() == "PAWN")
                score += piece.isWhite() ? -1: 1;
            else if(piece.getPieceName() == "KNIGHT")
                score += piece.isWhite() ? -3: 3;
            else if(piece.getPieceName() == "BISHOP")
                score += piece.isWhite() ? -3: 3;
            else if(piece.getPieceName() == "ROOK")
                score += piece.isWhite() ? -5: 5;
            else if(piece.getPieceName() == "QUEEN")
                score += piece.isWhite() ? -10: 10;
        }
        score += gameState.getAvailableMoves().size() * (gameState.isWhiteToMove ? -0.01 : 0.01);
        return score;
    }
}
