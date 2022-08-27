package pieces.pieces;

import enums.Color;
import game.Move;
import movementBehavior.MovementBehavior;

public abstract class Piece implements Cloneable {

    private final Color color;
    private MovementBehavior behavior;
    private Character shape;

    public Piece(Color color, MovementBehavior behavior, char shape) {
        this.color = color;
        this.behavior = behavior;
        this.shape = shape;
    }
    public Color getColor() {
        return color;
    }
    public boolean validateMove(Move move){
        return behavior.isValidMove(move);
    }
    public void setBehavior(MovementBehavior behavior) {
        this.behavior = behavior;
    }
    @Override
    public String toString(){
        if(this.color == Color.White)
            return " " + Character.toUpperCase(shape)+ " ";
        return " " + Character.toLowerCase(shape) + " ";
    }
    @Override
    public abstract Piece clone();
}
