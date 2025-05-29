
package com.DELI.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Menu {
    Scanner s = new Scanner(System.in);

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
                    // 1) Ask about bag
                    String bag = prompt("Would you like a bag for $0.08? (y/n): ").toLowerCase();
                    if (bag.equals("y")) {
                        order.requestBag();
                        System.out.println("Bag fee of $0.08 added.");
                    }

                    // 2) ask about tip
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

    public String prompt(String message){
        System.out.println(message);
        return s.nextLine().trim();
    }

    public Sandwich addSandwich() {
        System.out.println("\n===== Make your Sandwich =====");
        System.out.println("(Enter 0 to go back)");

        // sandwich size
        int size = 0;
        while(size != 4 && size != 8 && size != 12){
            String sizeInput = prompt("Enter sandwich size (4 / 8 / 12): ");
            if(sizeInput.equals("0")) return  null;
            try{
                size = Integer.parseInt(sizeInput);
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


        //toppings
        List<Topping> toppings = new ArrayList<>();
        String[] categories = {"MEAT","EXTRA MEAT","CHEESE","EXTRA CHEESE","REGULAR","SAUCE","SIDE"};
        int place = 0;
        while (place < categories.length) {
            String category = categories[place];
            String chosen = selectFromList(category, Topping.options.get(category));

        if (chosen == null) {
            // Skip this category
            place++;
            continue;
        }
            if (chosen.equals("BACK")) {
                // Step back one category
                if (place > 0) {
                    place--;
                    continue;
                } else {
                    return null;
                }
            }
            toppings.add(new Topping(category, chosen));
            System.out.println("Added: " + chosen + " ("+ category + ")");
        }
        //toasted
        boolean toasted = false;
        String toast = prompt("Would you like it toasted? (yes/no): ").toLowerCase();
        if(toast.equals("0")) return null;
        toasted = toast.equals("yes") || toast.equals("y");

        return new Sandwich(size, bread, toppings, toasted);
    }

    public Drink addDrink(){

        System.out.println("\n===== Add a Drink =====");
        String size;
        while (true) {
            String choice = prompt("Choose size: \n(S)mall  \n(M)edium  \n(L)arge:").trim().toUpperCase();
            if (choice.equals("0")) return null;

            switch (choice) {
                case "S" -> size = "SMALL";
                case "M" -> size = "MEDIUM";
                case "L" -> size = "LARGE";
                default -> {
                    System.out.println("Invalid entry; please enter S, M, L, or 0.");
                    continue;
                }
            }
            break;
        }

        List<String> flavours = Drink.drinkOption.get(size);
        String flavour = selectFromList(size+ " Drink Option", flavours);
        if (flavour == null || flavour.equals("BACK")) {
            System.out.println("Drink skipped.");
            return null;
        }

        return new Drink(size, flavour);
    }

    public Chips addChips(){
        System.out.println("\n===== Add Chips =====");

        String flavour = selectFromList("Chips options", Chips.chipsOption);
        if ("BACK".equals(flavour) || flavour == null){
            System.out.println("Chips skipped.");
            return null;
        }
        return new Chips(flavour);
    }

    public Sandwich special() {
        System.out.println("===== Special Sandwiches =====\n1- BLT\n2- Philly Cheese Steak\n3- French Dip\n0- Cancel");

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
        System.out.println("Selected: " + selected);

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
        } else {
            System.out.println("Keeping all toppings.");
        }

        //asks if user want to add extra topping
        String addMore = prompt("Would you like to add extra toppings? (y/n): ").toLowerCase();
        if(addMore.equals("y")){
            System.out.println("Add extra toppings press 0 to skip a category):");
            String[] categories = {"MEAT", "EXTRA MEAT", "CHEESE", "EXTRA CHEESE", "REGULAR", "SAUCE", "SIDE"};
            int input = 0;
            while (input < categories.length) {
                String category = categories[input];
                String chosen = selectFromList(category, Topping.options.get(category));

                if ("BACK".equals(chosen)) {
                    // step back one category
                    if (input > 0) {
                        input--;
                    }
                    continue;
                }
                if (chosen == null) {
                    // skip this category
                    input++;
                    continue;
                }
                current.add(new Topping(category, chosen));
                System.out.println("Added: " + chosen + " (" + category + ")");
                input++;
            }
        } else {
            System.out.println("No extra toppings added.");
        }
        // Ask for toasted
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
                if (choice == 0) return null;
                if (choice >= 1 && choice <= options.size()) {
                    return options.get(choice - 1);
                }
            } catch (NumberFormatException e) {
            }
            System.out.print("Invalid entry; please enter 1–" + options.size() + ", 0 to skip, or B to back.");
        }
    }
}
