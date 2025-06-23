import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {
    private String stockSymbol;
    private int quantity;
    private double price;
    private boolean isBuy;
    private LocalDateTime timestamp;

    public Transaction(String stockSymbol, int quantity, double price, boolean isBuy) {
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.isBuy = isBuy;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return (isBuy ? "BUY" : "SELL") + " " + quantity + " of " + stockSymbol + " at $" + price + " on " + timestamp;
    }
}
