import java.io.*;

public class User implements Serializable {
    private String name;
    private Portfolio portfolio;

    public User(String name, double initialCash) {
        this.name = name;
        this.portfolio = new Portfolio(initialCash);
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public String getName() {
        return name;
    }
}
