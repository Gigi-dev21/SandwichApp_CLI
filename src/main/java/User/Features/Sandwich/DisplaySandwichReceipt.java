package User.Features.Sandwich;

import User.Classes.Inventory;
import User.Classes.Order;
import User.Classes.Sandwich;

import java.util.List;

public class DisplaySandwichReceipt {
    private final Order order;

    public DisplaySandwichReceipt(Order order) {
        this.order = order;
    }

    void displaySandwichSummary(Sandwich sandwich, Inventory inventory) {
        String size = sandwich.getSize();
        int quantity = sandwich.getQuantity();

        System.out.println("\n======= Sandwich Summary =======");

        double breadPrice = inventory.bread.get(sandwich.getBread()).get(size);
        double meatPrice = inventory.meat.get(sandwich.getMeat()).get(size);
        double cheesePrice = inventory.cheese.get(sandwich.getCheese()).get(size);

        System.out.printf("%-20s %s ( $%.2f each )%n", "Bread", sandwich.getBread(), breadPrice);
        System.out.printf("%-20s %s ( $%.2f each )%n", "Meat", sandwich.getMeat(), meatPrice);
        if (sandwich.hasExtraMeat()) {
            double extraMeatPrice = inventory.meat.get("Extra meat").get(size);
            System.out.printf("%-20s Yes ( +$%.2f )%n", "  Extra Meat", extraMeatPrice);
        }
        System.out.printf("%-20s %s ( $%.2f each )%n", "Cheese", sandwich.getCheese(), cheesePrice);
        if (sandwich.hasExtraCheese()) {
            double extraCheesePrice = inventory.cheese.get("extra cheese").get(size);
            System.out.printf("%-20s Yes ( +$%.2f )%n", "  Extra Cheese", extraCheesePrice);
        }

        System.out.printf("%-20s %s%n", "Toppings", sandwich.getToppings().isEmpty() ? "None" : String.join(", ", sandwich.getToppings()));
        System.out.printf("%-20s %s%n", "Sauces", sandwich.getSauces().isEmpty() ? "None" : String.join(", ", sandwich.getSauces()));
        System.out.printf("%-20s %s%n", "Sides", sandwich.getSides().isEmpty() ? "None" : String.join(", ", sandwich.getSides()));
        if (sandwich.isToasted()) System.out.printf("%-20s %s%n", "Toasted", "Yes");

        double unitPrice = sandwich.calculatePrice(inventory);
        double totalPrice = unitPrice * quantity;
        System.out.println("--------------------------------");
        if (quantity > 1) {
            System.out.printf("%-20s %d x $%.2f = $%.2f%n", "Price Total", quantity, unitPrice, totalPrice);
        } else {
            System.out.printf("%-20s $%.2f%n", "Price Total", unitPrice);
        }
        System.out.println("================================");
    }


    public void displayTotalAddedSandwich(Inventory inventory) {
        System.out.println("\n🧾 Sandwiches in your order so far:");
        List<Sandwich> sandwichesInOrder = order.getSandwiches();

        if (sandwichesInOrder.isEmpty()) {
            System.out.println("   (none)");
        } else {
            double totalSandwichPrice = 0.0;

            for (int i = 0; i < sandwichesInOrder.size(); i++) {
                Sandwich s = sandwichesInOrder.get(i);
                double price = s.getTotalPrice(inventory);
                System.out.printf("   %d) %-20s ( %d x $%.2f = $%.2f )%n",
                        i + 1,
                        s.getName() + s.getSize() + "\"",
                        s.getQuantity(),
                        s.calculatePrice(inventory),
                        price);

                totalSandwichPrice += price;
            }
            System.out.println("------------------------------------");
            System.out.printf("💰 Total for sandwiches:     $%.2f%n", totalSandwichPrice);
        }
    }

}
