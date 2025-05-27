import User.Classes.Inventory;
import User.Classes.Order;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = Inventory.loadFromFile("Inventory.json");
        if (inventory == null) {
            System.out.println("❌ Failed to load inventory!");
            return;
        }
        Order order = new Order();
        HomeScreen homeScreen=new HomeScreen(order,inventory);
        homeScreen.displayHomeScreen();

    }
}
