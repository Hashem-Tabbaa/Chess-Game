package movementBehavior;

import game.Square;
import game.Move;

public class RookBehavior implements MovementBehavior {

    @Override
    public boolean isValidMove(Move move) {
        Square from = move.getStartSquare();
        Square to = move.getEndSquare();

        return from.getRow() == to.getRow() || from.getColumn() == to.getColumn();
    }
}
