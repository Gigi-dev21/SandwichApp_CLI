package Admin;

//import Admin.AddInventory.InventoryManager;
import Admin.ViewTransactions.TransactionDisplay;

import java.util.List;
import java.util.Scanner;

public class AdminUI {

    private final String ADMIN_PASSWORD = "12345";

    public void adminDisplayHomeScreen() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\033[0;33m=======================================");
        System.out.println("         Welcome Admin!");
        System.out.println("=======================================\033[0m");

        while (true) {
            System.out.print("\nEnter admin password: ");
            String enteredPassword = scanner.nextLine();

            if (enteredPassword.equals(ADMIN_PASSWORD)) {
                break;
            } else {
                System.out.println("\n\033[1;31mIncorrect password. Please try again.\033[0m");
            }
        }

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n\033[0;33m=======================================");
            System.out.println("         Admin Menu");
            System.out.println("=======================================\033[0m");

            System.out.println("\nPlease choose an option:");
            System.out.println("  1) View Transactions");
            System.out.println("  0) Exit ");
            System.out.print("\nYour choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        String transactionFile = "transactions.json";
                        TransactionDisplay.displayTransactions(transactionFile);
                        break;
                    case 0:
                        System.out.println("\nExiting Admin Panel.");
                        break;
                    default:
                        System.out.println("Invalid option. Please choose 0, 1, or 2.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }
}
