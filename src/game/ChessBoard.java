package game;

import enums.Color;
import enums.GameStatusType;
import pieces.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard{


    private static ChessBoard chessBoard = null;
    private Square[][] board;

    private Square whiteKing;
    private Square blackKing;
    private final int BOARD_SIZE = 8;

    private ChessBoard() {
        initializeBoardWithSquares();

        BoardInitializer.initializeBoard(this);

        whiteKing = board[0][4];
        blackKing = board[7][4];
    }
    public static ChessBoard getInstance() {
        if (chessBoard == null) {
            chessBoard = new ChessBoard();
        }
        return chessBoard;
    }
    private void initializeBoardWithSquares(){
        board = new Square[BOARD_SIZE][BOARD_SIZE];
        for(int i = 0 ; i<BOARD_SIZE ; i++){
            for(int j = 0 ; j<BOARD_SIZE ; j++){
                board[i][j] = new Square(i, j);
            }
        }
    }
    public boolean isEmptyPath(Move move){
        return BoardPathValidator.isEmptyPath(move, this);
    }
    public Square getWhiteKing(){
        return whiteKing.clone();
    }
    public Square getBlackKing(){
        return blackKing.clone();
    }
    public void performMove(Move move){
        if(move == null)
            throw new IllegalArgumentException("Move cannot be null");

        Square[][] oldBoard = new Square[BOARD_SIZE][BOARD_SIZE];
        for(int i = 0 ; i<BOARD_SIZE ; i++){
            for(int j = 0 ; j<BOARD_SIZE ; j++){
                oldBoard[i][j] = board[i][j].clone();
            }
        }

        Square from = move.getStartSquare();
        Square to = move.getEndSquare();

        if(from == null || to == null)
            throw new IllegalArgumentException("Move cannot be null");
        if(from.isEmptySquare())
            throw new IllegalArgumentException("Start square cannot be empty");

        Piece pieceFrom = from.getPiece();

        if (!to.isEmptySquare()) {
            removePiece(to.getRow(), to.getColumn());
        }

        setPiece(to.getRow(), to.getColumn(), pieceFrom);
        removePiece(from.getRow(), from.getColumn());

        Square oldKingSquare = pieceFrom.getColor() == Color.White ? whiteKing : blackKing;
        if (pieceFrom instanceof King)
            updateKingSquare(pieceFrom.getColor(), to);

        if(pieceFrom.getColor() == Color.White && KingSafetyChecker.isWhiteKingInCheck(whiteKing)){
            board = oldBoard;
            updateKingSquare(Color.White, oldKingSquare);
            throw new IllegalArgumentException("White king is in check");
        }
        if(pieceFrom.getColor() == Color.Black && KingSafetyChecker.isBlackKingInCheck(blackKing)){
            board = oldBoard;
            updateKingSquare(Color.Black, oldKingSquare);
            throw new IllegalArgumentException("Black king is in check");
        }
    }
    private void updateKingSquare(Color color, Square square){
        if(color == Color.White)
            whiteKing = square;
        else
            blackKing = square;
    }
    public void removePiece(int x,int y){
        Square square = board[x][y];
        if(square.isEmptySquare())
            throw new RuntimeException("No piece on this square");
        this.setPiece(x, y, null);
    }
    public Piece getPiece(int x, int y){
        if(board[x][y].isEmptySquare())
            throw new IllegalArgumentException("Square is empty");
        return  board[x][y].getPiece();
    }
    public void setPiece(int x,int y,Piece piece){
        board[x][y].setPiece(piece);
    }
    public int getBoardSize(){
        return BOARD_SIZE;
    }
    public void resetGame(){
        chessBoard = null;
        ChessBoard.getInstance();
    }
    public Square getSquare(int i, int j){
        return board[i][j].clone();
    }
    public void print(){
        System.out.println();
        for(int i = 7 ; i>=0 ; i--) {
            for (int j = 0 ; j<8 ; j++) {
                System.out.print(board[i][j].toString());
            }
            System.out.println();
        }
    }

    /* This method is used to check if the game is over or not
     * It returns the status of the game
     */
    public GameStatusType updateBoardStatus(){
        if(KingSafetyChecker.isBlackKingCheckMated(blackKing))
            return GameStatusType.WHITE_WIN;
        if(KingSafetyChecker.isWhiteKingCheckMated(whiteKing))
            return GameStatusType.BLACK_WIN;
        return GameStatusType.ACTIVE;
    }
    public List<Square> getBlackPiecesSquares(){
        List<Square> squares = new ArrayList<>();
        for(int i = 0 ; i<BOARD_SIZE ; i++){
            for(int j = 0 ; j<BOARD_SIZE ; j++){
                if(!board[i][j].isEmptySquare() && board[i][j].getPiece().getColor() == Color.Black){
                    squares.add(board[i][j].clone());
                }
            }
        }
        return squares;
    }
    public List<Square> getWhitePiecesSquares(){
        List<Square> pieces = new ArrayList<>();
        for(int i = 0 ; i<BOARD_SIZE ; i++){
            for(int j = 0 ; j<BOARD_SIZE ; j++){
                if(!board[i][j].isEmptySquare() && board[i][j].getPiece().getColor() == Color.White){
                    pieces.add(board[i][j].clone());
                }
            }
        }
        return pieces;
    }
}
