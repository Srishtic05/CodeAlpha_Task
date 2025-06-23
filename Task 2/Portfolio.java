import java.io.Serializable;
import java.util.*;

public class Portfolio implements Serializable {
    private double cash;
    private Map<String, Integer> holdings;
    private List<Transaction> history;

    public Portfolio(double initialCash) {
        this.cash = initialCash;
        this.holdings = new HashMap<>();
        this.history = new ArrayList<>();
    }

    public boolean buyStock(Stock stock, int qty) {
        double totalCost = stock.getPrice() * qty;
        if (cash >= totalCost) {
            cash -= totalCost;
            holdings.put(stock.getSymbol(), holdings.getOrDefault(stock.getSymbol(), 0) + qty);
            history.add(new Transaction(stock.getSymbol(), qty, stock.getPrice(), true));
            return true;
        }
        return false;
    }

    public boolean sellStock(Stock stock, int qty) {
        int heldQty = holdings.getOrDefault(stock.getSymbol(), 0);
        if (heldQty >= qty) {
            cash += qty * stock.getPrice();
            holdings.put(stock.getSymbol(), heldQty - qty);
            history.add(new Transaction(stock.getSymbol(), qty, stock.getPrice(), false));
            return true;
        }
        return false;
    }

    public void printSummary(Market market) {
        System.out.printf("Cash: $%.2f\n", cash);
        System.out.println("Holdings:");
        for (String symbol : holdings.keySet()) {
            int qty = holdings.get(symbol);
            double value = market.getStock(symbol).getPrice() * qty;
            System.out.printf("%s: %d shares - Market Value: $%.2f\n", symbol, qty, value);
        }
    }

    public void printHistory() {
        System.out.println("Transaction History:");
        for (Transaction t : history) {
            System.out.println(t);
        }
    }

    public double getTotalValue(Market market) {
        double total = cash;
        for (String symbol : holdings.keySet()) {
            int qty = holdings.get(symbol);
            total += market.getStock(symbol).getPrice() * qty;
        }
        return total;
    }

    public double getCash() {
        return cash;
    }
}
