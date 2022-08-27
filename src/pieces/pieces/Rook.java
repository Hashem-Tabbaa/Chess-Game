package pieces.pieces;

import enums.Color;
import movementBehavior.RookBehavior;

public class Rook extends Piece{

    private boolean zeroMove;

    public Rook(Color color) {
        super(color, new RookBehavior(), 'R');
        zeroMove = true;
    }

    public boolean canCastle(){
        return zeroMove;
    }
    public void setZeroMove(boolean zeroMove) {
        this.zeroMove = zeroMove;
    }
    @Override
    public Piece clone() {
        return new Rook(this.getColor());
    }
}
