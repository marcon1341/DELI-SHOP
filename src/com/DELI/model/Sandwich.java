package com.DELI.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a customizable sandwich order.
 * A sandwich consists of a size (4, 8, or 12 inches), a bread type,
 * a list of toppings, and an option to be toasted.
 * The price is calculated based on size and the selected toppings.
 */

public class Sandwich implements PricedItem{
    private final int sizeInches;//must be 4, 8 and 12 inches
    private final String breadType;
    private final List<Topping> toppings;
    private final boolean toasted;

    // sandwich size in inch
    public static final int SIZE_4_INCH = 4;
    public static final int SIZE_8_INCH = 8;
    public static final int SIZE_12_INCH = 12;

    //base price by size
    private static final double baseFourInches = 5.50;
    private static final double baseEightInches = 7.00;
    private static final double baseTwelveInches = 8.50;

    /**
     * Constructs a Sandwich with the specified size, bread type, toppings, and toasting preference.
     *
     * @param sizeInches the sandwich size in inches (must be 4, 8, or 12)
     * @param breadType the type of bread ("white", "wheat", "rye", "wrap", or "ciabatta")
     * @param toppings a list of Topping objects to be added to the sandwich
     * @param toasted whether the sandwich is toasted
     * @throws IllegalArgumentException if size or bread type is invalid
     * @throws NullPointerException if bread type or toppings list is null
     */
    public Sandwich(int sizeInches, String breadType, List<Topping> toppings, boolean toasted) {

        //Checks if the size is valid.
        if(sizeInches != SIZE_4_INCH && sizeInches != SIZE_8_INCH && sizeInches != SIZE_12_INCH){
            throw new IllegalArgumentException(
                    "Invalid sandwich size: " + sizeInches + " (must be 4, 8 or 12 inches)");
        }
        Objects.requireNonNull(breadType, "Bread type can't be null");
        String normalizedBread = breadType.trim().toLowerCase();
        if (!normalizedBread.equals("white") && !normalizedBread.equals("wheat")
                && !normalizedBread.equals("rye") && !normalizedBread.equals("wrap") &&
        !normalizedBread.equals("ciabatta")){
            throw new IllegalArgumentException("Bread type must be one of: white, wheat, rye, wrap, ciabatta");
        }
        Objects.requireNonNull(toppings, "Toppings list cannot be null");
        this.sizeInches = sizeInches;
        this.breadType = breadType;
        this.toppings = toppings;
        this.toasted = toasted;
    }

    /**
     * calculate total price for sandwich based on size and toppings
     * @return total price as a double
     */
    @Override
    public double getPrice() {
        double total = switch (sizeInches){
            case SIZE_4_INCH -> baseFourInches;
            case SIZE_8_INCH -> baseEightInches;
            case SIZE_12_INCH -> baseTwelveInches;
            default -> throw new IllegalStateException("Unexpected value: " + sizeInches);
        };
        //add topping cost
        for(Topping topping: toppings){
            total += topping.getPrice(sizeInches);
        }
        return total;
    }

    /**
     * return the base price excluding toppings
     * @return base price as double
     */
    public double getBaseBreadPrice() {
        return switch (sizeInches) {
            case SIZE_4_INCH -> baseFourInches;
            case SIZE_8_INCH -> baseEightInches;
            case SIZE_12_INCH -> baseTwelveInches;
            default -> 0.0;
        };
    }

    /**
     * gets size of sandwich
     * @return sandwich size(4,8 or 12
     */
    public int getSizeInches() {
        return sizeInches;
    }
    /**
     * gets the type of bread
     * @return the bread type as a String
     */
    public String getBreadType(){
        return this.breadType;
    }
    /**
     * returns the list of toppings
     * @return the list of Topping objects
     */
    public List<Topping> getToppings(){
        return toppings;
    }
    /**
     * shows is toasted.
     * @return true if toasted, false otherwise
     */
    public boolean isToasted(){
        return toasted;
    }

    /**
     * returns a string representation of the sandwich, including size, bread type, toppings, and toasting.
     * @return a user friendly description
     */
    @Override
    public String toString() {
        String toppingList = toppings.isEmpty()
                ? ""
                : " with " + toppings.stream()
                .map(Topping::getName)
                .collect(Collectors.joining(", "));
        String toasted = this.toasted ? " (toasted)" : "";
        return String.format("Sandwich: %d\" %s bread %s %s",
                sizeInches,
                breadType,
                toppingList,
                toasted);
    }
}

