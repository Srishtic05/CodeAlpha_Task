import java.io.Serializable;
import java.util.UUID;

public class Booking implements Serializable {
    private String bookingId;
    private String customerName;
    private Room room;

    public Booking(String customerName, Room room) {
        this.bookingId = UUID.randomUUID().toString().substring(0, 8);
        this.customerName = customerName;
        this.room = room;
    }

    public String getBookingId() { return bookingId; }
    public String getCustomerName() { return customerName; }
    public Room getRoom() { return room; }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + ", Customer: " + customerName + ", " + room;
    }
}
