package User.Features.Checkout;

import User.Classes.Transaction;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SaveToFile {
    protected void saveTransactionAsJson(Transaction newTransaction) {
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
    public void saveReceiptToFile(String receiptContent, long timestampMillis) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        ZonedDateTime dateTime = Instant.ofEpochMilli(timestampMillis).atZone(ZoneId.systemDefault());
        String fileName = dateTime.format(formatter) + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(receiptContent);
        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }
}
