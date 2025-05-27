package Admin.ViewTransactions;

import java.util.List;

public class TransactionRecord {
    private String customerName;
    private List<String> sandwichNames;
    private List<String> drinkNames;
    private List<String> chipNames;
    private double totalAmount;
    private String paymentMethod;
    private long timestamp;

    // Constructor
    public TransactionRecord(String customerName, List<String> sandwichNames, List<String> drinkNames,
                             List<String> chipNames, double totalAmount, String paymentMethod, long timestamp) {
        this.customerName = customerName;
        this.sandwichNames = sandwichNames;
        this.drinkNames = drinkNames;
        this.chipNames = chipNames;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.timestamp = timestamp;
    }

    // Getters & setters
    public String getCustomerName() { return customerName; }
    public List<String> getSandwichNames() { return sandwichNames; }
    public List<String> getDrinkNames() { return drinkNames; }
    public List<String> getChipNames() { return chipNames; }
    public double getTotalAmount() { return totalAmount; }
    public String getPaymentMethod() { return paymentMethod; }
    public long getTimestamp() { return timestamp; }

    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setSandwichNames(List<String> sandwichNames) { this.sandwichNames = sandwichNames; }
    public void setDrinkNames(List<String> drinkNames) { this.drinkNames = drinkNames; }
    public void setChipNames(List<String> chipNames) { this.chipNames = chipNames; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
