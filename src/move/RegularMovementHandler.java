package move;

import game.ChessBoard;
import game.Move;
import game.Square;
import pieces.pieces.King;
import pieces.pieces.Knight;
import pieces.pieces.Piece;
import pieces.pieces.Rook;

public class RegularMovementHandler extends MovementHandler{
    @Override
    public boolean handle(Move move) {
        ChessBoard chessBoard = ChessBoard.getInstance();

        if(isValidRegularMove(move)){
            try{
                chessBoard.performMove(move);
                return true;
            }catch(Exception e){
                System.out.println(e.getMessage());
                return false;
            }
        }
        return false;
    }

    private boolean isValidRegularMove(Move move){
        Square from = move.getStartSquare();
        Piece pieceFrom = ChessBoard.getInstance().getPiece(from.getRow(), from.getColumn());

        boolean isValidMove = move.validateTheMove();
        boolean isEmptyPath = true;
        if(!(pieceFrom instanceof Knight) && isValidMove)
            isEmptyPath = ChessBoard.getInstance().isEmptyPath(move);
        return isValidMove && isEmptyPath;
    }
    private void performMove(Move move){
        Square from = move.getStartSquare();
        Square to = move.getEndSquare();
        Piece pieceFrom = ChessBoard.getInstance().getPiece(from.getRow(), from.getColumn());

        Piece piece = ChessBoard.getInstance().getPiece(from.getRow(), from.getColumn());

        ChessBoard.getInstance().performMove(move);

        if(piece instanceof King)
            ((King) piece).setZeroMove(true);
        else if(piece instanceof Rook)
            ((Rook) piece).setZeroMove(true);
    }
}
