package Admin.ViewTransactions;

import Admin.ViewTransactions.model.Chip;
import Admin.ViewTransactions.model.Drink;
import Admin.ViewTransactions.model.Sandwich;
import Admin.ViewTransactions.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



import java.util.stream.Collectors;

public class TransactionDisplay {

    private static final int COL_WIDTH_DATE = 10;    // yyyy-MM-dd
    private static final int COL_WIDTH_TIME = 8;     // HH:mm:ss
    private static final int COL_WIDTH_CUSTOMER = 15;
    private static final int COL_WIDTH_ITEM = 20;
    private static final int COL_WIDTH_PAYMENT = 15;
    private static final int COL_WIDTH_AMOUNT = 13;

    private static String padRight(String text, int length) {
        if (text == null) text = "";
        if (text.length() > length) {
            return text.substring(0, length - 3) + "...";
        }
        return String.format("%-" + length + "s", text);
    }

    public static void displayTransactions(String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Transaction> transactions = mapper.readValue(
                    new File(filePath),
                    new TypeReference<List<Transaction>>() {}
            );

            if (transactions.isEmpty()) {
                System.out.println("No transactions found.");
                return;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            // Print header row
            System.out.printf(
                    "%-" + COL_WIDTH_DATE + "s|%-" + COL_WIDTH_TIME + "s|%-" + COL_WIDTH_CUSTOMER + "s|%-" +
                            COL_WIDTH_ITEM + "s|%-" + COL_WIDTH_ITEM + "s|%-" + COL_WIDTH_ITEM + "s|%-" +
                            COL_WIDTH_PAYMENT + "s|%-" + COL_WIDTH_AMOUNT + "s%n",
                    "Date", "Time", "Customer", "Sandwich", "Drinks", "Chips", "Payment Method", "Total Amount"
            );
            System.out.println("-".repeat(
                    COL_WIDTH_DATE + COL_WIDTH_TIME + COL_WIDTH_CUSTOMER +
                            COL_WIDTH_ITEM * 3 + COL_WIDTH_PAYMENT + COL_WIDTH_AMOUNT + 7 // for '|' chars
            ));

            double totalSales = 0;

            for (Transaction t : transactions) {
                Date tsDate = new Date(t.getTimestamp());
                String date = dateFormat.format(tsDate);
                String time = timeFormat.format(tsDate);
                String customer = t.getCustomerName();
                String payment = t.getPaymentMethod();
                String totalAmountStr = String.format("$%.2f", t.getTotalAmount());

                totalSales += t.getTotalAmount();

                List<Sandwich> sandwiches = (t.getOrder() != null) ? t.getOrder().getSandwiches() : null;
                List<Drink> drinks = (t.getOrder() != null) ? t.getOrder().getDrinks() : null;
                List<Chip> chips = (t.getOrder() != null) ? t.getOrder().getChips() : null;

                String sandwichNames = (sandwiches == null || sandwiches.isEmpty()) ? "" :
                        sandwiches.stream()
                                .map(Sandwich::getName)
                                .collect(Collectors.joining(", "));

                String drinkNames = (drinks == null || drinks.isEmpty()) ? "" :
                        drinks.stream()
                                .map(Drink::getName)
                                .collect(Collectors.joining(", "));

                String chipNames = (chips == null || chips.isEmpty()) ? "" :
                        chips.stream()
                                .map(Chip::getName)
                                .collect(Collectors.joining(", "));

                System.out.printf(
                        "%-" + COL_WIDTH_DATE + "s|%-" + COL_WIDTH_TIME + "s|%-" + COL_WIDTH_CUSTOMER + "s|%-" +
                                COL_WIDTH_ITEM + "s|%-" + COL_WIDTH_ITEM + "s|%-" + COL_WIDTH_ITEM + "s|%-" +
                                COL_WIDTH_PAYMENT + "s|%-" + COL_WIDTH_AMOUNT + "s%n",
                        padRight(date, COL_WIDTH_DATE),
                        padRight(time, COL_WIDTH_TIME),
                        padRight(customer, COL_WIDTH_CUSTOMER),
                        padRight(sandwichNames, COL_WIDTH_ITEM),
                        padRight(drinkNames, COL_WIDTH_ITEM),
                        padRight(chipNames, COL_WIDTH_ITEM),
                        padRight(payment, COL_WIDTH_PAYMENT),
                        padRight(totalAmountStr, COL_WIDTH_AMOUNT)
                );
            }

            System.out.println("-".repeat(
                    COL_WIDTH_DATE + COL_WIDTH_TIME + COL_WIDTH_CUSTOMER +
                            COL_WIDTH_ITEM * 3 + COL_WIDTH_PAYMENT + COL_WIDTH_AMOUNT + 7
            ));

            // Print total sales line
            String totalLabel = "TOTAL SALES:";
            System.out.printf("%" + (COL_WIDTH_DATE + COL_WIDTH_TIME + COL_WIDTH_CUSTOMER + COL_WIDTH_ITEM*3 + COL_WIDTH_PAYMENT + 6) + "s | $%.2f%n", totalLabel, totalSales);

            System.out.println();

        } catch (Exception e) {
            System.err.println("Error reading transactions: " + e.getMessage());
        }
    }

}
