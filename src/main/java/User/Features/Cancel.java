package User.Features;

import User.Classes.Order;
import User.Controllers.Controllers;

public class Cancel {
    Controllers controllers = new Controllers();

    public boolean displayCancel(Order order) {
        boolean confirm = controllers.getYesNo("\nAre you sure you want to cancel? (Y/N): ");
        if (confirm) {
            System.out.println("\n\033[1;31mOrder has been canceled.\033[0m");
            order.clear();
        } else {
            System.out.println("\n\033[1;32mContinuing your order.\033[0m");
        }
        return confirm;
    }
}
