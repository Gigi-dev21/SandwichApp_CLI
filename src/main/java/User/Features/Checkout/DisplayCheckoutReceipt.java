package User.Features.Checkout;

import User.Classes.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DisplayCheckoutReceipt {
    private final Order order;
    private final Inventory inventory;

    public DisplayCheckoutReceipt(Order order, Inventory inventory) {
        this.order = order;
        this.inventory = inventory;
    }

    public void displayReceipt() {
        System.out.println("\n========= 🧾 ORDER RECEIPT =========");

        // Date and Time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Date: " + LocalDateTime.now().format(formatter));

        // Customer Name
        System.out.println("Customer: " + (order.getCustomerName() == null ? "Guest" : order.getCustomerName()));
        System.out.println("------------------------------------");

        double sandwichSubtotal = 0.0;
        double drinkSubtotal = 0.0;
        double chipSubtotal = 0.0;

        // Sandwiches
        List<Sandwich> sandwiches = order.getSandwiches();
        if (!sandwiches.isEmpty()) {
            System.out.println("🥪 Sandwiches:");
            for (int i = 0; i < sandwiches.size(); i++) {
                Sandwich s = sandwiches.get(i);
                double unitPrice = s.calculatePrice(inventory);
                double totalPrice = s.getTotalPrice(inventory);
                sandwichSubtotal += totalPrice;

                System.out.printf("  %d) %dx %s\" %s  - $%.2f each%n",
                        i + 1, s.getQuantity(), s.getSize(), s.getName(), unitPrice);

                System.out.printf("     Bread: %s | Cheese: %s%n", s.getBread(), s.getCheese());
                if (s.hasExtraMeat()) System.out.print("     + Extra Meat\n");
                if (s.hasExtraCheese()) System.out.print("     + Extra Cheese\n");
                if (!s.getToppings().isEmpty())
                    System.out.println("     Toppings: " + String.join(", ", s.getToppings()));
                if (!s.getSauces().isEmpty())
                    System.out.println("     Sauces: " + String.join(", ", s.getSauces()));
                if (!s.getSides().isEmpty())
                    System.out.println("     Sides: " + String.join(", ", s.getSides()));
                if (s.isToasted()) System.out.println("     Toasted");

                System.out.printf("     ➤ Total: $%.2f%n%n", totalPrice);
            }
        }

        // Drinks
        List<Drink> drinks = order.getDrinks();
        if (!drinks.isEmpty()) {
            System.out.println("🥤 Drinks:");
            for (int i = 0; i < drinks.size(); i++) {
                Drink d = drinks.get(i);
                String size = d.getSize(); // assuming getSize() exists
                int quantity = d.getQuantity(); // assuming getQuantity() exists
                double unitPrice = d.getPrice(); // assuming getUnitPrice() exists
                double totalPrice = d.getTotalPrice();
                drinkSubtotal += d.getTotalPrice();
                System.out.printf("  %d) %dx %s %s - $%.2f each = $%.2f%n",
                        i + 1, quantity, size, d.getName(), unitPrice, totalPrice);
            }
            System.out.println();
        }

        // Chips
        List<Chips> chips = order.getChips();
        if (!chips.isEmpty()) {
            System.out.println("🍟 Chips:");
            for (int i = 0; i < chips.size(); i++) {
                Chips c = chips.get(i);
                int quantity = c.getQuantity(); // assuming getQuantity() exists
                double unitPrice = c.getPrice(); // assuming getUnitPrice() exists
                double totalPrice = c.getTotalPrice();
                chipSubtotal += c.getTotalPrice();
                System.out.printf("  %d) %dx %s - $%.2f each = $%.2f%n",
                        i + 1, quantity, c.getName(), unitPrice, totalPrice);
            }
            System.out.println();
        }

        // Subtotals
        System.out.println("------------------------------------");
        if (sandwichSubtotal > 0) System.out.printf("Subtotal Sandwiches: $%.2f%n", sandwichSubtotal);
        if (drinkSubtotal > 0) System.out.printf("Subtotal Drinks:     $%.2f%n", drinkSubtotal);
        if (chipSubtotal > 0) System.out.printf("Subtotal Chips:      $%.2f%n", chipSubtotal);



        double subtotal = sandwichSubtotal + drinkSubtotal + chipSubtotal;
        double tax = subtotal * order.getTax();
        double grandTotal = subtotal + tax;

        System.out.printf("Tax (7%%):            $%.2f%n", tax);
        System.out.println("------------------------------------");
        System.out.printf("TOTAL:                $%.2f%n", grandTotal);
//        System.out.println("=====================================");
//        System.out.println("  Thank you for your order!");
//        System.out.println("=====================================\n");
    }
}
