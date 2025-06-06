package com.DELI.model;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * represents a customer's order.
 * An order can contain multiple priced items (such as sandwiches, drinks, chips, or specials),
 * and supports features like bag requests, tips, and receipt generation with tax calculations.
 */
public class Order {
    private final List<PricedItem> items = new ArrayList<>();//list of all items
    private static final double taxRate = 0.1025; // 10.25% tax
    private boolean bagRequested = false;
    private static final double bagFee = 0.08;
    private double tipAmount = 0.0;
    private boolean tipApplied = false;

    /**
     * adds a priced item (sandwich, drink, etc.) to the order.
     * @param item the item to add and mustn't be null
     * @throws IllegalArgumentException if the item is null
     */
    public void addItem(PricedItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Can't add null item");
        }
        items.add(item);
        System.out.printf("Total price Added: $%.2f" , getTotal());
    }
    //request a bag
    public void requestBag() {
        this.bagRequested = true;
    }
    //add a tip
    public void addTip(double tip) {
        if (tip < 0) return;
        this.tipAmount = tip;
        this.tipApplied = true;
    }
    /**
     * calculates the total price of all items in the order (before tax, tip, and bag fee).
     * @return the total sum of all item prices
     */
    public double getTotal() {
        double total = 0.0;
        for (PricedItem item : items) {
            total += item.getPrice();
        }
        return total;
    }
    /**
     * completes the checkout process, prints a detailed summary to the console,
     * calculates subtotal, tip, tax, and total, and writes an itemized receipt to a file.
     * The receipt is saved in the sales-receipts folder with a timestamped filename.
     */
    public void checkout() {
        System.out.println("\n===== Order Summary =====");

        StringBuilder sb = new StringBuilder();
        sb.append("DELI-SHOP Receipt\n");
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd - HHmmss"));
        sb.append("Time: ").append(time).append("\n");

       double subtotal = 0.0;
        for (PricedItem item : items) {
            if (item instanceof SpecialSandwiches special) {
                // print base only
                double extrasSum = 0;
                for (Topping t : special.getExtraToppings()) {
                    extrasSum += t.getPrice(special.getSizeInches());
                }
                double basePrice = special.getPrice() - extrasSum;
                System.out.printf("%s $%.2f\n", special, basePrice);
                sb.append(String.format("%s $%.2f\n", special, basePrice));
                subtotal += basePrice;

                // lists each extra and add its cost
                if (special.getExtraToppings() != null && !special.getExtraToppings().isEmpty()) {
                    for (Topping topping : special.getExtraToppings()) {
                        double price = topping.getPrice(special.getSizeInches());
                        if (price > 0) {
                            System.out.printf("  + %-20s $%.2f\n", topping.getName(), price);
                            sb.append(String.format("  + %-20s $%.2f\n", topping.getName(), price));
                            subtotal += price;
                        }
                    }
                }

            } else if (item instanceof Sandwich sandwich) {
                System.out.printf("%s $%.2f\n", sandwich, sandwich.getPrice());
                sb.append(String.format("%s $%.2f\n", sandwich, sandwich.getPrice()));
                subtotal += sandwich.getPrice();

                double breadPrice = sandwich.getBaseBreadPrice();
                System.out.printf("Bread type: %-9s $%.2f\n",
                        sandwich.getBreadType().toUpperCase(), breadPrice);
                sb.append(String.format("Bread type: %-9s $%.2f\n",
                        sandwich.getBreadType().toUpperCase(), breadPrice));

                for (Topping topping : sandwich.getToppings()) {
                    double price = topping.getPrice(sandwich.getSizeInches());
                    String label = topping.getName();
                    if (topping.getCategory().equalsIgnoreCase("EXTRA MEAT")
                            || topping.getCategory().equalsIgnoreCase("EXTRA CHEESE")) {
                        label = "EXTRA " + label;
                    }
                    System.out.printf("%-20s  $%.2f\n", label, price);
                    sb.append(String.format("%-20s  $%.2f\n", label, price));
                }

            } else {
                // for Chips an Drink
                String name = item.toString();
                System.out.printf("%-21s $%.2f\n", name, item.getPrice());
                sb.append(String.format("%-21s $%.2f\n", name, item.getPrice()));
                subtotal += item.getPrice();
            }
        }
        if (bagRequested) {
            System.out.printf("%-21s $%.2f\n", "Bag", bagFee);
            sb.append(String.format("%-21s $%.2f\n", "Bag", bagFee));
            subtotal += bagFee;
        }
        System.out.printf("Subtotal: $%.2f\n", subtotal);
        sb.append(String.format("%-30s $%.2f\n", "Subtotal:", subtotal));

        // Tip
        if (tipApplied) {
            System.out.printf("Tip: $%.2f\n", tipAmount);
            sb.append(String.format("%-30s $%.2f\n", "Tip:", tipAmount));
            subtotal += tipAmount;
        }

        // Tax
        double tax = subtotal * taxRate;
        System.out.printf("Tax (%.2f%%): $%.2f\n", taxRate * 100, tax);
        sb.append(String.format("%-30s $%.2f\n",
                String.format("Tax (%.2f%%):", taxRate * 100), tax));

        double total = subtotal + tax;
        System.out.printf("Total: $%.2f\n", total);
        sb.append(String.format("Total: $%.2f\n", total));

        Path receiptsDir = Paths.get("sales-receipts");
        try {
            Files.createDirectories(receiptsDir);
            Path receiptFile = receiptsDir.resolve(time + ".txt");
            Files.writeString(receiptFile, sb.toString(), StandardOpenOption.CREATE_NEW);
            System.out.println("Receipt saved to " + receiptFile);
        } catch (IOException e) {
            System.out.println("Failed to save sales-receipts: " + e.getMessage());        }
    }

}

