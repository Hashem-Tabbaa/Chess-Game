package pieces.pieces;

import enums.Color;
import movementBehavior.QueenBehavior;

public class Queen extends Piece{

    public Queen(Color color) {
        super(color, new QueenBehavior(), 'Q');
    }

    @Override
    public Piece clone() {
        return new Queen(this.getColor());
    }
}
