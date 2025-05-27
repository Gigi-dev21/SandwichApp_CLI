package Admin.ViewTransactions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TransactionDisplay {

    public static void displayTransactions(List<TransactionRecord> records) {
        if (records == null || records.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        String format = "| %-15s | %-20s | %-20s | %-20s | %-10s | %-13s | %-20s |%n";
        String line = "+-----------------+----------------------+----------------------+----------------------+------------+---------------+----------------------+\n";

        System.out.print(line);
        System.out.printf(format, "Customer", "Sandwiches", "Drinks", "Chips", "Total", "Payment", "Date/Time");
        System.out.print(line);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        for (TransactionRecord record : records) {
            String sandwiches = String.join(", ", record.getSandwichNames());
            String drinks = String.join(", ", record.getDrinkNames());
            String chips = String.join(", ", record.getChipNames());
            String total = String.format("$%.2f", record.getTotalAmount());
            String dateTime = sdf.format(new Date(record.getTimestamp()));

            System.out.printf(format,
                    record.getCustomerName(),
                    truncate(sandwiches, 20),
                    truncate(drinks, 20),
                    truncate(chips, 20),
                    total,
                    record.getPaymentMethod(),
                    dateTime
            );
        }

        System.out.print(line);
    }

    private static String truncate(String text, int maxLength) {
        return text.length() > maxLength ? text.substring(0, maxLength - 3) + "..." : text;
    }
}
