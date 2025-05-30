
package com.DELI.model;

import Ons.Chips;
import Ons.Drink;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
/**
 * Handles user interaction and menu navigation
 * Provides methods for displaying menus, creating sandwiches, drinks, chips, special sandwiches,
 * managing orders, adding tips, and generating receipts.
 */
public class Menu {
    Scanner s = new Scanner(System.in);//scanner for user inputs

    //display home screen by calling from Symbol class and prompt users
    public void homeScreen(){
        boolean input = true;
        while (input){
           Symbol.printWelcomeBanner();
            String type = s.nextLine();

            switch (type){
                case "1" -> runOrder();
                case "0" -> {
                    input = false;
                    System.out.println("Thank you for visiting DELI-shop!");
                }
                default -> System.out.println("Invalid option. please try again.");
            }
        }
    }
    /**
     * manages the main order process. Allows the user to add sandwiches, drinks, chips
     * order a special sandwich, request a bag, add a tip, and checkout.
     */
    public void runOrder() {
        Order order = new Order();
        boolean input = true;

        while (input){
            Symbol.printOrderMenu();
            String type = s.nextLine();

            switch (type){
                case "1" -> {
                    Sandwich sandwich = addSandwich();
                    if (sandwich != null) {
                        order.addItem(sandwich);
                    } else {
                        System.out.println("Sandwich creation cancelled.");
                    }
                }
                case "2" -> {
                    Drink drink = addDrink();
                    if (drink != null) {
                        order.addItem(drink);
                    } else {
                        System.out.println("Drink creation cancelled.");
                    }
                }
                case "3" -> {
                    Chips chips = addChips();
                    if (chips != null) {
                        order.addItem(chips);
                    } else {
                        System.out.println("Chips creation cancelled.");
                    }
                }
                case "5" -> {
                    Sandwich sandwich = special();
                    if (sandwich != null) {
                        order.addItem(sandwich);
                        System.out.println("Added to order: " + sandwich + " $" + sandwich.getPrice());
                    } else {
                        System.out.println("Special sandwich cancelled.");
                    }
                }
                case "4" -> {
                    // bag request
                    String bag = prompt("Would you like a bag for $0.08? (y/n): ").toLowerCase();
                    if (bag.equals("y")) {
                        order.requestBag();
                        System.out.println("Bag fee of $0.08 added.");
                    }

                    // tip request
                    String tipChoice = prompt("Would you like to leave a tip? (y/n): ").toLowerCase();
                    if (tipChoice.equals("y")) {

                        System.out.println("1) 5% \n2) 10% \n3) 15% \n4) 20% \n5) 25%");
                        String pct = prompt("Enter choice (1-5) or press ENTER to skip: ");
                        double percent;
                        switch (pct) {
                            case "1" -> percent = 0.05;
                            case "2" -> percent = 0.10;
                            case "3" -> percent = 0.15;
                            case "4" -> percent = 0.20;
                            case "5" -> percent = 0.25;
                            default -> percent = 0.0;

                        }
                        if (percent > 0) {
                            double tip = order.getTotal() * percent;
                            order.addTip(tip);
                            System.out.printf("Tip of %.0f%% added: $%.2f%n", percent * 100, tip);
                        }
                    }
                    order.checkout();
                    input = false;
                }
                case "0" -> {
                    System.out.println("Order canceled. Returning to home.");
                    input = false;
                }
                default -> System.out.println("Invalid selection. please try again.");
            }
        }
    }
    /**
     * @param message The message to display.
     * @return The user input as a trimmed string.
     */
    public String prompt(String message){
        System.out.println(message);
        return s.nextLine().trim();
    }
    /**
     * create a custom sandwich with size, bread, toppings, and toasted option.
     * @return The created Sandwich, or null if the process is cancelled.
     */
    public Sandwich addSandwich() {
        System.out.println("\n===== Make your Sandwich =====");
        System.out.println("(Enter 0 to go back)");

        double currentPrice = 0.0;
        // sandwich size
        int size = 0;
        while(size != 4 && size != 8 && size != 12){
            String sizeInput = prompt("Enter sandwich size (4 / 8 / 12): ");
            if(sizeInput.equals("0")) return  null;
            try{
                size = Integer.parseInt(sizeInput);
                currentPrice = switch (size) {
                    case 4  -> 5.50;
                    case 8  -> 7.00;
                    case 12 -> 8.50;
                    default -> 0.0;
                };
                System.out.printf("Bread price: $%.2f%n", currentPrice);
            }catch (IllegalArgumentException e){
                System.out.print("Please enter a valid number.");
            }
        }
        //bread type
        String bread = null;
        while (bread == null) {
            String selected = selectFromList("Choose your bread", Topping.breadOptions);
            if (selected == null || selected.equals("BACK")) return null;
            bread = selected;
        }
        System.out.printf("%s bread added: $%.2f%n", bread, currentPrice);

        //toppings
        List<Topping> toppings = new ArrayList<>();
        String[] categories = {"MEAT","EXTRA MEAT","CHEESE","EXTRA CHEESE","REGULAR","SAUCE","SIDE"};
        int place = 0;
        while (place < categories.length) {
            String category = categories[place];
            String chosen = selectFromList(category, Topping.options.get(category));

        if (chosen == null) {
            place++;// Skip this category
            continue;
        }
            if (chosen.equals("BACK")) {
                if (place > 0) {
                    place--;  // Step back one category
                    continue;
                } else {
                    return null;
                }
            }
            Topping topping = new Topping(category, chosen);
            toppings.add(topping);
            double add = topping.getPrice(size);
            currentPrice += add;
            System.out.printf("%s added $%.2f: \nTotal: $%.2f%n", chosen, add, currentPrice);
        }
        //toasted
        String toast = prompt("Would you like it toasted? (yes/no): ").toLowerCase();
        if(toast.equals("0")) return null;
        boolean toasted = toast.equals("yes") || toast.equals("y");

        return new Sandwich(size, bread, toppings, toasted);
    }
    /**
     * guides the user through adding a drink to their order.
     * @return The created Drink, or null if the process is cancelled.
     */
    public Drink addDrink(){

        System.out.println("\n===== Add a Drink =====");
        String size;
        double currentPrice = 0.0;
        while (true) {
            String choice = prompt("Choose size: \n(S)mall  \n(M)edium  \n(L)arge:").trim().toUpperCase();
            if (choice.equals("0")) return null;

            switch (choice) {
                case "S" -> {
                    size = "SMALL";currentPrice = 2.00;
                }
                case "M" -> {
                    size = "MEDIUM";currentPrice = 2.50;}
                case "L" -> {
                    size = "LARGE";currentPrice=3.00;}

                default -> {
                    System.out.println("Invalid entry; please enter S, M, L, or 0.");
                    continue;
                }
            }
            break;
        }
        System.out.printf("%s drink price: $%.2f%n", size, currentPrice);

        List<String> flavours = Drink.drinkOption.get(size);
        String flavor = selectFromList(size+ " Drink Option", flavours);
        if (flavor == null || flavor.equals("BACK")) {
            System.out.println("Drink skipped.");
            return null;
        }
        System.out.printf("Added %s $%.2f%n",flavor, currentPrice);
        return new Drink(size, flavor);
    }
    /**
     * guides the user through adding chips to their order.
     * @return The created Chips, or null if the process is cancelled.
     */
    public Chips addChips(){
        System.out.println("\n===== Add Chips =====");
        System.out.println("Chips price: $1.50");

        String flavor = selectFromList("Chips options", Chips.chipsOption);
        if ("BACK".equals(flavor) || flavor == null){
            System.out.println("Chips skipped.");
            return null;
        }
        Chips chips = new Chips(flavor);
        double price = chips.getPrice();
        System.out.printf("Added %s $%.2f%n", flavor,price);
        return new Chips(flavor);
    }
    /**
     * guides the user through ordering and customizing a special sandwich.
     * Includes removal and addition of toppings, and toasted option.
     * @return The created SpecialSandwiches, or null if the process is cancelled.
     */
    public Sandwich special() {
        System.out.println("===== Special Sandwiches =====\n1- BLT\n2- Philly Cheese Steak\n3- French Dip\n0- Cancel");
        System.out.println("Special Sandwich price: $12.00");
        String choice = prompt("Choose a sandwich: ");

        SpecialSandwiches selected = null;
        switch (choice) {
            case "1" -> selected = SpecialSandwiches.BLT();
            case "2" -> selected = SpecialSandwiches.PhillyCheeseSteak();
            case "3" -> selected = SpecialSandwiches.FrenchDip();
            default -> {
                System.out.println("returning to home menu...");
                return null;
            }
        }
        double currentPrice = selected.getPrice();
        System.out.printf("%s price: $%.2f%n", selected.getName(), currentPrice);

        //REMOVE topping
        List<Topping> current = selected.getMutableToppings();
        String remove = prompt("Would you like to remove any toppings? (y/n): ").toLowerCase();
        if(remove.equals("y")) {
            for (int i = 0; i < current.size(); i++) {
                Topping topping = current.get(i);
                System.out.printf("%d) Remove %s? (yes/no): ", i + 1, topping.getName());
                String resp = s.nextLine().trim().toLowerCase();
                if (resp.startsWith("y")) {
                    current.set(i, null);
                }
            }
            current.removeIf(Objects::isNull);
            System.out.printf("Added total: $%.2f%n", currentPrice);
        } else {
            System.out.println("Keeping all toppings.");
        }

        //asks if user want to add extra topping
        currentPrice = selected.getPrice();
        System.out.printf("Price: $%.2f%n", currentPrice);
        String addMore = prompt("Would you like to add extra toppings? (y/n): ").toLowerCase();
        if(addMore.equals("y")){
            System.out.println("Add extra toppings press 0 to skip a category):");
            String[] categories = {"MEAT", "EXTRA MEAT", "CHEESE", "EXTRA CHEESE", "REGULAR", "SAUCE", "SIDE"};
            int input = 0;
            while (input < categories.length) {
                String category = categories[input];
                String chosen = selectFromList(category, Topping.options.get(category));

                if ("BACK".equals(chosen)) {
                    if (input > 0) {
                        input--;// step back one category
                    }
                    continue;
                }
                if (chosen == null) {
                    input++; // skip this category
                    continue;
                }
                Topping t = new Topping(category, chosen);
                current.add(t);
                double cost = t.getPrice(selected.getSizeInches());
                currentPrice += cost;
                System.out.printf("%s added: $%.2f%n\nTotal: $%.2f added",
                        chosen, cost, currentPrice);
                input++;
            }
        } else {
            System.out.println("No extra toppings added.");
        }
        // Ask for toasted option
        String toast = prompt("Would you like it toasted? (yes/no): ").toLowerCase();
        boolean toasted = toast.equals("yes") || toast.equals("y");

        List<Topping> defaultToppings = selected.getToppings();
        List<Topping> extrasOnly = new ArrayList<>(current);
        extrasOnly.removeAll(defaultToppings); // only keep added toppings

        return new SpecialSandwiches(
                selected.getName(),
                selected.getSizeInches(),
                selected.getBreadType(),
                defaultToppings,
                toasted,
                extrasOnly
        );
    }

    /**
     * displays a numbered list of options to the user and prompts for a selection.
     * @param label The label or title for the option list.
     * @param options The list of options to display.
     * @return The selected option as a string, or "BACK"/null for special actions.
     */
    private String selectFromList(String label, List<String> options) {
        System.out.printf("\nAvailable: %s\n" , label);
        for(int i =0; i<options.size(); i++){
            System.out.println((i + 1)+") "+ options.get(i));
        }
        while (true) {
            String input = prompt("Choose a number (0 to skip, B to back): ").trim();
            if (input.equalsIgnoreCase("B")) return "BACK";
            try {
                int choice = Integer.parseInt(input);
                if (choice == 0) {
                    return null;
                }
                if (choice >= 1 && choice <= options.size()) {
                    return options.get(choice - 1);
                }
            } catch (NumberFormatException e) {
            }
            System.out.print("Invalid entry; please enter 1–" + options.size() + ", 0 to skip, or B to back.");
        }
    }
}
