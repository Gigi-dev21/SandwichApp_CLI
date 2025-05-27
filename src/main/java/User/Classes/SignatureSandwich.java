package User.Classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SignatureSandwich extends Sandwich {

    public SignatureSandwich() {
        super();
    }

    public SignatureSandwich(String size, String bread, String meat, String cheese,
                             boolean toasted, List<String> toppings, List<String> sauces) {
        this.size = size;
        this.bread = bread;
        this.meat = meat;
        this.cheese = cheese;
        this.toasted = toasted;
        this.toppings.addAll(toppings);
        this.sauces.addAll(sauces);
    }

    public Sandwich toSandwich(int quantity, String name) {
        Sandwich s = new Sandwich(
                this.size,
                this.bread,
                this.meat,
                this.extraMeat,
                this.cheese,
                this.extraCheese,
                this.toppings,
                this.sauces,
                this.sides,
                this.toasted,
                quantity
        );
        s.setName(name);  // <-- Set the name here!
        return s;
    }


    @Override
    public String toString() {
        return String.format("Signature Sandwich (%s): %s sandwich with %s%s, %s%s, toppings: %s, toasted: %b",
                size,
                bread,
                meat,
                extraMeat ? " (extra)" : "",
                cheese,
                extraCheese ? " (extra)" : "",
                toppings,
                toasted
        );
    }

    public void displayDetails() {
        System.out.printf("   Size & Bread: %s\" %s%n", this.size, this.bread);
        System.out.printf("   Meat: %s%s%n", this.meat, this.extraMeat ? " (extra)" : "");
        System.out.printf("   Cheese: %s%s%n", this.cheese, this.extraCheese ? " (extra)" : "");

        System.out.printf("   Toppings: %s%n",
                (this.toppings != null && !this.toppings.isEmpty()) ? String.join(", ", this.toppings) : "none");

        System.out.printf("   Sauces: %s%n",
                (this.sauces != null && !this.sauces.isEmpty()) ? String.join(", ", this.sauces) : "none");

        System.out.printf("   Sides: %s%n",
                (this.sides != null && !this.sides.isEmpty()) ? String.join(", ", this.sides) : "none");

        System.out.printf("   Toasted: %s%n", this.toasted ? "Yes" : "No");
        
    }
    public SignatureSandwich cloneSandwich() {
        return new SignatureSandwich(size, bread, meat, cheese, toasted, new ArrayList<>(toppings), new ArrayList<>(sauces));
    }
}
