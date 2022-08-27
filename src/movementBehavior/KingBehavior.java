package movementBehavior;

import game.Move;

public class KingBehavior implements MovementBehavior {

    @Override
    public boolean isValidMove(Move move) {
        int rowDifference = Math.abs(move.getStartSquare().getRow() - move.getEndSquare().getRow());
        int columnDifference = Math.abs(move.getStartSquare().getColumn() - move.getEndSquare().getColumn());

        return rowDifference <= 1 && columnDifference <= 1;
    }
}
