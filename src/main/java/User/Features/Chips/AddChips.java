package User.Features.Chips;

import User.Classes.Chips;
import User.Classes.Inventory;
import User.Classes.Order;
import User.Controllers.Controllers;

import java.util.List;

public class AddChips {
    Controllers controllers = new Controllers();
    Inventory inventory = Inventory.loadFromFile("Inventory.json");
    private final Order order;

    public AddChips(Order order) {
        this.order = order;
    }

    public void displayChips() {
        if (inventory == null) {
            System.out.println("Inventory not loaded.");
            return;
        }

        while (true) {
            System.out.println("\n\033[0;33m=======================================");
            System.out.println("              Select Chips             ");
            System.out.println("=======================================\033[0m");

            // Get current chips in order
            List<Chips> chipsInOrder = order.getChips();

            // List of chip names from inventory
            List<String> chipsNames = List.copyOf(inventory.chips.keySet());

            // User selects chip
            String selectedChipName = controllers.selectOneOption("Choose a chip:", chipsNames);

            // Get price of selected chip
            double price = inventory.chips.get(selectedChipName);

            // Get quantity from user
            int quantity = controllers.getIntegerInput("\nHow many would you like? ");

            // Display selected item and calculated total
            System.out.printf("\033[0;36m\n%d x %s ($%.2f each) - $%.2f\033[0m%n",
                    quantity, selectedChipName, price, quantity * price);

            // Confirm adding to order
            if (controllers.getYesNo("\nAdd this chip to your order? (Y/N): ")) {
                Chips chip = new Chips(selectedChipName, price, quantity);
                order.addChips(chip);
            }

            // Print chips added so far
            System.out.println("\n🧾 Chips in your order so far:");
            if (chipsInOrder.isEmpty()) {
                System.out.println("   (none)");
            } else {
                for (Chips c : chipsInOrder) {
                    System.out.printf("   • %d x %-12s ($%.2f each) - $%.2f%n",
                            c.getQuantity(), c.getName(), c.getPrice(), c.getTotalPrice());
                }
            }
            System.out.println("----------------------------------------------------");
            System.out.printf("💰 Total for Chips:                    $%.2f%n", order.getTotalOfAllChipsPrice());

            // Ask if user wants to add another chip
            if (!controllers.getYesNo("\nAdd another chip? (Y/N): ")) {
                System.out.println("\033[0;36m\nReturning to main menu...\n\033[0m");
                break;
            }
        }
    }
}
