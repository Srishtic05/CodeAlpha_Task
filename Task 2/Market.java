import java.util.*;

public class Market {
    private Map<String, Stock> stocks;

    public Market() {
        stocks = new HashMap<>();
        stocks.put("AAPL", new Stock("AAPL", 180.25));
        stocks.put("GOOGL", new Stock("GOOGL", 2750.10));
        stocks.put("TSLA", new Stock("TSLA", 695.50));
        stocks.put("AMZN", new Stock("AMZN", 3305.50));
    }

    public void displayStocks() {
        System.out.println("Available Stocks:");
        for (Stock s : stocks.values()) {
            System.out.println(s);
        }
    }

    public Stock getStock(String symbol) {
        return stocks.get(symbol.toUpperCase());
    }
}
