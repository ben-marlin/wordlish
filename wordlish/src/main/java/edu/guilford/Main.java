package edu.guilford;

public class Main {
    // ANSI escape codes for text colors
    private static final String RESET = "\u001B[0m";
    private static final String BLACK = "\u001B[30m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";
    private static final String BOLD = "\033[1m";
    private static final String UNDERLINE = "\033[4m";
    
    public static void main(String[] args) {
        // Hard-coded array of 100 five-letter words
        String[] words = {
            "apple", "grape", "melon", "peach", "lemon",
            "berry", "chess", "grass", "water", "stone",
            "brick", "light", "night", "chair", "table",
            "clock", "plant", "glass", "frame", "track",
            "wheat", "flame", "cloud", "floor", "spoon",
            "knife", "fruit", "bread", "salad", "sugar",
            "cream", "paint", "brush", "earth", "ocean",
            "storm", "field", "shade", "sword", "armor",
            "crown", "globe", "tiger", "eagle", "shark",
            "whale", "zebra", "horse", "sheep", "snake",
            "mouse", "goose", "steel", "metal", "stone",
            "flint", "forge", "weave", "hinge", "screw",
            "laser", "torch", "cable", "drill", "flask",
            "badge", "chain", "grill", "gauge", "lever",
            "patch", "pouch", "quill", "scale", "shear",
            "slice", "spike", "strap", "vapor", "valve",
            "vital", "yield", "blend", "bound", "braid",
            "broom", "crane", "clamp", "clasp", "angle",
            "adopt", "alter", "alert", "bless", "blink",
            "build", "bring", "cover", "grove", "slant"
        };

        // choose one of the words
        String chosenWord = words[0].toUpperCase();

        // create blank string for printing
        String colorExample = new String();
        
        // make string w/ alternating colors, space b/w letters
        colorExample += PURPLE + chosenWord.charAt(0) + " ";
        colorExample += RED + chosenWord.charAt(1) + " ";
        colorExample += GREEN + chosenWord.charAt(2) + " ";
        colorExample += BLUE + chosenWord.charAt(3) + " ";
        colorExample += YELLOW + chosenWord.charAt(4) + " ";
        colorExample += RESET;

        System.out.println(colorExample);
    }

}