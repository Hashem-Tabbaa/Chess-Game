package game;

import move.MovementHandler;
import move.MovementHandlerFactory;
import player.Player;

public class ChessGameFactory {
    public ChessGame createChessGame(Player whitePlayer, Player blackPlayer) {

        MovementHandlerFactory movementHandlerFactory = new MovementHandlerFactory();
        MovementHandler movementHandler = movementHandlerFactory.createMovementHandler();

        ChessGame chessGame = new ChessGame(whitePlayer, blackPlayer, ChessBoard.getInstance(), movementHandler);

        return chessGame;
    }
}
