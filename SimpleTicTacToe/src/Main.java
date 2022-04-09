import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        System.out.print("Enter cells: ");
        Scanner in = new Scanner(System.in);
        char[] cells = in.nextLine().toCharArray();
        TicTacToe game = new TicTacToe(cells);

        game.printBoard();
    }
}
