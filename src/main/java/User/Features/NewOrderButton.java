package User.Features;

//import ChipFeatures.AddChipsButton;
//import DrinkFeatures.AddDrinkButton;
//import SandwitchFeatures.AddSandwichButton;

import User.Classes.Inventory;
import User.Classes.Order;
import User.Controllers.Controllers;
import User.Features.Checkout.Checkout;
import User.Features.Chips.AddChips;
import User.Features.Drink.AddDrink;
import User.Features.Sandwich.AddSandwich;
import User.Features.SignatureSandwich.SignatureSandwiches;

import java.util.Scanner;

public class NewOrderButton {
    private final Order order;
    private final Inventory inventory;
    private final Controllers controllers;


    public NewOrderButton(Order order, Inventory inventory, Controllers controllers) {
        this.order = order;
        this.inventory = inventory;
        this.controllers = controllers;

    }

    public void addNewOrder() {
        Scanner scanner = new Scanner(System.in);
        int numInserted = -1;

        if (order.getCustomerName() == null) {
            String name = controllers.getValidatedName("👤 Please enter your name: ");
            order.setCustomerName(name);

        }
        System.out.println("\n\033[0;33m=======================================");
        System.out.println("      Adding a New Order");
        System.out.println("=======================================\033[0m");
        while (true) {
            System.out.println("\nPlease choose an option:");
            System.out.println("  1) Add Sandwich");
            System.out.println("  2) Add Drink");
            System.out.println("  3) Add Chips");
            System.out.println("  4) Add Our Signature Sandwiches");
            System.out.println("  5) Checkout");
            System.out.println("  0) Cancel Order");
            System.out.print("\n Your choice: ");

            String input = scanner.nextLine();

            try {
                numInserted = Integer.parseInt(input);
                switch (numInserted) {
                    case 1:
                        new AddSandwich(order, inventory).addSandwichButton();
                        break;
                    case 2:
                        new AddDrink(order).displayDrinks();
                        break;
                    case 3:
                        new AddChips(order).displayChips();
                        break;
                    case 4:
                        new SignatureSandwiches(inventory, order).displaySignatureSandwiches();
                        break;
                    case 5:
                        if (order.isEmpty()) {
                            System.out.println("\n\033[1;31mYour order is empty! Please add something before checking out.\033[0m");
                        } else {
                            boolean paid = new Checkout(order, inventory).checkOut();
                            if (paid) {
                                return;
                            }
                        }
                        break;
                    case 0:
                        boolean canceled = new Cancel().displayCancel(order);
                        if (canceled) {
                            return;
                        }
                        break;
                    default:
                        System.out.println("\n\033[0;31mInvalid input! Please enter a number between 0 and 4.\033[0m");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\n\033[0;31mInvalid input. Please enter a valid number.\033[0m");
            }
        }

    }
}
