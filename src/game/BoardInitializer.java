package game;

import enums.Color;
import pieces.pieces.*;

public class BoardInitializer {

    public static void initializeBoard(ChessBoard chessBoard) {
        int BOARD_SIZE = chessBoard.getBoardSize();
        for(int i = 0 ; i<BOARD_SIZE ; i++){
            for(int j = 0 ; j<BOARD_SIZE ; j++){
                chessBoard.setPiece(i, j, null);
            }
        }
        initializeWhitePieces(chessBoard);
        initializeBlackPieces(chessBoard);
    }
    private static void initializeWhitePieces(ChessBoard chessBoard){
        initializeWhitePawns(chessBoard);
        initializeWhiteRocks(chessBoard);
        initializeWhiteKnights(chessBoard);
        initializeWhiteBishops(chessBoard);
        initializeWhiteQueen(chessBoard);
        initializeWhiteKing(chessBoard);
    }

    private static void initializeWhitePawns(ChessBoard chessBoard) {
        int BOARD_SIZE = chessBoard.getBoardSize();
        for(int i = 0 ; i<BOARD_SIZE ; i++)
            chessBoard.setPiece(1, i, new Pawn(Color.White));
    }

    private static void initializeWhiteRocks(ChessBoard chessBoard) {
        chessBoard.setPiece(0, 0, new Rook(Color.White));
        chessBoard.setPiece(0, 7, new Rook(Color.White));
    }
    private static void initializeWhiteKnights(ChessBoard chessBoard) {
        chessBoard.setPiece(0, 1, new Knight(Color.White));
        chessBoard.setPiece(0, 6, new Knight(Color.White));
    }
    private static void initializeWhiteBishops(ChessBoard chessBoard) {
        chessBoard.setPiece(0, 2, new Bishop(Color.White));
        chessBoard.setPiece(0, 5, new Bishop(Color.White));
    }
    private static void initializeWhiteQueen(ChessBoard chessBoard) {
        chessBoard.setPiece(0, 3, new Queen(Color.White));
    }
    private static void initializeWhiteKing(ChessBoard chessBoard) {
        chessBoard.setPiece(0, 4, new King(Color.White));
    }


    private static void initializeBlackPieces(ChessBoard chessBoard){
        initializeBlackPawns(chessBoard);
        initializeBlackRocks(chessBoard);
        initializeBlackKnights(chessBoard);
        initializeBlackBishops(chessBoard);
        initializeBlackQueen(chessBoard);
        initializeBlackKing(chessBoard);
    }

    private static void initializeBlackPawns(ChessBoard chessBoard) {
        int BOARD_SIZE = chessBoard.getBoardSize();
        for(int i = 0 ; i<BOARD_SIZE ; i++)
            chessBoard.setPiece(6, i, new Pawn(Color.Black));
    }
    private static void initializeBlackRocks(ChessBoard chessBoard) {
        chessBoard.setPiece(7, 0, new Rook(Color.Black));
        chessBoard.setPiece(7, 7, new Rook(Color.Black));
    }
    private static void initializeBlackKnights(ChessBoard chessBoard) {
        chessBoard.setPiece(7, 1, new Knight(Color.Black));
        chessBoard.setPiece(7, 6, new Knight(Color.Black));
    }
    private static void initializeBlackBishops(ChessBoard chessBoard) {
        chessBoard.setPiece(7, 2, new Bishop(Color.Black));
        chessBoard.setPiece(7, 5, new Bishop(Color.Black));
    }
    private static void initializeBlackQueen(ChessBoard chessBoard) {
        chessBoard.setPiece(7, 3, new Queen(Color.Black));
    }
    private static void initializeBlackKing(ChessBoard chessBoard) {
        chessBoard.setPiece(7, 4, new King(Color.Black));
    }
}
