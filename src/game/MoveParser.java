package game;

import game.ChessBoard;
import game.Square;
import game.Move;

public class MoveParser {

    /* This method validates the move from the user and parses it to a Move object
     * @param String the move to be parsed
     * @return Move the move parsed
     */

    public static Move parseMove(String moveInput) throws IllegalArgumentException{
        moveInput = moveInput.toLowerCase();
        boolean validInput = moveInput.matches("move\\s[a-h][1-8]\\s[a-h][1-8]");
        Move move;
        if(validInput){
            String[] tmp = moveInput.replaceAll("\\s+$", "").split(" ");

            Square from = ChessBoard.getInstance().getSquare(tmp[1].charAt(1)-49,tmp[1].charAt(0)-97);
            Square to = ChessBoard.getInstance().getSquare(tmp[2].charAt(1)-49,tmp[2].charAt(0)-97);

            move=new Move(from,to);
            return move;
        }
        throw new IllegalArgumentException("Invalid move format");
    }
}
