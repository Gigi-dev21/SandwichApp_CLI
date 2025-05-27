package Admin.AddInventory;//package Admin.AddInventory;
//
//import com.fasterxml.jackson.databind.util.JSONPObject;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//
//public class Methods {
//    private void addBread(Scanner scanner) {
//        System.out.print("Enter bread type: ");
//        String type = scanner.nextLine();
//        Map<String, Object> breadOptions = new HashMap<>();
//        for (String size : List.of("4", "8", "12")) {
//            System.out.print("Enter price for size " + size + ": ");
//            double price = Double.parseDouble(scanner.nextLine());
//            breadOptions.put(size, price);
//        }
//        inventory.getJSONObject("bread").put(type, new JSONObject(breadOptions));
//        System.out.println("Bread type added.");
//    }
//    private void addSimpleCategory(Scanner scanner, String category) {
//        System.out.print("Enter name to add to " + category + ": ");
//        String name = scanner.nextLine();
//        inventory.getJSONObject(category).put(name, "Included");
//        System.out.println("Item added to " + category);
//    }
//    private void addDrink(Scanner scanner) {
//        System.out.print("Enter drink name: ");
//        String name = scanner.nextLine();
//        Map<String, Object> sizes = new HashMap<>();
//        for (String size : List.of("small", "medium", "large")) {
//            System.out.print("Enter price for " + size + ": ");
//            sizes.put(size, Double.parseDouble(scanner.nextLine()));
//        }
//        inventory.getJSONObject("drinks").put(name, new JSONObject(sizes));
//        System.out.println("Drink added.");
//    }
//    private void addChips(Scanner scanner) {
//        System.out.print("Enter chip brand: ");
//        String name = scanner.nextLine();
//        System.out.print("Enter price: ");
//        double price = Double.parseDouble(scanner.nextLine());
//        inventory.getJSONObject("chips").put(name, price);
//        System.out.println("Chip added.");
//    }
//    private void addSignatureSandwich(Scanner scanner) {
//        JSONPObject sandwich = new JSONObject();
//        System.out.print("Enter sandwich name: ");
//        String name = scanner.nextLine();
//        System.out.print("Size (4, 8, 12): ");
//        sandwich.put("size", scanner.nextLine());
//        System.out.print("Bread type: ");
//        sandwich.put("bread", scanner.nextLine());
//        System.out.print("Meat type: ");
//        sandwich.put("meat", scanner.nextLine());
//        System.out.print("Extra meat (true/false): ");
//        sandwich.put("extraMeat", Boolean.parseBoolean(scanner.nextLine()));
//        System.out.print("Cheese type: ");
//        sandwich.put("cheese", scanner.nextLine());
//        System.out.print("Extra cheese (true/false): ");
//        sandwich.put("extraCheese", Boolean.parseBoolean(scanner.nextLine()));
//
//        sandwich.put("toppings", getListFromInput(scanner, "toppings (comma separated)"));
//        sandwich.put("sauces", getListFromInput(scanner, "sauces (comma separated)"));
//        sandwich.put("sides", getListFromInput(scanner, "sides (comma separated)"));
//
//        System.out.print("Toasted (true/false): ");
//        sandwich.put("toasted", Boolean.parseBoolean(scanner.nextLine()));
//
//        inventory.getJSONObject("signature_sandwiches").put(name, sandwich);
//        System.out.println("Signature sandwich added.");
//    }
//
//    private JSONArray getListFromInput(Scanner scanner, String prompt) {
//        System.out.print("Enter " + prompt + ": ");
//        String[] input = scanner.nextLine().split(",");
//        JSONArray array = new JSONArray();
//        for (String item : input) {
//            array.put(item.trim());
//        }
//        return array;
//    }
//
//}
