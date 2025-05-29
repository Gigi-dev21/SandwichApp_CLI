package User.Features.Sandwich;

import User.Classes.Inventory;
import User.Classes.Order;
import User.Classes.Sandwich;
import User.Controllers.Controllers;

import java.util.ArrayList;
import java.util.List;

public class AddSandwich {
    Controllers controllers = new Controllers();
//    Inventory inventory = Inventory.loadFromFile("Inventory.json");
    private final Order order;
    private final Inventory inventory;
    private final DisplaySandwichReceipt displaySandwichReceipt;

    public AddSandwich(Order order, Inventory inventory) {
        this.order = order;
        this.displaySandwichReceipt = new DisplaySandwichReceipt(order);
        this.inventory = inventory;
    }

    public void addSandwichButton() {
        if (inventory == null) {
            System.out.println("Inventory not loaded.");
            return;
        }

        while (true) {
            System.out.println("\n\033[0;33m=======================================");
            System.out.println("       Create Your Sandwich");
            System.out.println("=======================================\033[0m");

            // Sandwich size
            String[] sizes = {"4", "8", "12"};
            String selectedSize = controllers.selectOneOption("Select sandwich size", List.of(sizes));
            System.out.println("\033[0;35mYou chose: " + selectedSize + "\"\033[0m");

            // Bread
            List<String> breadOptions = new ArrayList<>(inventory.bread.keySet());
            String selectedBread = controllers.selectOptionWithPrice("Select bread", breadOptions, inventory.bread, selectedSize);
            System.out.println("\033[0;35mYou chose: " + selectedBread + "\033[0m");

            // Meat
            List<String> meatOptions = new ArrayList<>(inventory.meat.keySet());
            meatOptions.remove("Extra meat");
            String selectedMeat = controllers.selectOptionWithPrice("Select meat", meatOptions, inventory.meat, selectedSize);
            System.out.println("\033[0;35mYou chose: " + selectedMeat + "\033[0m");

            // Extra Meat
            boolean wantsExtraMeat = controllers.getYesNo("\nWould you like to add extra meat for $" + inventory.meat.get("Extra meat").get(selectedSize) + "? (Y/N): ");
            System.out.println("\033[0;35mExtra meat: " + (wantsExtraMeat ? "Yes" : "No") + "\033[0m");

            // Cheese
            List<String> cheeseOptions = new ArrayList<>(inventory.cheese.keySet());
            cheeseOptions.remove("extra cheese");
            String selectedCheese = controllers.selectOptionWithPrice("Select cheese", cheeseOptions, inventory.cheese, selectedSize);
            System.out.println("\033[0;35mYou chose: " + selectedCheese + "\033[0m");

            // Extra Cheese
            boolean wantsExtraCheese = controllers.getYesNo("\nWould you like to add extra cheese for $" + inventory.cheese.get("extra cheese").get(selectedSize) + "? (Y/N): ");
            System.out.println("\033[0;35mExtra cheese: " + (wantsExtraCheese ? "Yes" : "No") + "\033[0m");

            // Toppings
            List<String> selectedToppings = controllers.selectMultipleOptions("Choose your toppings", new ArrayList<>(inventory.regular_toppings.keySet()));
            System.out.println("\033[0;35mToppings selected: " + String.join(", ", selectedToppings) + "\033[0m");

            // Sauces
            List<String> selectedSauces = controllers.selectMultipleOptions("Choose your sauces", new ArrayList<>(inventory.sauces.keySet()));
            System.out.println("\033[0;35mSauces selected: " + String.join(", ", selectedSauces) + "\033[0m\n");

            // Sides
            List<String> sidesSelected = new ArrayList<>();
            for (String side : inventory.sides.keySet()) {
                if (controllers.getYesNo("Would you like " + side + " on the side? (Y/N): ")) {
                    sidesSelected.add(side);
                }
            }
            System.out.println("\033[0;35mSides selected: " + (sidesSelected.isEmpty() ? "None" : String.join(", ", sidesSelected)) + "\033[0m");

            // Toasted
            boolean toasted = controllers.getYesNo("\nWould you like your sandwich toasted? (Y/N):");
            System.out.println("\033[0;35mToasted selection: " + (toasted ? "Yes" : "No") + "\033[0m");

            // Quantity
            int quantity = controllers.getIntegerInput("\nHow many of this sandwich would you like?");

            // Create sandwich object
            Sandwich sandwich = new Sandwich(
                    selectedSize,
                    selectedBread,
                    selectedMeat,
                    wantsExtraMeat,
                    selectedCheese,
                    wantsExtraCheese,
                    selectedToppings,
                    selectedSauces,
                    sidesSelected,
                    toasted,
                    quantity
            );

            // Display the sandwich receipt
            displaySandwichReceipt.displaySandwichSummary(sandwich, inventory);

            // Confirm
            if (controllers.getYesNo("\nConfirm this order? (Y/N): ")) {
                order.addSandwich(sandwich);
                displaySandwichReceipt.displayTotalAddedSandwich(inventory);

                if (!controllers.getYesNo("\nWould you like to add another sandwich? (Y/N): ")) {
                    System.out.println("\033[0;36m\nReturning to main menu...\n\033[0m");
                    return;
                }
            } else {
                System.out.println("\033[0;36m\nLet's start over and rebuild your sandwich...\n\033[0m");
                return;
            }
        }
    }
}
