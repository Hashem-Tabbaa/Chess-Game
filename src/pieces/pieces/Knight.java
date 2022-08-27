package pieces.pieces;

import enums.Color;
import movementBehavior.KnightBehavior;

public class Knight extends Piece{
    public Knight(Color color) {
        super(color, new KnightBehavior(), 'K');
    }

    @Override
    public Piece clone() {
        return new Knight(this.getColor());
    }
}
