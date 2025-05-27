package Admin.ViewTransactions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TransactionLoader {

    public static List<TransactionRecord> loadTransactionSummaries(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        List<TransactionRecord> summaries = new ArrayList<>();
        try {
            List<FullTransactionRecord> fullTransactions = mapper.readValue(
                    new File(filePath), new TypeReference<List<FullTransactionRecord>>() {});

            for (FullTransactionRecord full : fullTransactions) {
                List<String> sandwichNames = new ArrayList<>();
                if (full.getOrder() != null && full.getOrder().getSandwiches() != null) {
                    for (Sandwich s : full.getOrder().getSandwiches()) {
                        sandwichNames.add(s.getName());
                    }
                }

                List<String> drinkNames = new ArrayList<>();
                if (full.getOrder() != null && full.getOrder().getDrinks() != null) {
                    for (Drink d : full.getOrder().getDrinks()) {
                        drinkNames.add(d.getName());
                    }
                }

                List<String> chipNames = new ArrayList<>();
                if (full.getOrder() != null && full.getOrder().getChips() != null) {
                    for (Chip c : full.getOrder().getChips()) {
                        chipNames.add(c.getName());
                    }
                }

                TransactionRecord summary = new TransactionRecord(
                        full.getCustomerName(),
                        sandwichNames,
                        drinkNames,
                        chipNames,
                        full.getTotalAmount(),
                        full.getPaymentMethod(),
                        full.getTimestamp()
                );

                summaries.add(summary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return summaries;
    }
}
