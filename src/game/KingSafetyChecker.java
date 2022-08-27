package game;

import enums.Color;
import move.MovementHandler;
import pieces.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class KingSafetyChecker {

    public KingSafetyChecker() {
    }


    public static boolean isWhiteKingInCheck(Square whiteKingSquare){
        List<Square> blackPiecesSquares = ChessBoard.getInstance().getBlackPiecesSquares();
        for(Square square : blackPiecesSquares){
            Move move = new Move(square, whiteKingSquare);
            if(square.equals(whiteKingSquare))
                continue;
            if(isValidMove(move))
                return true;
        }
        return false;
    }

    public static boolean isWhiteKingCheckMated(Square whiteKingSquare){
        if(!isWhiteKingInCheck(whiteKingSquare))
            return false;
        List<Square> kingPossibleSquares = getKingPossibleSquares(whiteKingSquare);
        for(Square square : kingPossibleSquares){
            square.setPiece(whiteKingSquare.getPiece());
            if(!isWhiteKingInCheck(square))
                return false;
        }
        return true;
    }


    public static boolean isBlackKingInCheck(Square blackKingSquare){
        List<Square> whitePiecesSquares = ChessBoard.getInstance().getWhitePiecesSquares();
        for(Square square : whitePiecesSquares){
            Move move = new Move(square, blackKingSquare);
            if(square.equals(blackKingSquare))
                continue;
            if(isValidMove(move))
                return true;
        }
        return false;
    }

    public static boolean isBlackKingCheckMated(Square blackKingSquare){
        if(!isBlackKingInCheck(blackKingSquare))
            return false;
        List<Square> kingPossibleSquares = getKingPossibleSquares(blackKingSquare);
        for(Square square : kingPossibleSquares){
            square.setPiece(blackKingSquare.getPiece());
            if (!isBlackKingInCheck(square)) {
                return false;
            }
        }
        return true;
    }
    private static List<Square> getKingPossibleSquares(Square kingSquare){
        List<Square> possibleMoves = new ArrayList<>();

        int row = kingSquare.getRow();
        int column = kingSquare.getColumn();

        Color kingColor = kingSquare.getPiece().getColor();

        for(int i = row-1 ; i<=row+1 ; i++){
            for(int j = column-1 ; j<=column+1 ; j++){
                if((i==row && j==column) || i<0 || i>=8 || j<0 || j>=8)
                    continue;
                Square square = ChessBoard.getInstance().getSquare(i, j);
                if(!square.isEmptySquare() && square.getPiece().getColor() == kingColor)
                    continue;
                possibleMoves.add(ChessBoard.getInstance().getSquare(i, j));
            }
        }
        return possibleMoves;
    }
    private static boolean isValidMove(Move move){
        if(move == null)
            return false;
        Square from = move.getStartSquare();
        Square to = move.getEndSquare();
        Piece piece = from.getPiece();
        if(!to.isEmptySquare() && to.getPiece().getColor() == piece.getColor())
            return false;
        return piece.validateMove(move) && ChessBoard.getInstance().isEmptyPath(move);
    }
}
