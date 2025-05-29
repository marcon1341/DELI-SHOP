package com.DELI.model;

public class Symbol {
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";
    private static final String ORANGE = "\u001B[38;5;208m";
    private static final String CYAN = "\u001B[36m";
    private static final String BOLD = "\u001B[1m";
    private static final String WHITE_BOLD = "\u001B[1;37m";

    public static void printBanner() {
        System.out.println("\n" + ORANGE +
                "  🍞🥩🥪 " + WHITE_BOLD + "D E L I - S H O P" + ORANGE + " 🥪🥩🍞");
        System.out.println(RESET +
                "" + YELLOW + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(
                GREEN + "  B E T W E E N   T H E   B R E A D\n");
    }

    public static void printWelcomeBanner() {
        System.out.println("     🍔" + CYAN + "WELCOME TO DELI-SHOP" + YELLOW + "  🧀 ");
        System.out.println("════════════════════════════════════");
        System.out.println(" " + GREEN + "1" + YELLOW + " → 🛒 " + CYAN + "Start a New Order");
        System.out.println(" " + RED + "0" + YELLOW + " → ❌ " + RED + "Exit ");
        System.out.print(BOLD + CYAN + "Enter your choice: ");
    }

    public static void printOrderMenu() {
        System.out.println(BOLD + YELLOW + "\n════════════════════════════════");
        System.out.println("        🧾 " + CYAN + "ORDER MENU" + YELLOW);
        System.out.println("════════════════════════════════");
        System.out.println(GREEN + "1" + YELLOW + " → 🥪 " + CYAN + "Add Sandwich");
        System.out.println(GREEN + "2" + YELLOW + " → 🥤 " + CYAN + "Add Drink");
        System.out.println(GREEN + "3" + YELLOW + " → 🍟 " + CYAN + "Add Chips");
        System.out.println(GREEN + "4" + YELLOW + " → 🛒 " + CYAN + "Checkout Order");
        System.out.println(GREEN + "5" + YELLOW + " → 🥙 " + CYAN + "Add Special Sandwich");
        System.out.println(RED + "0" + YELLOW + " → ❌ " + RED + "Cancel Order");
        System.out.print(BOLD + CYAN + "Please choose to proceed: ");
    }
}