class TicTacToe {

    private char[] cells;
    private final int LENGTH = 3;

    protected TicTacToe(char[] cells) {
        this.cells = cells;
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
}
