package User.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controllers {
    Scanner scanner = new Scanner(System.in);

    public String selectOneOption(String title, List<String> options) {
        System.out.println("\n" + title + ":");
        for (int i = 0; i < options.size(); i++) {
            if (title == "Select sandwich size") {
                System.out.println("  " + (i + 1) + ") " + options.get(i) + "\"");
            } else
                System.out.println("  " + (i + 1) + ") " + options.get(i));
        }
        int choice = getValidatedIndex("Enter the number: ", options.size());
        return options.get(choice);
    }

    public String selectOptionWithPrice(String title, List<String> options, java.util.Map<String, java.util.Map<String, Double>> prices, String size) {
        System.out.println("\n" + title + ":");
        for (int i = 0; i < options.size(); i++) {
            String name = options.get(i);
            double price = prices.get(name).get(size);
            System.out.println("  " + (i + 1) + ") " + name + " ($" + price + ")");
        }
        int choice = getValidatedIndex("Enter the number: ", options.size());
        return options.get(choice);
    }

    public List<String> selectMultipleOptions(String title, List<String> options) {
        System.out.println("\n" + title + " (select by number, comma-separated):");
        for (int i = 0; i < options.size(); i++) {
            System.out.println("  " + (i + 1) + ") " + options.get(i));
        }
        System.out.print("Enter your choices: ");
        String input = scanner.nextLine();
        return getValidSelections(input, options);
    }


    private int getValidatedIndex(String prompt, int maxOption) {
        while (true) {
            System.out.print(prompt);
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= maxOption) {
                    return choice - 1;
                } else {
                    System.out.println("\n\033[0;31mInvalid choice. Please enter a number between 1 and " + maxOption + ".\033[0m");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n\033[0;31mInvalid input. Please enter a valid number.\033[0m");
            }
        }
    }

    public boolean getYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) return true;
            if (input.equals("n")) return false;
            System.out.println("\n\033[0;31mInvalid input. Please enter 'Y' or 'N'.\033[0m");
        }
    }

    private List<String> getValidSelections(String input, List<String> options) {
        List<String> selections = new ArrayList<>();

        while (true) {
            String[] parts = input.split(",");
            boolean allValid = true;
            selections.clear();

            for (String part : parts) {
                try {
                    int index = Integer.parseInt(part.trim()) - 1;
                    if (index >= 0 && index < options.size()) {
                        selections.add(options.get(index));
                    } else {
                        System.out.println("\n\033[0;31mInvalid selection number: " + (index + 1) + "\033[0m");
                        allValid = false;
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\n\033[0;31mInvalid input: '" + part.trim() + "' is not a number.\033[0m");
                    allValid = false;
                    break;
                }
            }

            if (allValid) break;
            else {
                System.out.println("\n\033[0;31mPlease try again with valid numbers.\033[0m\n");
                System.out.print("Enter your choices (comma-separated): ");
                input = scanner.nextLine().trim();
            }
        }

        return selections;
    }

    public int selectOption(String prompt, List<String> options) {
        System.out.println(prompt);
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("  %d) %s%n", i + 1, options.get(i));
        }
        int choice = -1;
        while (choice < 1 || choice > options.size()) {
            System.out.print("\nEnter the number: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                // ignore and continue loop
            }
            if (choice < 1 || choice > options.size()) {
                System.out.println("\n\033[0;31mInvalid choice. Please try again.\033[0m");
            }
        }
        return choice;
    }

    public String getValidatedName(String prompt) {
        String name;
        while (true) {
            System.out.print("\n\033[1;34m" + prompt + "\033[0m");
            name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("\033[0;31mName cannot be empty. Please try again.\033[0m");
            } else if (!name.matches("[a-zA-Z\\s]+")) {
                System.out.println("\033[0;31mPlease enter letters only (no numbers or symbols).\033[0m");
            } else {
                break;
            }
        }
        return name;
    }

    public int getIntegerInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        int number = -1;

        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                number = Integer.parseInt(input);
                if (number > 0) { // only allow positive numbers
                    break;
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n\033[0;31mInvalid input. Please enter a valid number.\033[0m");
            }
        }

        return number;
    }
    public String getValidAction(Scanner sc, String prompt) {
        String action;
        while (true) {
            System.out.print(prompt);
            action = sc.nextLine().trim().toLowerCase();

            if (action.equals("add") || action.equals("remove") || action.equals("skip")) {
                return action;
            } else {
                System.out.println("\033[0;31mInvalid input. Please enter 'add', 'remove', or 'skip'.\033[0m");
            }
        }
    }
    public List<String> getValidToppingsInput(Scanner sc, String prompt, List<String> validOptions) {
        List<String> chosen = new ArrayList<>();

        while (true) {
            System.out.println(prompt);
            String input = sc.nextLine();
            String[] parts = input.split(",");

            chosen.clear();
            boolean allValid = true;

            for (String part : parts) {
                String topping = part.trim();
                if (!validOptions.contains(topping)) {
                    System.out.printf("\033[0;31mTopping '%s' is not available. Please enter valid toppings.\033[0m%n", topping);
                    allValid = false;
                    break;
                }
                chosen.add(topping);
            }

            if (allValid && !chosen.isEmpty()) {
                return chosen;
            }
            System.out.println("Please try again.\n");
        }
    }

}
