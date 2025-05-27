package User.Features.SignatureSandwich;

import User.Classes.Inventory;
import User.Classes.Order;
import User.Classes.SignatureSandwich;
import User.Controllers.Controllers;

import java.util.ArrayList;
import java.util.List;

public class SignatureSandwiches {
    private final List<String> names;
    private final List<SignatureSandwich> sandwiches;
    private final Inventory inventory;
    private final Order order;
    Controllers controller = new Controllers();

    public SignatureSandwiches(Inventory inventory, Order order) {
        this.inventory = inventory;
        this.order = order;
        this.names = new ArrayList<>(inventory.signature_sandwiches.keySet());
        this.sandwiches = new ArrayList<>(inventory.signature_sandwiches.values());
    }

    public void displaySignatureSandwiches() {
        System.out.println("\n\033[0;33m=======================================");
        System.out.println("           Signature Sandwiches            ");
        System.out.println("=======================================\033[0m");

        for (int i = 0; i < sandwiches.size(); i++) {
            SignatureSandwich s = sandwiches.get(i);
            System.out.printf("\n%d. \033[35m%s\033[0m%n", i + 1, names.get(i));
            s.displayDetails();
        }

        // 👇 Ask the user to select one right after displaying
        int choice = controller.selectOption("\nSelect a signature sandwich:", names);
        SignatureSandwich selected = sandwiches.get(choice - 1);

        // 👇 Confirm selection
        System.out.println("\n\033[1;32mYou selected:\033[0m");
        System.out.printf("\033[35m%s\033[0m%n", names.get(choice - 1));
        selected.displayDetails();

        // Ask for customization

        CustomizeSandwich customizeSandwich=new CustomizeSandwich(inventory,order);
        String selectedName = names.get(choice - 1)+"   ";
        customizeSandwich.customizeSandwich(selected, selectedName);
    }

}





