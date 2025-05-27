package Admin.ViewTransactions.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    private String customerName;  // because your JSON has it inside order
    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Chip> chips;

    public String getCustomerName() { return customerName; }
    public List<Sandwich> getSandwiches() { return sandwiches; }
    public List<Drink> getDrinks() { return drinks; }
    public List<Chip> getChips() { return chips; }

    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setSandwiches(List<Sandwich> sandwiches) { this.sandwiches = sandwiches; }
    public void setDrinks(List<Drink> drinks) { this.drinks = drinks; }
    public void setChips(List<Chip> chips) { this.chips = chips; }
}
