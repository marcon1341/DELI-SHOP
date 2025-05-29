package com.DELI.model;

import java.util.List;
import java.util.Map;

public class Drink implements PricedItem{
    private final String size;//small/medium/large
    private final String flavor;

    public Drink(String size, String flavour) {
        this.size = size.toUpperCase();//stores to uppercase
        this.flavor = flavour.toUpperCase();//stores to uppercase
    }
    //Drink flavours list  map for size and list for flavor
    public static final Map<String, List<String>> drinkOption = Map.of(
            "SMALL", List.of("Coke","Sprite","Pepsi", "Lemonade", "Root Beer", "Water"),
            "MEDIUM", List.of("Coke","Sprite","Pepsi", "Lemonade", "Root Beer", "Water"),
            "LARGE", List.of("Coke", "Sprite","Pepsi", "Lemonade", "Root Beer", "Water")
    );
    @Override
    public double getPrice() {
       return switch (size){
           case "SMALL" -> 2.00;
           case "MEDIUM" -> 2.50;
           case "LARGE" -> 3.00;
           default -> throw new IllegalArgumentException("Drink size unavailable" + size);
       };
    }
    @Override
    public String toString(){
        return size + " "+ flavor + " drink";
    }
}
