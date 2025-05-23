package com.DELI.model;

public class Toppings {
    private  final String category;
    private final String name;

    //
    public static final String meat = "MEAT";
    public static final String extraMeat = "EXTRA MEAT";
    public static final String cheese = "CHEESE";
    public static final String extraCheese = "EXTRA CHEESE";
    public static final String regular = "REGULAR";
    public static final String sauce = "SAUCE";
    public static final String side = "SIDE";

    public Toppings(String category, String name) {
        this.category = category.toUpperCase();
        this.name = name;
    }

    public double getPrice(int sandwichSize){
        if(sandwichSize != 4 && sandwichSize != 8 && sandwichSize != 12){
            throw new IllegalArgumentException("Size must be 4, 8, or 12 inches");
        }
        switch (category){
            case meat:
                return (sandwichSize == 4 ? 1.00 :sandwichSize == 8 ? 2.00 :3.00);
            case extraMeat:
                return (sandwichSize == 4 ? 0.50 :sandwichSize == 8 ? 1.00 : 1.50);
            case cheese:
                return (sandwichSize == 4 ? 0.75 :sandwichSize == 8 ? 1.50 : 2.25);
            case extraCheese:
                return (sandwichSize == 4 ? 0.30 : sandwichSize == 8 ? 0.60 : 0.90);
            default:
                return 0.0;
        }
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }
}
