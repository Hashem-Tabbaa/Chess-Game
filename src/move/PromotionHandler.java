package move;

import game.ChessBoard;
import game.Move;
import game.Square;
import enums.Color;
import pieces.pieces.Pawn;
import pieces.pieces.Piece;

import java.util.Scanner;

public class PromotionHandler extends MovementHandler {
    @Override
    public boolean handle(Move move) {

        if(move == null || move.getEndSquare() == null || move.getStartSquare().isEmptySquare())
            return false;

        if(!isPromotionMove(move)){
            if(nextHandler != null)
                return nextHandler.handle(move);
            throw new IllegalArgumentException("Request cannot be handled");
        }

        if(!isValidPromotionMove(move))
            return false;

        performPromotion(move);
        return true;
    }
    private void performPromotion(Move move){

        Square to = move.getEndSquare();
        ChessBoard chessBoard = ChessBoard.getInstance();

        chessBoard.performMove(move);
        Color pieceColor = move.getStartSquare().getPiece().getColor();

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the new piece type (K, B, N, R, Q): ");
        String pieceType = scan.nextLine();

        PieceFactory pieceFactory = new PieceFactory();
        Piece newPiece = pieceFactory.buildPiece(pieceType, pieceColor);

        chessBoard.setPiece(to.getRow(), to.getColumn(), newPiece);
    }
    private boolean isValidPromotionMove(Move move){
        if(move == null)
            throw new IllegalArgumentException("Move cannot be null");

        Square from = move.getStartSquare();
        Square to = move.getEndSquare();
        ChessBoard chessBoard = ChessBoard.getInstance();

        int columnDifference = Math.abs(from.getColumn() - to.getColumn());

        if(columnDifference > 1)
            return false;

        if(columnDifference == 1){
            if(to.isEmptySquare() || to.getPiece().getColor() == from.getPiece().getColor())
                return false;
        }

        return chessBoard.isEmptyPath(move);
    }
    private boolean isPromotionMove(Move move){

        Square from = move.getStartSquare();
        Square to = move.getEndSquare();

        if(from.isEmptySquare() || !(from.getPiece() instanceof Pawn))
            return false;

        Pawn pawn = null;
        try{
            pawn = (Pawn) from.getPiece();
        }catch(Exception e){
            return false;
        }

        int columnDifference = Math.abs(from.getColumn() - to.getColumn());

        //White pawn promotion
        if(pawn.getColor() == Color.White && from.getRow() == 6 && to.getRow() == 7 &&
                columnDifference <= 1)
            return true;
        //Black pawn promotion
        else if(pawn.getColor() == Color.Black && from.getRow() == 1 && to.getRow() == 0 &&
                columnDifference <= 1)
            return true;
        return false;
    }
}
