package com.DELI.model;

import java.util.ArrayList;
import java.util.List;
/**
 * represents a special sandwich (such as BLT, Philly Cheese Steak, or French Dip)
 * with a fixed base price(12.00)
 * This class extends Sandwich class.
 */
public class SpecialSandwiches extends Sandwich{
    private final String name;
    private static final double price = 12.00;
    private final List<Topping> extraToppings;

    /**
     * Constructs a SpecialSandwiches object.
     * @param name the name of the special sandwich
     * @param sizeInches the sandwich size in inches (typically 8)
     * @param breadType the bread type (e.g., "white", "ciabatta")
     * @param toppings the default list of toppings
     * @param toasted whether the sandwich is toasted
     * @param extraToppings the list of extra toppings added by the customer
     */
    public SpecialSandwiches(String name, int sizeInches, String breadType, List<Topping> toppings, boolean toasted, List<Topping> extraToppings) {
        super(sizeInches, breadType, new ArrayList<>(toppings), toasted);
        this.name = name;
        this.extraToppings = extraToppings;
    }


    /**
     *method for creating a BLT special sandwich.
     *@return a BLT SpecialSandwiches object
     */
    public static SpecialSandwiches BLT(){
        return new SpecialSandwiches("BLT", 8, "white", List.of(
                new Topping("MEAT", "Bacon"),
                new Topping("CHEESE", "Cheddar"),
                new Topping("REGULAR", "Lettuce"),
                new Topping("REGULAR", "Tomatoes"),
                new Topping("SAUCE", "Mayo")
        ), true, new ArrayList<>());
    }
    /**
     *method for creating a Philly Cheese Steak special sandwich.
     *@return a Philly Cheese Steak SpecialSandwiches object
     */
    public static SpecialSandwiches PhillyCheeseSteak() {
        return new SpecialSandwiches("Philly Cheese Steak", 8, "ciabatta", List.of(
                new Topping("MEAT", "Steak"),
                new Topping("CHEESE", "American"),
                new Topping("REGULAR", "Onions"),
                new Topping("REGULAR", "Peppers"),
                new Topping("SAUCE", "Mayo")
        ), true, new ArrayList<>());
    }
    /**
     *method for creating a French Dip special sandwich.
     *@return a French Dip SpecialSandwiches object
     */
    public static SpecialSandwiches FrenchDip() {
        return new SpecialSandwiches("French Dip", 8, "ciabatta", List.of(
                new Topping("MEAT", "Pastrami"),
                new Topping("CHEESE", "Cheddar"),
                new Topping("SIDE", "Au Jus")
        ), true, new ArrayList<>());
    }

    /**
     * returns a mutable list of the sandwich's default toppings for customization.
     * @return a new list containing the default toppings
     */
    public List<Topping> getMutableToppings(){
        return new ArrayList<>(super.getToppings());
    }
    /**
     * returns the list of extra toppings added by the customer.
     * @return the extra toppings list
     */
    public List<Topping> getExtraToppings() {
        return extraToppings;
    }
    /**
     * returns the name of the special sandwich.
     * @return the sandwich name
     */
    public String getName() {
        return name;
    }
    /**
     * Calculates the total price of the special sandwich, including any extra toppings.
     * @return the total price as a double
     */
    @Override
    public double getPrice() {
        double total = price;
        if (extraToppings != null) {
            for (Topping t : extraToppings) {
                total += t.getPrice(getSizeInches());
            }
        }
        return total;
    }
    /**
     * returns a string representation of the special sandwich.
     * @return a user friendly description of the sandwich
     */
    @Override
    public String toString() {
        return String.format("Special Sandwich: %s", name);
    }
}

