package User.Features.Drink;

import User.Classes.Drink;
import User.Classes.Inventory;
import User.Classes.Order;
import User.Controllers.Controllers;

import java.util.List;

public class AddDrink {
    Controllers controllers = new Controllers();
    Inventory inventory = Inventory.loadFromFile("Inventory.json");
    private final Order order;

    public AddDrink(Order order) {
        this.order = order;
    }

    public void displayDrinks() {
        if (inventory == null) {
            System.out.println("Inventory not loaded.");
            return;
        }

        while (true) {
            System.out.println("\n\033[0;33m=======================================");
            System.out.println("              Select Drink             ");
            System.out.println("=======================================\033[0m");

            List<Drink> drinksInOrder = order.getDrinks();
            List<String> drinkNames = List.copyOf(inventory.drinks.keySet());

            String selectedDrinkName = controllers.selectOneOption("Choose a drink:", drinkNames);
            var sizePriceMap = inventory.drinks.get(selectedDrinkName);
            List<String> sizes = List.copyOf(sizePriceMap.keySet());

            String selectedSize = controllers.selectOneOption("Choose size:", sizes);
            double pricePerUnit = sizePriceMap.get(selectedSize);

            int quantity = controllers.getIntegerInput("\nHow many would you like? ");
            double totalPrice = pricePerUnit * quantity;

            System.out.printf(
                    "\033[0;36m\n%d x %s (%s, $%.2f each) - $%.2f\033[0m%n",
                    quantity, selectedDrinkName, selectedSize, pricePerUnit, totalPrice
            );

            if (controllers.getYesNo("\nAdd this drink to your order? (Y/N): ")) {
                Drink drink = new Drink(selectedDrinkName, selectedSize, pricePerUnit, quantity);
                order.addDrink(drink);
            }

            System.out.println("\n🧾 Drinks in your order so far:");
            if (drinksInOrder.isEmpty()) {
                System.out.println("   (none)");
            } else {
                for (Drink d : drinksInOrder) {
                    System.out.printf(
                            "   • %d x %-12s (%s, $%.2f each) - $%.2f%n",
                            d.getQuantity(), d.getName(), d.getSize(), d.getPrice(), d.getTotalPrice()
                    );
                }
                System.out.println("----------------------------------------------------");
                System.out.printf("💰 Total for drinks:                          $%.2f%n", order.getTotalOfAllDrinkPrice());
            }

            if (!controllers.getYesNo("\nAdd another drink? (Y/N): ")) {
                System.out.println("\033[0;36m\nReturning to main menu...\n\033[0m");
                break;
            }
        }
    }
}
