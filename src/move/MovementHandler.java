package move;

import game.*;
import enums.Color;
import pieces.pieces.Piece;

public abstract class MovementHandler{
    protected MovementHandler nextHandler = null;
    public void setNextHandler(MovementHandler handler) {
        if(handler==null)
            throw new IllegalArgumentException("Next handler cannot be null");
        this.nextHandler=handler;
    }
    public abstract boolean handle(Move move);
}
