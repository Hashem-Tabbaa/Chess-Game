package test;

import game.ChessBoard;
import game.ChessGame;
import game.ChessGameFactory;
import game.GameController;
import player.Player;
import java.util.Scanner;
import enums.Color;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the white player's name : ");
        String whitePlayer = scanner.nextLine();

        System.out.println("Enter the black player's name : ");
        String blackPlayer = scanner.nextLine();

        ChessGameFactory chessGameFactory = new ChessGameFactory();

        ChessGame chessGame = chessGameFactory.createChessGame(new Player(whitePlayer, Color.White),
                new Player(blackPlayer, Color.Black));

        GameController controller = new GameController(chessGame);

        controller.start();

    }
}
