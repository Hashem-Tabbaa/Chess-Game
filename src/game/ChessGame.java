package game;

import player.Player;
import enums.*;
import move.*;

public class ChessGame {

    private static MovementHandler movementHandler;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private GameStatusType status;
    private boolean whiteTurn;
    private final ChessBoard chessBoard;

    public ChessGame(Player whitePlayer, Player blackPlayer, ChessBoard chessBoard, MovementHandler movementHandler) {
        if(whitePlayer == null || blackPlayer == null)
            throw new IllegalArgumentException("Players cannot be null");

        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.movementHandler = movementHandler;

        status = GameStatusType.ACTIVE;
        whiteTurn=true;

        this.chessBoard = chessBoard;
        chessBoard.resetGame();
    }
    public boolean isGameStatusActive(){
        return status == GameStatusType.ACTIVE;
    }
    public boolean isWhiteTurn(){
        return whiteTurn;
    }
    public void playWhiteMove(Move move){
        if(move == null)
            throw new IllegalArgumentException("Move cannot be null");
        if(move.getStartSquare().isEmptySquare() || move.getStartSquare().getPiece().getColor() != Color.White){
            System.out.println("Invalid move");
            return;
        }
        if(playMove(move))
            whiteTurn =false;
    }
    public void playBlackMove(Move move){
        if(move == null)
            throw new IllegalArgumentException("Move cannot be null");
        if(move.getStartSquare().isEmptySquare() || move.getStartSquare().getPiece().getColor() != Color.Black){
            System.out.println("Invalid move");
            return;
        }

        if(playMove(move))
            whiteTurn =true;
    }
    private boolean playMove(Move move){
        if(move == null)
            return false;

        boolean success = movementHandler.handle(move);
        if (!success) {
            System.out.println(
            (whiteTurn ? whitePlayer : blackPlayer) + " entered an invalid move, try again please");
          return false;
        }
        return true;
    }
    public void setGameStatus(GameStatusType status){
        this.status = status;
    }
    public void print(){
        ChessBoard.getInstance().print();
    }
    public void updateGameStatus(){
        status = ChessBoard.getInstance().updateBoardStatus();
    }
    public GameStatusType getGameStatus(){
        return status;
    }
    public Player getWhitePlayer(){
        return whitePlayer;
    }
    public Player getBlackPlayer(){
        return blackPlayer;
    }
}
