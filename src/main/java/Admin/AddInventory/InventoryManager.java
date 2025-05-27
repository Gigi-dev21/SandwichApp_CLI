package Admin.AddInventory;//package Admin.AddInventory;//package Admin;
//
//import Admin.AddInventory.InventoryPrompter;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.io.File;
//import java.util.*;
//
//public class InventoryManager {
//    private Map<String, Object> inventory;
//    private final String filePath;
//
//    public InventoryManager(String filePath) {
//        this.filePath = filePath;
//        loadInventory();
//    }
//
//    private void loadInventory() {
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            inventory = mapper.readValue(new File(filePath), Map.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//            inventory = new HashMap<>();
//        }
//    }
//
//    public void saveInventory() {
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), inventory);
//            System.out.println("Inventory saved successfully.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void addInventoryItem(Scanner scanner) {
//        System.out.println("\nChoose category to add:");
//        System.out.println("  1) Bread");
//        System.out.println("  2) Meat");
//        System.out.println("  3) Cheese");
//        System.out.println("  4) Regular Toppings");
//        System.out.println("  5) Sides");
//        System.out.println("  6) Sauces");
//        System.out.println("  7) Drinks");
//        System.out.println("  8) Chips");
//        System.out.println("  9) Signature Sandwiches");
//        System.out.print("Enter your choice: ");
//
//        String input = scanner.nextLine();
//
//        switch (input) {
//            case "1":
//                addBread(scanner);
//                break;
//            case "2":
//                addMeat(scanner);
//                break;
//            case "3":
//                addCheese(scanner);
//                break;
//            case "4":
//                addSimpleCategory(scanner, "regular_toppings");
//                break;
//            case "5":
//                addSimpleCategory(scanner, "sides");
//                break;
//            case "6":
//                addSimpleCategory(scanner, "sauces");
//                break;
//            case "7":
//                addDrink(scanner);
//                break;
//            case "8":
//                addChips(scanner);
//                break;
//            case "9":
//                addSignatureSandwich(scanner);
//                break;
//            default:
//                System.out.println("Invalid choice.");
//        }
//    }
//
//
//    public Map<String, Object> getInventory() {
//        return inventory;
//    }
//}
