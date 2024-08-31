package edu.guilford;

import java.util.Scanner;
import java.util.Random;

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

        Random rand = new Random();

        // establish word to copy
        String chosenWord = words[rand.nextInt(100)].toUpperCase();
        char[] chosenArray = chosenWord.toCharArray();

/*         // create blank string for printing
        String colorExample = new String();
        
        // make string w/ alternating colors, space b/w letters
        colorExample += PURPLE + chosenWord.charAt(0) + " ";
        colorExample += RED + chosenWord.charAt(1) + " ";
        colorExample += GREEN + chosenWord.charAt(2) + " ";
        colorExample += BLUE + chosenWord.charAt(3) + " ";
        colorExample += YELLOW + chosenWord.charAt(4) + " ";
        colorExample += RESET;

        System.out.println(colorExample); */

        // declare color code & guess variables
        String[] colorCodes = {RESET + " ", RESET + " ", RESET + " ", RESET + " ", RESET + " "};
        String guessWord = new String();
        char[] guessArray = new char[5];
        String printWord = new String();

        // instantiate a scanner
        Scanner scan = new Scanner(System.in);

        // loop for six repetitions
        for (int k = 0; k < 6; k++) {
            // make array of reset color codes
            for (int i = 0; i < 5; i++) {
                colorCodes[i] = RESET + " ";
            }

            // establish word to compare
            System.out.print("A 5-letter word was chosen. What is your guess? ");
            guessWord = scan.next().toUpperCase();
            
            while (guessWord.length() != 5) {
                System.out.println("Your guess didn't have 5 letters!");
                System.out.print("Guess again. ");
                guessWord = scan.next().toUpperCase();
            }

            guessArray = guessWord.toCharArray();

            // nested loop compares guess to answer, red if match
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (chosenArray[i] == guessArray[j]) {
                        colorCodes[j] = RED + " ";
                    }
                }
            }

            // loop compares guess to answer, green if match
            for (int i = 0; i < 5; i++) {
                if (chosenArray[i] == guessArray[i]) {
                    colorCodes[i] = GREEN + " ";
                }
            }

            // build a word to be printed with colors from colorCodes
            printWord = new String();

            // loop builds string alternate code & letters
            for (int i = 0; i < 5; i++) {
                printWord += colorCodes[i] + guessArray[i];
            }

            // reset at end of word
            printWord += RESET;

            // prints guess - yellow if in word, green if position correct
            System.out.println(printWord);

             // exit loop on correct guess
            if (guessWord.equals(chosenWord)) {
                break;
            }
        }

        // message given on correct guess
        if (guessWord.equals(chosenWord)) {
            System.out.println("You were right!");
        } else {
            System.out.println("The chosen word was " + chosenWord + ".");
        }

        scan.close();
    }

}