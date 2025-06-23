import java.io.*;
import java.util.*;

public class Hotel {
    private List<Room> rooms = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();
    private final String FILE = "bookings.ser";

    public Hotel() {
        // Sample rooms
        for (int i = 1; i <= 3; i++) rooms.add(new Room(100 + i, "Standard"));
        for (int i = 1; i <= 2; i++) rooms.add(new Room(200 + i, "Deluxe"));
        rooms.add(new Room(301, "Suite"));
        loadBookings();
    }

    public void showAvailableRooms(String category) {
        System.out.println("Available " + category + " Rooms:");
        for (Room r : rooms) {
            if (r.getCategory().equalsIgnoreCase(category) && r.isAvailable()) {
                System.out.println(r);
            }
        }
    }

    public Booking bookRoom(String customerName, String category) {
        for (Room r : rooms) {
            if (r.getCategory().equalsIgnoreCase(category) && r.isAvailable()) {
                r.setAvailable(false);
                Booking booking = new Booking(customerName, r);
                bookings.add(booking);
                saveBookings();
                return booking;
            }
        }
        return null;
    }

    public boolean cancelBooking(String bookingId) {
        Iterator<Booking> iterator = bookings.iterator();
        while (iterator.hasNext()) {
            Booking b = iterator.next();
            if (b.getBookingId().equals(bookingId)) {
                b.getRoom().setAvailable(true);
                iterator.remove();
                saveBookings();
                return true;
            }
        }
        return false;
    }

    public void viewAllBookings() {
        System.out.println("\nCurrent Bookings:");
        for (Booking b : bookings) {
            System.out.println(b);
        }
    }

    private void saveBookings() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(bookings);
        } catch (IOException e) {
            System.out.println("Error saving bookings.");
        }
    }

    private void loadBookings() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
            bookings = (List<Booking>) in.readObject();
            for (Booking b : bookings) {
                b.getRoom().setAvailable(false);
                rooms.add(b.getRoom()); // Ensure room reference exists
            }
        } catch (Exception e) {
            bookings = new ArrayList<>();
        }
    }
}
