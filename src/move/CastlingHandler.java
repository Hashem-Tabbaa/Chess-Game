package move;

import game.ChessBoard;
import game.Move;
import game.Square;
import pieces.pieces.King;
import pieces.pieces.Rook;

public class CastlingHandler extends MovementHandler{
    @Override
    public boolean handle(Move move) {
        if(!isCastlingMove(move)){
            if(nextHandler != null)
                return nextHandler.handle(move);
            throw new IllegalArgumentException("Request cannot be handled");
        }
        if(!isValidCastling(move))
            return false;

        Square from = move.getStartSquare();
        Square to = move.getEndSquare();

        if(from.getColumn() > to.getColumn())
            performLongCastling(move);
        else
            performShortCastling(move);
        return true;
    }
    private boolean isCastlingMove(Move move){
        Square from = move.getStartSquare();
        Square to = move.getEndSquare();

        if(from.isEmptySquare() || ! (from.getPiece() instanceof King))
            return false;
        King king = (King) from.getPiece();

        int columnDifference = Math.abs(from.getColumn() - to.getColumn());
        if(columnDifference != 2)
            return false;
        return true;
    }
    private boolean isValidCastling(Move move){
        if(move == null)
            throw new IllegalArgumentException("Move cannot be null");

        Square from = move.getStartSquare();
        Square to = move.getEndSquare();


        King king = (King) from.getPiece();

        //long castling
        if(to.getColumn() > from.getColumn() && !king.canShortCastle())
            return false;
        //short castling
        if(to.getColumn() < from.getColumn() && !king.canLongCastle())
            return false;

        return true;
    }
    private void performShortCastling(Move move){

        Square from = move.getStartSquare();
        Square to = move.getEndSquare();

        ChessBoard chessBoard = ChessBoard.getInstance();

        Rook oldRock = (Rook) chessBoard.getSquare(from.getRow(), 7).getPiece();

        chessBoard.removePiece(from.getRow(), 7); //remove the rook
        chessBoard.setPiece(from.getRow(), 5, oldRock); //move the rook to the new position
        chessBoard.performMove(move); //move the king
    }
    private void performLongCastling(Move move){

        Square from = move.getStartSquare();
        Square to = move.getEndSquare();

        ChessBoard chessBoard = ChessBoard.getInstance();

        Rook oldRock = (Rook) chessBoard.getSquare(from.getRow(), 0).getPiece();

        chessBoard.removePiece(from.getRow(), 0); //remove the rook
        chessBoard.setPiece(from.getRow(), 3, oldRock); //move the rook to the new position

        chessBoard.performMove(move);//move the king
    }
}
