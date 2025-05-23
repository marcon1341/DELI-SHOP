package com.DELI.model;

import java.util.List;

public class Sandwich implements PricedItem{
    private final int sizeInches;//" must be 4, 8 and 12 inches
    private final String breadType;
    private final List<Toppings> toppings;
    private final boolean toasted;

    //base price by size
    private static final double baseFourInches = 5.00;
    private static final double baseEightInches = 7.00;
    private static final double baseTwelveInches = 8.50;

    public Sandwich(int sizeInches, String breadType, List<Toppings> toppings, boolean toasted) {
        if(sizeInches != 4 && sizeInches != 8 && sizeInches != 12){
            throw new IllegalArgumentException(
                    "Invalid sandwich size: " + sizeInches + " (must be 4, 8 or 12 inches)");
        }

        this.sizeInches = sizeInches;
        this.breadType = breadType;
        this.toppings = toppings;
        this.toasted = toasted;
    }

    @Override
    public double getPrice() {
        double basePrice = switch (sizeInches){
            case 4 -> baseFourInches;
            case 8 -> baseEightInches;
            case 12 -> baseTwelveInches;
            default -> throw new IllegalStateException("Unexpected value: " + sizeInches);
        };
        //add topping cost
        for(Toppings t: toppings){
            basePrice += t.getPrice(sizeInches);
        }
        return basePrice;
    }
}
