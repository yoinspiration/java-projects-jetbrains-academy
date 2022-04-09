import java.util.Scanner;

class TicTacToe {

    private char[] cells;
    private final int LENGTH = 3;

    protected TicTacToe(char[] cells) {
        this.cells = cells;
    }

    protected void start() {
        printBoard();
        firstMove();
        printBoard();
    }

    protected void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < LENGTH; ++i) {
            System.out.print("|");
            for (int j = 0; j < LENGTH; ++j) {
                System.out.print(" " + cells[i*LENGTH+j]);
            }
            System.out.println(" |");
        }
        System.out.println("---------");
    }

    protected void firstMove() {
        while (true) {
            System.out.print("Enter the coordinates: ");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String[] coordinates = input.split(" ");

            try {
                int row = Integer.parseInt(coordinates[0]) - 1;
                int col = Integer.parseInt(coordinates[1]) - 1;

                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    int index = row * LENGTH + col;
                    if (cells[index] != '_') {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        cells[index] = 'X';
                        break;
                    }
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("You should enter numbers!");
            }
        }
    }

    protected GameState processGameState() {
        int xCount = 0;
        int oCount = 0;

        for (char cell : cells) {
            if (cell == 'X') {
                ++xCount;
            } else if (cell == 'O') {
                ++oCount;
            }
        }

        if (Math.abs(xCount - oCount) > 1) {
            return GameState.IMPOSSIBLE;
        }

        boolean xWin = checkWin(true);
        boolean oWin = checkWin(false);

        if (xWin && oWin) {
            return GameState.IMPOSSIBLE;
        }

        if (xWin) {
            return GameState.X_WIN;
        }

        if (oWin) {
            return GameState.O_WIN;
        }

        final int COUNT = LENGTH * LENGTH;
        if (xCount + oCount < COUNT) {
            return GameState.GAME_NOT_FINISHED;
        } else {
            return GameState.DRAW;
        }

    }

    protected boolean checkWin(boolean cross) {
        char goal = cross ? 'X' : 'O';

        int pattern = 0;
        final int COUNT = LENGTH * LENGTH;
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

    protected void printGameState() {
        GameState state = processGameState();

        switch (state) {
            case IMPOSSIBLE -> System.out.println("Impossible");
            case X_WIN -> System.out.println("X wins");
            case O_WIN -> System.out.println("O wins");
            case GAME_NOT_FINISHED -> System.out.println("Game not finished");
            case DRAW -> System.out.println("Draw");
        }
    }

}
