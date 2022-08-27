package movementBehavior;

import game.Square;
import game.Move;
import enums.Color;
import pieces.pieces.Piece;

public class PawnBehavior implements MovementBehavior {


    @Override
    public boolean isValidMove(Move move) {
        Square from = move.getStartSquare();
        Square to = move.getEndSquare();

        Piece pieceFrom = from.getPiece();
        Piece pieceTo = null;
        if(!to.isEmptySquare())
            pieceTo = to.getPiece();

        // If pawn is moving backwards, return false
        if((from.getRow() < to.getRow() && pieceFrom.getColor() == Color.Black) ||
                (from.getRow() > to.getRow() && pieceFrom.getColor() == Color.White))
            return false;

        // If pawn is moving his first square, he can move two squares
        if(from.getRow() == 1 && to.getRow() == 3 && pieceFrom.getColor() == Color.White ||
                from.getRow() == 6 && to.getRow() == 4 && pieceFrom.getColor() == Color.Black)
            return true;

        // If pawn is not moving his first move, he can move only one square
        int rowsDifference = Math.abs(from.getRow() - to.getRow());
        if(rowsDifference > 1)
            return false;

        // If pawn is moving diagonally, he can move only one square and only if there is an enemy piece
        int columnsDifference = Math.abs(from.getColumn() - to.getColumn());
        if(columnsDifference == 1&& pieceTo != null && pieceTo.getColor() != pieceFrom.getColor())
            return true;

        // If pawn is moving straight, he can move only one square and only if there is an enemy piece
        if(rowsDifference == 1 && columnsDifference == 0)
            return true;

        return false;
    }
}
