package Ons;

import com.DELI.model.PricedItem;

import java.util.List;
/**
 * represents a chips item that can be added to an order.
 * each Chips object has a flavor and a fixed price.
 */
public class Chips implements PricedItem {
    private final String flavor;
    private static  final double price = 1.50;

    /**
     * returns the flavor of the chips.
     * @return the chips flavor as a string
     */
    public String getFlavor(){
        return flavor;
    }
    /**
     * constructs a Chips object with the specified flavor.
     * @param flavour the chips flavor (case-insensitive)
     */
    public Chips(String flavour) {
        this.flavor = flavour.toUpperCase();
    }

    //Chips flavour lists
    public static final List<String> chipsOption = List.of("BBQ", "Sour Cream", "Salt & Vinegar", "Jalapeño", "Lays Classic");

    /**
     * returns the price of the chips.
     * @return the price as a double
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * returns a string representation of the chips for receipts and display.
     * @return the chips description by flavor (e.g., "BBQ Chips")
     */
    @Override
    public String toString(){
        return flavor + " Chips";
    }
}
