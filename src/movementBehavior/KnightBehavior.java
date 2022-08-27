package movementBehavior;

import game.Square;
import game.Move;

public class KnightBehavior implements MovementBehavior {

    @Override
    public boolean isValidMove(Move move) {
        Square from = move.getStartSquare();
        Square to = move.getEndSquare();

        int rowDifference = Math.abs(from.getRow() - to.getRow());
        int columnDifference = Math.abs(from.getColumn() - to.getColumn());

        return rowDifference == 2 && columnDifference == 1 ||
                rowDifference == 1 && columnDifference == 2;
    }

}
