package Ons;

import com.DELI.model.PricedItem;

import java.util.List;
import java.util.Map;
/**
 * represents a drink item that can be added to an order.
 * Each Drink has a size (SMALL, MEDIUM, LARGE) and a flavor (e.g., Coke, Sprite).
 * Price depends on the selected size.
 */
public class Drink implements PricedItem {
    private final String size;//small/medium/large
    private final String flavor;

    /**
     * constructs a Drink object with the specified size and flavor.
     * @param size the size of the drink (case-insensitive, should be SMALL, MEDIUM, or LARGE)
     * @param flavour the flavor of the drink (case-insensitive)
     */
    public Drink(String size, String flavour) {
        this.size = size.toUpperCase();//stores to uppercase
        this.flavor = flavour.toUpperCase();//stores to uppercase
    }
    //Drink flavours map for size and list for flavor
    public static final Map<String, List<String>> drinkOption = Map.of(
            "SMALL", List.of("Coke","Sprite","Pepsi", "Lemonade", "Root Beer", "Water"),
            "MEDIUM", List.of("Coke","Sprite","Pepsi", "Lemonade", "Root Beer", "Water"),
            "LARGE", List.of("Coke", "Sprite","Pepsi", "Lemonade", "Root Beer", "Water")
    );
    /**
     * returns the price of the drink based on its size.
     * @return the price as a double
     * @throws IllegalArgumentException if the size is not recognized
     */
    @Override
    public double getPrice() {
       return switch (size){
           case "SMALL" -> 2.00;
           case "MEDIUM" -> 2.50;
           case "LARGE" -> 3.00;
           default -> throw new IllegalArgumentException("Drink size unavailable" + size);
       };
    }
    /**
     * returns a string representation of the drink for receipts and display.
     * @return the drink description size and flavor
     */
    @Override
    public String toString(){
        return size + " "+ flavor;
    }
}
