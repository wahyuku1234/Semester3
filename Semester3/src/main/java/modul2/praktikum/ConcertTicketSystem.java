package modul2.praktikum;

import java.util.Scanner;

public class ConcertTicketSystem {
    private int[][] seatPrices;
    private boolean[][] seatAvailability;
    private int totalRows;
    private int totalCols;
    private int seatAvailable;
    public ConcertTicketSystem(int rows, int cols){
        this.totalRows = rows;
        this.totalCols = cols;
        this.seatAvailable = rows * cols;
        initializeSeatPricesAndAvailability();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of rows in the concert hall: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of columns in the concert hall:");
        int cols = scanner.nextInt();

        ConcertTicketSystem ticketSystem = new ConcertTicketSystem(rows, cols);

        System.out.println("Welcome to the Concert Ticket System! ");
        ticketSystem.showSeatPrices();

        char choice;
        do {
            System.out.println("\nOptions:");
            System.out.println("1. View Available Seats");
            System.out.println("2. Buy Ticket");
            System.out.println("3. View Available Seats Count");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1/2/3/4): ");
            choice = scanner.next().charAt(0);

            switch (choice){
                case '1':
                    ticketSystem.showAvailableSeats();
                    break;
                case '2':
                    System.out.print("Enter row number: ");
                    int row = scanner.nextInt();
                    System.out.print("Enter column number:");
                    int col = scanner.nextInt();
                    ticketSystem.buyTicket(row, col);
                    break;
                case '3':
                    System.out.println("Available Seats Count: " + ticketSystem.seatAvailability);
                    break;
                case '4':
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != '4');

        scanner.close();
    }
    private void initializeSeatPricesAndAvailability(){
        seatPrices = new int[totalRows][totalCols];
        seatAvailability = new boolean[totalRows][totalCols];

        for (int i=0;i<totalRows;i++){
            for (int j=0;j<totalCols;j++){
                seatPrices[i][j] = 50;
                seatAvailability[i][j] = true;
            }
        }
    }
    public void showSeatPrices(){
        System.out.println("Seat Prices: ");
        for (int i=0;i<totalRows;i++){
            for (int j=0;j<totalCols;j++){
                System.out.println("Row " + (i + 1) + ", Column " + (j + 1) + ": $" + seatPrices[i][j]);
            }
        }
    }
    public void showAvailableSeats(){
        System.out.println("Available Seats:");
        for (int i=0;i<totalRows;i++){
            for (int j=0;j<totalCols;j++){
                if (seatAvailability[i][j]){
                    System.out.println("[ " + (i + 1) + "," + (j + 1) + " ] ");
                } else {
                    System.out.print(" [ X ] ");
                }
            }
            System.out.println();
        }
    }
    public void buyTicket(int row, int col){
        boolean validSeat = row >= 1 && row <= totalRows && col >= 1 && col <= totalCols && seatAvailability[row - 1][col - 1];
        if (validSeat){
            int price = seatPrices[row - 1][col - 1];
            System.out.println("You have purchased a ticket at row " + row + " and column" + col + " for $" + price);
            seatAvailability[row - 1][col - 1] = false;
            seatAvailable--;
        } else {
            System.out.println("Invalid seat selection or seat is already taken.");
        }
    }
}
