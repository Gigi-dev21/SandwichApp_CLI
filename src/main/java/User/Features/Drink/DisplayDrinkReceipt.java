package User.Features.Drink;

import User.Classes.Drink;

import java.util.List;

public class DisplayDrinkReceipt {
    public void displayDrinkReceipt(List<Drink> drinks) {
        if (drinks == null || drinks.isEmpty()) {
            System.out.println("No drinks in order.");
            return;
        }

        System.out.println("\n=========== Drink Receipt ===========");
        double total = 0;

        for (Drink drink : drinks) {
            System.out.printf("• %-18s %-6s  $%.2f%n", drink.getName(), drink.getSize(), drink.getPrice());
            total += drink.getPrice();
        }

        System.out.println("-------------------------------------");
        System.out.printf("%-25s $%.2f%n", "Drinks Total", total);
        System.out.println("=====================================\n");
    }
}
