package pieces.pieces;

import enums.Color;
import movementBehavior.PawnBehavior;

public class Pawn extends Piece{
    public Pawn(Color color) {
        super(color,new PawnBehavior(), 'P');
    }

    @Override
    public Piece clone() {
        return new Pawn(this.getColor());
    }
}
