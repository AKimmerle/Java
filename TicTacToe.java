import java.util.Scanner;

public class TicTacToe {
    private char[][] board;

    public TicTacToe() {
        board = new char[3][3];
        blankBoard();
    }

    public void blankBoard() { 
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = '-';
            }
        } 
    }

    public void printBoard() { 
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                System.out.print(board[row][col] + " ");
            }
                System.out.println();
        } 
    }

    public boolean makeMove(int row, int col, char player) { 
        if (board[row][col] == '-') {
            board[row][col] = player;
            return true;
        } 
        return false;
   }

    public char checkWinner() { 
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
            return board[0][0];
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-') {
            return board[0][2];
        }
        
        for (int col = 0; col < board.length; col++) {
                if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != '-') {
                    return board[0][col];
                }
        }

        for (int row = 0; row < board.length; row++) {
                if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != '-') {
                    return board[row][0];
                }
        }
        return '-';
    }
    public boolean isFull() { 
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        } 
        return true;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        char player = 'X';
        Scanner scan = new Scanner(System.in);
        while (game.checkWinner() == '-' && !game.isFull()) {
            game.printBoard();
            System.out.println("It is " + player + "'s turn");
            System.out.println("Which row would you like to go? (1-3)");
            int row = scan.nextInt() - 1;
            System.out.println("Where column would you like to go? (1-3)");
            int col = scan.nextInt() - 1;
            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Invalid input, please enter a number between 1 and 3");
                continue;
            }
            boolean moved = game.makeMove(row, col, player);
            if (moved) {
                
            }

            else {
                System.out.println("Can't place an " + player + " there, try again");
                continue;
            }

            if (player == 'X') {
                player = 'O';
            }

            else if (player == 'O') {
                player = 'X';
            }
            
        }
    game.printBoard();
    char winner = game.checkWinner();

    if (winner != '-') {
        System.out.println("Player " + winner + " wins!");
    } 

    else {
    System.out.println("It's a draw!");
    }

    }
}