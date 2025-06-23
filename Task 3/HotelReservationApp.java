import java.util.Scanner;

public class HotelReservationApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();
        System.out.println("=== Welcome to Java Hotel Reservation System ===");

        while (true) {
            System.out.println("\n1. View Available Rooms\n2. Book Room\n3. Cancel Booking\n4. View Bookings\n5. Exit");
            System.out.print("Select option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter category (Standard, Deluxe, Suite): ");
                    hotel.showAvailableRooms(scanner.nextLine());
                    break;
                case "2":
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter room category: ");
                    String cat = scanner.nextLine();
                    Booking booking = hotel.bookRoom(name, cat);
                    if (booking != null) {
                        System.out.println("Booking successful! ID: " + booking.getBookingId());
                        System.out.println("Payment: Success ✔️");
                    } else {
                        System.out.println("No available rooms in that category.");
                    }
                    break;
                case "3":
                    System.out.print("Enter Booking ID to cancel: ");
                    boolean canceled = hotel.cancelBooking(scanner.nextLine());
                    System.out.println(canceled ? "Booking canceled." : "Booking ID not found.");
                    break;
                case "4":
                    hotel.viewAllBookings();
                    break;
                case "5":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
