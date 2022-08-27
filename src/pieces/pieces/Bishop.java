package pieces.pieces;

import enums.Color;
import movementBehavior.BishopBehavior;

public class Bishop extends Piece{
    public Bishop(Color color) {
        super(color, new BishopBehavior(), 'B');
    }

    @Override
    public Piece clone() {
        return new Bishop(this.getColor());
    }
}
