package move;

import enums.Color;
import pieces.pieces.Pawn;
import pieces.pieces.*;


public class PieceFactory {

    public Piece buildPiece(String pieceType, Color color) {
        if(pieceType.equals("p"))
            return new Pawn(color);
        else if(pieceType.equals("R"))
            return new Rook(color);
        else if(pieceType.equals("N"))
            return new Knight(color);
        else if(pieceType.equals("B"))
            return new Bishop(color);
        else if(pieceType.equals("Q"))
            return new Queen(color);
        else if(pieceType.equals("K"))
            return new King(color);
        throw new IllegalArgumentException("Piece type not recognized");
    }
}
