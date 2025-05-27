package User.Features.Checkout;

import java.util.Scanner;

public class Payment {
    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String cvv;

    private final Scanner scanner = new Scanner(System.in);

    public boolean processPayment() {
        System.out.println("\n💳 Enter Credit Card Details:");

        // Cardholder Name
        System.out.print("Cardholder Name: ");
        cardHolderName = scanner.nextLine().trim();

        // Card Number
        while (true) {
            System.out.print("Card Number (16 digits): ");
            cardNumber = scanner.nextLine().trim();
            if (cardNumber.matches("\\d{16}")) break;
            System.out.println("❌ Invalid card number. Please enter exactly 16 digits.");
        }

        // Expiration Date
        while (true) {
            System.out.print("Expiration Date (MM/YY): ");
            expirationDate = scanner.nextLine().trim();
            if (expirationDate.matches("(0[1-9]|1[0-2])/\\d{2}")) break;
            System.out.println("❌ Invalid format. Please enter in MM/YY format.");
        }

        // CVV
        while (true) {
            System.out.print("CVV (3 digits): ");
            cvv = scanner.nextLine().trim();
            if (cvv.matches("\\d{3}")) break;
            System.out.println("❌ Invalid CVV. Please enter 3 digits.");
        }

        // Simulate processing
        System.out.println("\n🔄 Processing payment...");
        try {
            Thread.sleep(1500); // simulate delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("✅ Payment approved!");
        return true;
    }
}
