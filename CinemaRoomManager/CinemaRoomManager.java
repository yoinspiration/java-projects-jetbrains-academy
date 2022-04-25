import java.util.Scanner;

class CinemaRoomManager {

    private final int rows;
    private final int cols;
    private final int totalSeats;
    private char[] seats;
    private int purchasedTickets = 0;
    private int currentIncome = 0;
    private final int totalIncome;

    protected CinemaRoomManager(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        totalSeats = rows * cols;
        seats = new char[totalSeats];
        for (int i = 0; i < totalSeats; i++) {
            seats[i] = 'S';
        }

        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else {
            totalIncome = (rows / 2 * 10 + (rows - rows / 2) * 8) * cols;
        }
    }

    Scanner in = new Scanner(System.in);

    protected void showMenu() {

        while (true) {
            System.out.print("""
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit
                """);

            int choice = in.nextInt();
            switch (choice) {
                case 1 -> showSeats();
                case 2 -> bookATicket();
                case 3 -> statistics();
                case 0 -> {
                    return;
                }
            }
        }
    }

    protected void showSeats() {
        System.out.println("Cinema:");

        System.out.print("  1");
        for (int i = 1; i < cols; i++) {
            System.out.print(" " + (i+1));
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < cols; j++) {
                System.out.print(" " + seats[i*cols+j]);
            }
            System.out.println();
        }
    }

    protected void bookATicket() {
        while (true) {
            System.out.println("Enter a row number:");
            int row = in.nextInt();
            System.out.println("Enter a seat number in that row:");
            int col = in.nextInt();

            int index = (row-1)*cols + col-1;
            try {
                if (seats[index] == 'B') {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    seats[index] = 'B';
                    purchasedTickets++;

                    int ticketPrice;
                    if (totalSeats > 60 && row > rows / 2) {
                        ticketPrice = 8;
                    } else {
                        ticketPrice = 10;
                    }
                    currentIncome += ticketPrice;

                    System.out.println("Ticket price: $" + ticketPrice);
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong input!");
            }
        }
    }

    protected void statistics() {

        double ticketPurchasePercentage = (double) purchasedTickets / (double) totalSeats * 100.0;
        System.out.printf("""
                Number of purchased tickets: %d
                Percentage: %.2f%%
                Current income: $%d
                Total income: $%d
                """, purchasedTickets, ticketPurchasePercentage, currentIncome, totalIncome);
    }
}
