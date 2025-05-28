package User.Features.SignatureSandwich;

import User.Classes.Inventory;
import User.Classes.Order;
import User.Classes.Sandwich;
import User.Classes.SignatureSandwich;
import User.Controllers.Controllers;
import User.Features.Sandwich.DisplaySandwichReceipt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomizeSandwich {
    private final Inventory inventory;
    private final Order order;
    Controllers controller = new Controllers();
    private final DisplaySandwichReceipt displaySandwichReceipt;

    public CustomizeSandwich(Inventory inventory, Order order) {
        this.inventory = inventory;
        this.order = order;
        this.displaySandwichReceipt = new DisplaySandwichReceipt(order);
    }

    public void customizeSandwich(SignatureSandwich sandwich, String name) {
        Scanner sc = new Scanner(System.in);

        // Toppings
        String action = controller.getValidAction(sc, "\nDo you want to add or remove toppings? (add/remove/skip): ");
//        String action = sc.nextLine().trim().toLowerCase();

        if (action.equals("add")) {
            List<String> availableToppings = new ArrayList<>(inventory.regular_toppings.keySet());
            System.out.println("\n\033[1;36mAvailable toppings:\033[0m " + String.join(", ", availableToppings));
            List<String> toAdd = controller.getValidToppingsInput(sc, "Enter topping(s) to add (comma-separated):", availableToppings);

            for (String topping : toAdd) {
                if (!sandwich.getToppings().contains(topping)) {
                    sandwich.getToppings().add(topping);
                }
            }

        } else if (action.equals("remove")) {
            List<String> currentToppings = sandwich.getToppings();
            if (currentToppings.isEmpty()) {
                System.out.println("There are no toppings to remove.");
            } else {
                System.out.println("\n\033[1;36mCurrent toppings:\033[0m " + String.join(", ", currentToppings));
                List<String> toRemove = controller.getValidToppingsInput(sc, "Enter topping(s) to remove (comma-separated):", currentToppings);

                for (String topping : toRemove) {
                    sandwich.getToppings().remove(topping);
                }
            }
        }

        // Sauces
        action = controller.getValidAction(sc, "\nDo you want to add or remove sauces? (add/remove/skip): ");


        if (action.equals("add")) {
            List<String> availableSauces = new ArrayList<>(inventory.sauces.keySet());
            System.out.println("\n\033[1;36mAvailable sauces:\033[0m " + String.join(", ", availableSauces));
            List<String> toAdd = controller.getValidToppingsInput(sc, "Enter sauce(s) to add (comma-separated):", availableSauces);

            for (String sauce : toAdd) {
                if (!sandwich.getSauces().contains(sauce)) {
                    sandwich.getSauces().add(sauce);
                }
            }

        } else if (action.equals("remove")) {
            List<String> currentSauces = sandwich.getSauces();
            if (currentSauces.isEmpty()) {
                System.out.println("There are no sauces to remove.");
            } else {
                System.out.println("\n\033[1;36mCurrent sauces:\033[0m " + String.join(", ", currentSauces));
                List<String> toRemove = controller.getValidToppingsInput(sc, "Enter sauce(s) to remove (comma-separated):", currentSauces);

                for (String sauce : toRemove) {
                    sandwich.getSauces().remove(sauce);
                }
            }
        }

        System.out.println("\n\033[1;32mFinal Customized Sandwich:\033[0m");
        sandwich.displayDetails();

        boolean addToOrder = controller.getYesNo("\nDo you want to add this to your order? (Y/N):");


        if (addToOrder) {
            int quantity = controller.getIntegerInput("\nHow many of this sandwich would you like?");
            Sandwich finalized = sandwich.toSandwich(quantity, name);
            order.addSandwich(finalized);
            displaySandwichReceipt.displayTotalAddedSandwich(inventory);
            System.out.println("\n\033[1;32mSandwich added to your order.\033[0m");
        } else {
            System.out.println("\033[1;31mSandwich not added to your order.\033[0m");
        }
    }

}
