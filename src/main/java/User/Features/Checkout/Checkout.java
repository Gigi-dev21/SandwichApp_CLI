package User.Features.Checkout;

import User.Classes.Inventory;
import User.Classes.Order;
import User.Classes.Transaction;
import User.Controllers.Controllers;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Checkout {
    private final Order order;
    private final Inventory inventory;
    Controllers controller = new Controllers();


    public Checkout(Order order, Inventory inventory) {
        this.order = order;
        this.inventory = inventory;
    }

    public boolean checkOut() {
        boolean proceed = controller.getYesNo("\n🧾 Proceed to checkout? (Y/N): ");
        if (!proceed) {
            System.out.println("\n↩️ Checkout cancelled. Returning to main menu...");
            return false;
        }

        DisplayCheckoutReceipt displayCheckoutReceipt = new DisplayCheckoutReceipt(order, inventory);
        displayCheckoutReceipt.displayReceipt();

        boolean confirmPayment = controller.getYesNo("\n💳 Confirm payment? (Y/N): ");
        if (confirmPayment) {
            Payment payment = new Payment();
            boolean success = payment.processPayment();
            if (success) {
                System.out.println("\n\033[1;35m🎉🎉 Thank you for your order, we hope to see you again soon! 🎉🎉\033[0m");
                // Create Transaction object
                double totalWithTax = order.getOrderTotal(inventory) * (1 + order.getTax());
                totalWithTax = Math.round(totalWithTax * 100.0) / 100.0;

                Transaction transaction = new Transaction(
                        order.getCustomerName(),
                        order,
                        totalWithTax,
                        "Credit Card",
                        Instant.now().toEpochMilli()
                );

                // Save transaction as JSON
                saveTransactionAsJson(transaction);

                // Reset the order
                order.clear();
                return true;
            } else {
                System.out.println("\n❌ Payment failed. Please try again.");
            }
        } else {
            System.out.println("\n❌ Payment cancelled. Returning to main menu...");
        }
        return false;
    }

    private void saveTransactionAsJson(Transaction newTransaction) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("transactions.json");
        List<Transaction> transactions = new ArrayList<>();

        try {
            // Check if file exists and is not empty before reading
            if (file.exists() && Files.size(Paths.get(file.getPath())) > 0) {
                transactions = mapper.readValue(file, new TypeReference<List<Transaction>>() {
                });
            }
            // Add the new transaction
            transactions.add(newTransaction);
            // Write the updated list back to the file
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, transactions);
        } catch (IOException e) {
            System.out.println("Error saving transaction to JSON: " + e.getMessage());
        }

    }

}
