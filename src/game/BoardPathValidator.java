package game;

import pieces.pieces.Pawn;
import pieces.pieces.Piece;

public class BoardPathValidator {

    private ChessBoard chessBoard;

    public static boolean isEmptyPath(Move move, ChessBoard chessBoard) {
        if(move == null)
            throw new IllegalArgumentException("Move cannot be null");
        Square from = move.getStartSquare();
        Square to = move.getEndSquare();

        int rowDifference = Math.abs(from.getRow() - to.getRow());
        int columnDifference = Math.abs(from.getColumn() - to.getColumn());
        if(columnDifference == 0)
            return isEmptyVerticalPath(move, chessBoard);
        else if(rowDifference == 0)
            return isEmptyHorizontalPath(move, chessBoard);
        else if(rowDifference == columnDifference)
            return isEmptyDiagonalPath(move, chessBoard);

        return true;
    }
    private static boolean isEmptyVerticalPath(Move move, ChessBoard chessBoard) {
        Square from = move.getStartSquare();
        Square to = move.getEndSquare();

        Piece pieceFrom = from.getPiece();

        int row = from.getRow();
        int rowIncrement = from.getRow() < to.getRow() ? 1 : -1;
        while(row != to.getRow()) {
            row += rowIncrement;
            if((row != to.getRow() || pieceFrom instanceof Pawn) && !chessBoard.getSquare(row, from.getColumn()).isEmptySquare()) {
                return false;
            }
        }
        return true;
    }
    private static boolean isEmptyHorizontalPath(Move move, ChessBoard chessBoard){
        Square from = move.getStartSquare();
        Square to = move.getEndSquare();

        int column = from.getColumn();
        while(column != to.getColumn()) {
            column += from.getColumn() < to.getColumn() ? 1 : -1;
            if(column != to.getColumn() && !chessBoard.getSquare(from.getRow(), column).isEmptySquare())
                return false;
        }
        return true;
    }
    private static boolean isEmptyDiagonalPath(Move move, ChessBoard chessBoard){
        Square from = move.getStartSquare();
        Square to = move.getEndSquare();

        int row = from.getRow();
        int column = from.getColumn();

        int rowIncrement = (from.getRow() > to.getRow()) ? -1 : 1;
        int columnIncrement = (from.getColumn() > to.getColumn()) ? -1 : 1;

        while(row != to.getRow() && column != to.getColumn()) {
            row += rowIncrement;
            column += columnIncrement;
            if(row != to.getRow() && column != to.getColumn() &&
                    !chessBoard.getSquare(row, column).isEmptySquare())
                return false;
        }
        return true;
    }
}
