package com.DELI.model;

public class Chips implements PricedItem {
    private final String flavour;
    private static  final double price = 1.50;

    public Chips(String flavour) {
        this.flavour = flavour.toUpperCase();
    }

    @Override
    public double getPrice() {
        return price;
    }
    public String getFlavour(){
        return flavour;
    }
    @Override
    public String toString(){
        return flavour + " Chips";
    }
}
