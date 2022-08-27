package game;

import enums.GameStatusType;

import java.util.Scanner;

public class GameController {

    private ChessGame chessGame;
    private MoveParser moveParser;
    public GameController(ChessGame chessGame) {
        this.chessGame = chessGame;
        moveParser = new MoveParser();
    }
    public void start(){
        int playedMoves = 0;
        Scanner scan = new Scanner(System.in);
        while (chessGame.isGameStatusActive()){
            chessGame.print();
            if(chessGame.isWhiteTurn())
                System.out.print("Enter next move (white player): ");
            else
                System.out.print("Enter next move (black player): ");
            String move = scan.nextLine();
            Move parsedMove = null;
            try {
                parsedMove = moveParser.parseMove(move);
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                continue;
            }
            if(chessGame.isWhiteTurn())
                chessGame.playWhiteMove(parsedMove);
            else
                chessGame.playBlackMove(parsedMove);
            playedMoves++;
            if(playedMoves == 50){
                System.out.println("Forced draw!!");
                chessGame.setGameStatus(GameStatusType.DRAW);
                break;
            }
            chessGame.updateGameStatus();
        }
        chessGame.print();
        if(chessGame.getGameStatus() == GameStatusType.DRAW)
            System.out.println("Game ended in a draw");
        else if(chessGame.getGameStatus() == GameStatusType.WHITE_WIN)
            System.out.println("White (" +chessGame.getWhitePlayer()+ ") player won");
        else
            System.out.println("Black (" +chessGame.getBlackPlayer()+ ") player won");
    }

}
