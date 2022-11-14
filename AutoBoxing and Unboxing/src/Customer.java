import java.util.ArrayList;

public class Customer {
    private String name;
    private ArrayList<Double> transactions = new ArrayList<Double>();

    public Customer(String name, double initialAmount) {
        this.name = name;
        transactions.add(initialAmount);
    }
    public String getName() {
        return name;
    }
    public ArrayList<Double> getTransactions() {
        return transactions;
    }
    public void setName(String name) {
        this.name = name;
    }
}
