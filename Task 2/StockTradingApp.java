import java.io.*;
import java.util.Scanner;

public class StockTradingApp {
    private static final String DATA_FILE = "portfolio.ser";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Market market = new Market();
        User user = loadUser();

        if (user == null) {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            user = new User(name, 10000);
            System.out.println("New account created with $10,000.");
        }

        while (true) {
            System.out.println("\n=== Welcome " + user.getName() + " ===");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. Portfolio Summary");
            System.out.println("5. Transaction History");
            System.out.println("6. Save & Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    market.displayStocks();
                    break;
                case 2:
                    System.out.print("Enter symbol: ");
                    String buySymbol = scanner.nextLine().toUpperCase();
                    System.out.print("Quantity: ");
                    int buyQty = Integer.parseInt(scanner.nextLine());
                    Stock buyStock = market.getStock(buySymbol);
                    if (buyStock != null && user.getPortfolio().buyStock(buyStock, buyQty)) {
                        System.out.println("Stock purchased.");
                    } else {
                        System.out.println("Purchase failed.");
                    }
                    break;
                case 3:
                    System.out.print("Enter symbol: ");
                    String sellSymbol = scanner.nextLine().toUpperCase();
                    System.out.print("Quantity: ");
                    int sellQty = Integer.parseInt(scanner.nextLine());
                    Stock sellStock = market.getStock(sellSymbol);
                    if (sellStock != null && user.getPortfolio().sellStock(sellStock, sellQty)) {
                        System.out.println("Stock sold.");
                    } else {
                        System.out.println("Sell failed.");
                    }
                    break;
                case 4:
                    user.getPortfolio().printSummary(market);
                    System.out.printf("Total Value: $%.2f\n", user.getPortfolio().getTotalValue(market));
                    break;
                case 5:
                    user.getPortfolio().printHistory();
                    break;
                case 6:
                    saveUser(user);
                    System.out.println("Saved. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static User loadUser() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            return (User) ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    private static void saveUser(User user) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(user);
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }
}
