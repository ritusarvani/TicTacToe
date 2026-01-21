import java.util.Scanner;

public class TicTacToe {

    static char[][] board = new char[3][3];
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("      TIC TAC TOE - JAVA GAME     ");
        System.out.println("=================================");
        System.out.println("Instructions:");
        System.out.println("Enter row and column number (0, 1, or 2)");
        System.out.println("Example: 0 1 means row 0, column 1");
        System.out.println();

        boolean playAgain = true;

        while (playAgain) {
            initializeBoard();
            playGame();
            playAgain = askPlayAgain();
        }

        System.out.println("Thanks for playing! 😊");
    }

    static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    static void playGame() {
        char currentPlayer = 'X';
        boolean gameOver = false;

        while (!gameOver) {
            printBoard();
            System.out.println("Player " + currentPlayer + "'s turn.");

            int row, col;

            while (true) {
                System.out.print("Enter row and column: ");
                row = sc.nextInt();
                col = sc.nextInt();

                if (isValidMove(row, col)) {
                    board[row][col] = currentPlayer;
                    break;
                } else {
                    System.out.println("Invalid move! Try again.");
                }
            }

            if (checkWinner(currentPlayer)) {
                printBoard();
                System.out.println("🎉 Player " + currentPlayer + " wins!");
                gameOver = true;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("🤝 It's a draw!");
                gameOver = true;
            } else {
                // Switch player
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    static void printBoard() {
        System.out.println();
        System.out.println("Current Board:");
        System.out.println("  0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println(" ---+---+---");
        }
        System.out.println();
    }

    static boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }
        return board[row][col] == ' ';
    }

    static boolean checkWinner(char player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player &&
                board[i][1] == player &&
                board[i][2] == player) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player &&
                board[1][j] == player &&
                board[2][j] == player) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == player &&
            board[1][1] == player &&
            board[2][2] == player) {
            return true;
        }

        if (board[0][2] == player &&
            board[1][1] == player &&
            board[2][0] == player) {
            return true;
        }

        return false;
    }

    static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean askPlayAgain() {
        System.out.print("Do you want to play again? (y/n): ");
        char choice = sc.next().charAt(0);
        return choice == 'y' || choice == 'Y';
    }
}
