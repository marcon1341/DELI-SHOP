package com.DELI.model;

/**
 * this interface represent any item that has price can provide its price
 * and must define getPrice()
 */
public interface PricedItem {
    //return the price of item
    double getPrice();
}
