package Admin.AddInventory;

import java.util.*;

public class InventoryPrompter {

    public static void addChip(Scanner scanner, Map<String, Object> inventory) {
        System.out.print("Enter chip name: ");
        String chipName = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());

        Map<String, Double> chips = (Map<String, Double>) inventory.get("chips");
        if (chips == null) {
            chips = new HashMap<>();
            inventory.put("chips", chips);
        }
        chips.put(chipName.toLowerCase(), price);
        System.out.println("Chip added.");
    }

    public static void addDrink(Scanner scanner, Map<String, Object> inventory) {
        System.out.print("Enter drink name: ");
        String drinkName = scanner.nextLine();
        Map<String, Double> sizes = new HashMap<>();
        for (String size : List.of("small", "medium", "large")) {
            System.out.printf("Enter price for %s: ", size);
            sizes.put(size, Double.parseDouble(scanner.nextLine()));
        }

        Map<String, Map<String, Double>> drinks = (Map<String, Map<String, Double>>) inventory.get("drinks");
        if (drinks == null) {
            drinks = new HashMap<>();
            inventory.put("drinks", drinks);
        }
        drinks.put(drinkName.toLowerCase(), sizes);
        System.out.println("Drink added.");
    }

    public static void addSignatureSandwich(Scanner scanner, Map<String, Object> inventory) {
        System.out.print("Enter sandwich name: ");
        String name = scanner.nextLine();

        Map<String, Object> sandwich = new HashMap<>();
        System.out.print("Size (4, 8, 12): ");
        sandwich.put("size", scanner.nextLine());

        System.out.print("Bread (white, wheat, etc.): ");
        sandwich.put("bread", scanner.nextLine());

        System.out.print("Meat: ");
        sandwich.put("meat", scanner.nextLine());

        System.out.print("Extra meat (true/false): ");
        sandwich.put("extraMeat", Boolean.parseBoolean(scanner.nextLine()));

        System.out.print("Cheese: ");
        sandwich.put("cheese", scanner.nextLine());

        System.out.print("Extra cheese (true/false): ");
        sandwich.put("extraCheese", Boolean.parseBoolean(scanner.nextLine()));

        System.out.print("Toasted (true/false): ");
        sandwich.put("toasted", Boolean.parseBoolean(scanner.nextLine()));

        sandwich.put("toppings", promptList(scanner, "Enter toppings (comma-separated): "));
        sandwich.put("sauces", promptList(scanner, "Enter sauces (comma-separated): "));
        sandwich.put("sides", promptList(scanner, "Enter sides (comma-separated): "));

        Map<String, Object> signature = (Map<String, Object>) inventory.get("signature_sandwiches");
        if (signature == null) {
            signature = new HashMap<>();
            inventory.put("signature_sandwiches", signature);
        }

        signature.put(name, sandwich);
        System.out.println("Sandwich added.");
    }

    private static List<String> promptList(Scanner scanner, String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        if (input.isBlank()) return new ArrayList<>();
        return Arrays.asList(input.split("\\s*,\\s*"));
    }
}
