package com.DELI.model;

import java.util.ArrayList;
import java.util.List;

public class SpecialSandwiches extends Sandwich{
    private final String name;
    private static final double price = 12.00;
    private final List<Topping> extraToppings;

    public SpecialSandwiches(String name, int sizeInches, String breadType, List<Topping> toppings, boolean toasted, List<Topping> extraToppings) {
        super(sizeInches, breadType, new ArrayList<>(toppings), toasted);
        this.name = name;
        this.extraToppings = extraToppings;
    }

    public static SpecialSandwiches BLT(){
        return new SpecialSandwiches("BLT", 8, "white", List.of(
                new Topping("MEAT", "Bacon"),
                new Topping("CHEESE", "Cheddar"),
                new Topping("REGULAR", "Lettuce"),
                new Topping("REGULAR", "Tomatoes"),
                new Topping("SAUCE", "Mayo")
        ), true, new ArrayList<>());
    }

    public static SpecialSandwiches PhillyCheeseSteak() {
        return new SpecialSandwiches("Philly Cheese Steak", 8, "ciabatta", List.of(
                new Topping("MEAT", "Steak"),
                new Topping("CHEESE", "American"),
                new Topping("REGULAR", "Onions"),
                new Topping("REGULAR", "Peppers"),
                new Topping("SAUCE", "Mayo")
        ), true, new ArrayList<>());
    }
    public static SpecialSandwiches FrenchDip() {
        return new SpecialSandwiches("French Dip", 8, "ciabatta", List.of(
                new Topping("MEAT", "Pastrami"),
                new Topping("CHEESE", "Cheddar"),
                new Topping("SIDE", "Au Jus")
        ), true, new ArrayList<>());
    }

    public List<Topping> getMutableToppings(){
        return new ArrayList<>(super.getToppings());
    }

    public List<Topping> getExtraToppings() {
        return extraToppings;
    }


    public String getName() {
        return name;
    }

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

    @Override
    public String toString() {
        return String.format("Special Sandwich: %s", name);
    }
}

