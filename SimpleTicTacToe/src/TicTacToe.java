import java.util.Scanner;

class TicTacToe {

    private char[] cells;
    private Player playerX = new Player('X');
    private Player playerO = new Player('O');
    
    private final int LENGTH = 3;
    private final int COUNT = LENGTH * LENGTH;

    protected TicTacToe() {
        cells = new char[COUNT];
        for (int i = 0; i < COUNT; i++) {
            cells[i] = ' ';
        }
    }

    protected void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < LENGTH; i++) {
            System.out.print("|");
            for (int j = 0; j < LENGTH; j++) {
                System.out.print(" " + cells[i * LENGTH + j]);
            }
            System.out.println(" |");
        }
        System.out.println("---------");
    }

    protected void start() {

        System.out.print("""
                ---------
                |       |
                |       |
                |       |
                ---------
                """);

        int turn = 0;
        while (true) {
            int index = getCoordinates();

            if (turn % 2 == 0) {
                cells[index] = playerX.content;
                playerX.moves++;
                printBoard();

                if (playerX.moves > 2 && checkWin(playerX)) {
                    System.out.println("X wins");
                    return;
                }

                if (playerX.moves > 4) {
                    System.out.println("Draw");
                    return;
                }
            } else {
                cells[index] = playerO.content;
                playerO.moves++;
                printBoard();

                if (playerO.moves > 2 && checkWin(playerO)) {
                    System.out.println("O wins");
                    return;
                }
            }
            turn++;
        }
    }

    protected int getCoordinates() {

        while (true) {
            System.out.print("Enter the coordinates: ");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String[] coordinates = input.split(" ");

            try {
                int row=Integer.parseInt(coordinates[0]) - 1;
                int col=Integer.parseInt(coordinates[1]) - 1;

                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    int index = row * LENGTH + col;
                    if (cells[index] != ' ') {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        return index;
                    }
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("You should enter numbers!");
            }
        }
    }

    protected boolean checkWin(Player player) {

        char goal = player.content;

        int pattern = 0;
        for (int i = 0; i < COUNT; ++i) {
            if (cells[i] == goal) {
                pattern |= 1 << i;
            }
        }

        int[] winningPatterns = {
                0B000_000_111, 0B000_111_000, 0B111_000_000,
                0B001_001_001, 0B010_010_010, 0B100_100_100,
                0B100_010_001, 0B001_010_100
        };

        for (int winningPattern: winningPatterns) {
            if ((winningPattern & pattern) == winningPattern) {
                return true;
            }
        }

        return false;
    }
}
