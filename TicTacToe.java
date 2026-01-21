import java.util.Scanner;

public class TicTacToe {

    static char[][] board = new char[3][3];
    static Scanner sc = new Scanner(System.in);

    static String player1;
    static String player2;

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("==                             ==");
        System.out.println("==      TIC TAC TOE GAME       ==");
        System.out.println("==                             ==");
        System.out.println("=================================");

        // Take player names
        System.out.print("Enter Player 1 name (X): ");
        player1 = sc.nextLine();

        System.out.print("Enter Player 2 name (O): ");
        player2 = sc.nextLine();

        boolean playAgain = true;

        while (playAgain) {
            initializeBoard();
            playGame();
            playAgain = askPlayAgain();
        }

        System.out.println("{{-*- Thanks for playing! -*-}}");
    }

    static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    static void playGame() {

        char currentSymbol = 'X';
        String currentPlayer = player1;

        while (true) {
            printBoard();
            System.out.println(currentPlayer + "'s turn (" + currentSymbol + ")");

            int row, col;

            while (true) {
                System.out.print("Enter row and column: ");
                row = sc.nextInt();
                col = sc.nextInt();

                if (isValidMove(row, col)) {
                    board[row][col] = currentSymbol;
                    break;
                } else {
                    System.out.println("Invalid move! Try again.");
                }
            }

            if (checkWinner(currentSymbol)) {
                printBoard();
                System.out.println(" ** " + currentPlayer + " wins! ** ");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println(" ** It's a draw! ** ");
                break;
            }

            // Switch player
            if (currentSymbol == 'X') {
                currentSymbol = 'O';
                currentPlayer = player2;
            } else {
                currentSymbol = 'X';
                currentPlayer = player1;
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
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    static boolean checkWinner(char symbol) {

        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol &&
                board[i][1] == symbol &&
                board[i][2] == symbol)
                return true;
        }

        for (int j = 0; j < 3; j++) {
            if (board[0][j] == symbol &&
                board[1][j] == symbol &&
                board[2][j] == symbol)
                return true;
        }

        if (board[0][0] == symbol &&
            board[1][1] == symbol &&
            board[2][2] == symbol)
            return true;

        if (board[0][2] == symbol &&
            board[1][1] == symbol &&
            board[2][0] == symbol)
            return true;

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
        System.out.print("Play again? (y/n): ");
        char choice = sc.next().charAt(0);
        sc.nextLine(); // clear buffer
        return choice == 'y' || choice == 'Y';
    }
}
