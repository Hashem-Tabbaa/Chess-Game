package game;

import game.ChessBoard;
import game.Square;
import pieces.pieces.Piece;

public class Move {
    private final Square startSquare;
    private Square endSquare;

    public Move(Square startSquare, Square endSquare) {
        this.startSquare = startSquare;
        this.endSquare = endSquare;
    }

    public Square getStartSquare() {
        return startSquare;
    }
    public Square getEndSquare() {
        return endSquare;
    }
    public void setEndSquare(Square endSquare) {
        this.endSquare = endSquare;
    }

    public boolean validateTheMove(){
        Piece piece = ChessBoard.getInstance().getPiece(startSquare.getRow(), startSquare.getColumn());

        if(startSquare.isEmptySquare() || !isInsideBoard(this.startSquare) || !isInsideBoard(this.endSquare))
            return false;
        if(!endSquare.isEmptySquare() && endSquare.getPiece().getColor() == piece.getColor())
            return false;
        return piece.validateMove(this);
    }

    /* This method checks if the given square is inside the board boundaries
     * @param Move the move to be checked
     * @return true if the move is valid, false otherwise
     */
    public boolean isInsideBoard(Square square){
        return square.getRow()>=0 && square.getRow()<8 && square.getColumn()>=0 && square.getColumn()<8;
    }
}
