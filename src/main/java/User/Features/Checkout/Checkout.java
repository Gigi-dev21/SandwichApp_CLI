package User.Features.Checkout;

import User.Classes.Inventory;
import User.Classes.Order;
import User.Classes.Transaction;
import User.Controllers.Controllers;

import java.time.Instant;


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

                //Creates the timestamp//
                long orderTimestamp = Instant.now().toEpochMilli();

                Transaction transaction = new Transaction(
                        order.getCustomerName(),
                        order,
                        totalWithTax,
                        "Credit Card",
                        Instant.now().toEpochMilli()
                );
                //save it as json//
                SaveToFile saveToFile= new SaveToFile();
                saveToFile.saveTransactionAsJson(transaction);

                //save it as text//
                String receiptContent = displayCheckoutReceipt.getReceiptAsString();
                saveToFile.saveReceiptToFile(receiptContent, orderTimestamp);

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

}
