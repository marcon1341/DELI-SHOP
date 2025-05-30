package ui;

import com.DELI.model.Menu;
import com.DELI.model.Symbol;
/**
 * The entry point for the DELI-shop
 * Prints the application banner and launches the main menu system.
 */
public class Main {
    public static void main(String[] args) {
        Symbol.printBanner();
        Menu menu = new Menu();
        menu.homeScreen();
    }
}
