package Admin.ViewTransactions;

import java.util.List;

public class FullTransactionRecord {
    private String customerName;
    private Order order;
    private double totalAmount;
    private String paymentMethod;
    private long timestamp;

    // getters and setters
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}

class Order {
    private String customerName;
    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Chip> chips;
    private double tax;

    // getters and setters
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public List<Sandwich> getSandwiches() { return sandwiches; }
    public void setSandwiches(List<Sandwich> sandwiches) { this.sandwiches = sandwiches; }
    public List<Drink> getDrinks() { return drinks; }
    public void setDrinks(List<Drink> drinks) { this.drinks = drinks; }
    public List<Chip> getChips() { return chips; }
    public void setChips(List<Chip> chips) { this.chips = chips; }
    public double getTax() { return tax; }
    public void setTax(double tax) { this.tax = tax; }
}

class Sandwich {
    private String name;
    private String size;
    private String bread;
    private String meat;
    private String cheese;
    private List<String> toppings;
    private List<String> sauces;
    private List<String> sides;
    private boolean toasted;
    private int quantity;

    // getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    public String getBread() { return bread; }
    public void setBread(String bread) { this.bread = bread; }
    public String getMeat() { return meat; }
    public void setMeat(String meat) { this.meat = meat; }
    public String getCheese() { return cheese; }
    public void setCheese(String cheese) { this.cheese = cheese; }
    public List<String> getToppings() { return toppings; }
    public void setToppings(List<String> toppings) { this.toppings = toppings; }
    public List<String> getSauces() { return sauces; }
    public void setSauces(List<String> sauces) { this.sauces = sauces; }
    public List<String> getSides() { return sides; }
    public void setSides(List<String> sides) { this.sides = sides; }
    public boolean isToasted() { return toasted; }
    public void setToasted(boolean toasted) { this.toasted = toasted; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}

class Drink {
    private String name;
    private double price;
    private int quantity;
    private String size;
    private double totalPrice;

    // getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
}

class Chip {
    private String name;
    private double price;
    private int quantity;
    private double totalPrice;

    // getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
}
