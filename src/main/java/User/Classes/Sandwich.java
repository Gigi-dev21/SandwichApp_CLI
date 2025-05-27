package User.Classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sandwich {
    protected String name;
    protected String size;
    protected String bread;
    protected String meat;
    protected boolean extraMeat;
    protected String cheese;
    protected boolean extraCheese;
    protected List<String> toppings;
    protected List<String> sauces;
    protected List<String> sides;
    protected boolean toasted;
    protected int quantity;  // New

    // Constructor
    public Sandwich(String size, String bread, String meat, boolean extraMeat,
                    String cheese, boolean extraCheese,
                    List<String> toppings, List<String> sauces, List<String> sides,
                    boolean toasted, int quantity) {
        this.size = size;
        this.bread = bread;
        this.meat = meat;
        this.extraMeat = extraMeat;
        this.cheese = cheese;
        this.extraCheese = extraCheese;
        this.toppings = toppings;
        this.sauces = sauces;
        this.sides = sides;
        this.toasted = toasted;
        this.quantity = quantity;
    }

    public Sandwich() {

    }

    // Getters
    public String getSize() {
        return size;
    }

    public String getBread() {
        return bread;
    }

    public String getMeat() {
        return meat;
    }

    public boolean hasExtraMeat() {
        return extraMeat;
    }

    public String getCheese() {
        return cheese;
    }

    public boolean hasExtraCheese() {
        return extraCheese;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public List<String> getSauces() {
        return sauces;
    }

    public List<String> getSides() {
        return sides;
    }

    public boolean isToasted() {
        return toasted;
    }

    public int getQuantity() {
        return quantity;
    }

//    public String getName() {
//        return String.format(meat + " Sandwich  ");
//    }

    public String getName() {
        return name != null ? name : meat + " Sandwich";
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Price for one
    public double calculatePrice(Inventory inventory) {
        double total = 0.0;

        // Bread
        if (inventory.bread.containsKey(bread)) {
            total += inventory.bread.get(bread).get(size);
        }

        // Meat
        if (inventory.meat.containsKey(meat)) {
            total += inventory.meat.get(meat).get(size);
        }

        if (extraMeat && inventory.meat.containsKey("Extra meat")) {
            total += inventory.meat.get("Extra meat").get(size);
        }

        // Cheese
        if (inventory.cheese.containsKey(cheese)) {
            total += inventory.cheese.get(cheese).get(size);
        }

        if (extraCheese && inventory.cheese.containsKey("extra cheese")) {
            total += inventory.cheese.get("extra cheese").get(size);
        }

        // Sides (if they have price, you can add logic here)

        return total;
    }

    // Price for all quantities
    public double getTotalPrice(Inventory inventory) {
        return calculatePrice(inventory) * quantity;
    }

    @Override
    public String toString() {
        return String.format("%d x %s sandwich on %s bread with %s%s, %s%s, %s, toasted: %b",
                quantity,
                size,
                bread,
                meat,
                extraMeat ? " (extra)" : "",
                cheese,
                extraCheese ? " (extra)" : "",
                toppings,
                toasted);
    }


}