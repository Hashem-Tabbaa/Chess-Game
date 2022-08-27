package movementBehavior;

import game.Move;

public interface MovementBehavior {

     /*
       * This method checks if the move is valid for a piece
       * @param Move the move to be checked
       * @return true if the move is valid, false otherwise
   */
     boolean isValidMove(Move move);
}
