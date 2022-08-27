package game;

import pieces.pieces.Piece;

public class Square implements Cloneable {
    private final int row;
    private final int column;
    private Piece piece;

    public Square(int row, int column, Piece piece) {
        if(row > 7 || row < 0 || column > 7 || column < 0)
            throw new IllegalArgumentException("Invalid row or column");
        this.row = row;
        this.column = column;
        this.piece = piece;
    }
    public Square(int x, int y) {
        this.row = x;
        this.column = y;
        piece = null;
    }
    public boolean isEmptySquare(){
        return piece == null;
    }
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public Piece getPiece() {
        if(isEmptySquare())
            throw new RuntimeException("No piece on this square");
        return piece.clone();
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    @Override
    public String toString() {
        if(piece==null)
            return " - ";
        return piece.toString();
    }
    @Override
    public Square clone(){
        if(isEmptySquare())
            return new Square(row, column);
        return new Square(row, column, piece.clone());
    }
    @Override public boolean equals(Object obj) {
        if(obj == null)
            throw new IllegalArgumentException("Object cannot be null");
        if(obj instanceof Square){
            Square other = (Square) obj;
            return other.row == row && other.column == column;
        }
        return false;
    }
}
