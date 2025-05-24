package com.DELI.model;

public class Drink implements PricedItem{
    private final String size;
    private final String flavour;

    public Drink(String size, String flavour) {
        this.size = size.toUpperCase();
        this.flavour = flavour.toUpperCase();
    }


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
        return size + " "+ flavour + " drink";
    }
}
