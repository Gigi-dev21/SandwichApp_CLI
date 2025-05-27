package Admin.ViewTransactions.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
    private String customerName;
    private Order order;
    private double totalAmount;
    private String paymentMethod;
    private long timestamp;

    public String getCustomerName() { return customerName; }
    public Order getOrder() { return order; }
    public double getTotalAmount() { return totalAmount; }
    public String getPaymentMethod() { return paymentMethod; }
    public long getTimestamp() { return timestamp; }

    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setOrder(Order order) { this.order = order; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
