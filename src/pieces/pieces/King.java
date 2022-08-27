package pieces.pieces;

import game.ChessBoard;
import game.Square;
import game.Move;
import enums.Color;
import movementBehavior.KingBehavior;

public class King extends Piece {

    private boolean zeroMove;

    public King(Color color){
        super(color,new KingBehavior(), 'G');

        zeroMove = true;
    }
    public boolean canShortCastle(){
        if(!zeroMove)
            return false;

        ChessBoard chessBoard = ChessBoard.getInstance();

        Square from = (this.getColor() == Color.White) ? chessBoard.getWhiteKing() : chessBoard.getBlackKing();
        Square to = chessBoard.getSquare(from.getRow(), from.getColumn() + 2);

        if(chessBoard.getSquare(from.getRow(), 7).isEmptySquare())
            return false;

        Rook rook = (Rook) chessBoard.getPiece(from.getRow(), 7);

        if(!rook.canCastle())
            return false;

        return chessBoard.isEmptyPath(new Move(from, to));
    }
    public boolean canLongCastle(){
        if(!zeroMove)
            return false;

        ChessBoard chessBoard = ChessBoard.getInstance();

        Square from = (this.getColor() == Color.White) ? chessBoard.getWhiteKing() : chessBoard.getBlackKing();
        Square to = chessBoard.getSquare(from.getRow(), from.getColumn() - 3);

        if(chessBoard.getSquare(from.getRow(),0).isEmptySquare())
            return false;

        Rook rook = (Rook) chessBoard.getPiece(from.getRow(), 0);

        if(!rook.canCastle())
            return false;

        return chessBoard.isEmptyPath(new Move(from, to));
    }
    public void setZeroMove(boolean zeroMove) {
        this.zeroMove = zeroMove;
    }
    @Override
    public Piece clone() {
        return new King(this.getColor());
    }
}
