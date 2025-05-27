package User;

//import Buttons.NewOrderButton;

import User.Classes.Inventory;
import User.Classes.Order;
import User.Controllers.Controllers;
import User.Features.NewOrderButton;

import java.util.Scanner;

public class UserUI {
    private final Order order;
    private final Inventory inventory;

    public UserUI(Order order, Inventory inventory) {
        this.order = order;
        this.inventory = inventory;

    }

    public void userDisplayHomeScreen() {
        Order order = new Order();
        Controllers controllers = new Controllers();
        Scanner scanner = new Scanner(System.in);

        String name = controllers.getValidatedName("👤 Please enter your name: ");
        order.setCustomerName(name);


        System.out.println("\n\033[0;33m=======================================");
        System.out.println("       Welcome to DELI-cious" + " " + name + "!");
        System.out.println("=======================================\033[0m");
        int numInserted = -1;

        while (numInserted != 0) {
            System.out.println("\nPlease choose an option:");
            System.out.println("  1) Add New Order");
            System.out.println("  0) Exit");
            System.out.print("\nYour choice: ");

            String input = scanner.nextLine();

            try {
                numInserted = Integer.parseInt(input);
                switch (numInserted) {
                    case 1:
                        NewOrderButton newOrderButton = new NewOrderButton(order,inventory);
                        newOrderButton.addNewOrder();
                        break;
                    case 0:
                        System.out.println("\n\033[0;34mThank you for visiting DELI-cious Sandwiches. Goodbye! 👋\033[0m");
                        break;
                    default:
                        System.out.println("\nInvalid input! Please enter 1 or 0.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input! Please enter a valid number.");
            }
        }
    }
}
