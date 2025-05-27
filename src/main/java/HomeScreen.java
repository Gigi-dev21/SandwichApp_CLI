import Admin.AdminUI;
import User.Classes.Inventory;
import User.Classes.Order;
import User.UserUI;

import java.util.Scanner;


public class HomeScreen {
    private final Order order;
    private final Inventory inventory;

    public HomeScreen(Order order, Inventory inventory) {
        this.order = order;
        this.inventory = inventory;
    }

    public void displayHomeScreen() {
        Scanner scanner = new Scanner(System.in);
        int roleChoice = -1;

        System.out.println("\n\033[0;33m=======================================");
        System.out.println("       Welcome to DELI-cious!");
        System.out.println("=======================================\033[0m");

        while (true) {
            System.out.println("Please select your role:");
            System.out.println("  1) User");
            System.out.println("  2) Admin ");
            System.out.print("\nEnter 1 or 2:");

            String input = scanner.nextLine().trim();

            if (input.equals("1") || input.equals("2")) {
                roleChoice = Integer.parseInt(input);
                break;
            } else {
                System.out.println("\n\033[0;31m1Invalid input. Please enter 1 for User  or 2 for Admin.\033[0m\n");
            }
        }

        if (roleChoice == 2) {
            AdminUI adminUI = new AdminUI();
            adminUI.adminDisplayHomeScreen();
        } else {
            UserUI userUI = new UserUI(order,inventory);
            userUI.userDisplayHomeScreen();
        }
        scanner.close();
    }
}
