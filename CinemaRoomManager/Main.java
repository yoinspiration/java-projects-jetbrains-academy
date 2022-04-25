import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = in.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int cols = in.nextInt();

        CinemaRoomManager cinemaRoomManager = new CinemaRoomManager(rows, cols);

        cinemaRoomManager.showMenu();
    }
}
