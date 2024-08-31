# Wordlish

During the Pandemic, a lot of my friends got hooked on the game Wordle, a word game created by Josh Wardle. Some of my friends still play every day! My daughter got this assignment in her coding class, so I thought we could make a version of it, too.

The game secretly selects a five-letter word. It has a player input a five-letter word. The game then changes the color of the letters input - gray for letters that weren't in the secret word at all, yellow for letters that appeared, but were in a different position, and green for letters that appeared and were in the right position. The player got six guesses. If any of the guesses were correct, play stopped. If the player guessed incorrectly six times, the secret word was revealed.

As this first version is going to be simplified, I figured we'd say it was just Wordle-ish. 

## Sample Code

The sample code demonstrates using ASCII codes to change text color. We put the color codes into appropriately named variables that will be easy to remember. They are defined outside `main` just for readability.

You may want to experiment with changing the colors to other choices to see what is most readable on your screen. I found yellow didn't show up very well on my Macbook in dark theme for some reason.

## Choosing the Initial Word

For testing purposes, let's just make the program choose "apple" every time. Later, we'll randomize it, as in the Word Guessing Game.

```
// establish word to copy
String chosenWord = words[0].toUpperCase();
char[] chosenArray = chosenWord.toCharArray();
```

Notice that we create an array of the characters. This saves us the cumbersome task of reading out individual characters or using the string method `equals()`. Primitive data types use == and != as comparisons.

## Making Array of Color Codes

Since we may need to make each of the letter displays a different color, we create an array of the same length. Because the color codes were strings instead of characters, we'll make these color codes strings as well. Since we're doing that anyhow, let's add a blank space to each. This will let us space out the characters in the final product. Insert code like the following.

```
String[] colorCodes = {RESET+" ", RESET+" ", RESET+" ", RESET+" ", RESET+" "};
```

The code for `RESET` should turn off whatever other color may have been set before. We could also have chosen a color we wanted all display that wasn't changed in, like gray or white. Next we need to change the colors according to the rules decided.

## Make a Tentative Guess

Let's just choose "grape" as our first guess so the testing is straightforward. It shares the letters a, e, and p with "apple" and e is even in the right position. Later, we'll use a `Scanner` to input a word and validate their entry.

```
// establish word to compare
String guessWord = words[1].toUpperCase();
char[] guessArray = guessWord.toCharArray();
```

Again, we create a character array for comparisons.

## Checking Whether Guess Letters Are In Chosen

We loop through each entry of `guessArray` using the loop variable `i`. Nested inside each of these loops, we loop through each entry of `chosenArray` using the loop variable `j`. When the two match, we need to change the color of what we're going to display to `RED`. The idea of the comparison follows.

|   | G | R | A | P | E |
|:--:|:--:|:--:|:--:|:--:|:--:|
| A |   |   | ✔ |   |   |
| P |   |   |   | ✔ |   |
| P |   |   |   | ✔ |   |
| L |   |   |   |   |   |
| E |   |   |   |   | ✔ |

Since we want to change the color for the letters A, P, and E, we'll record that as follows. I chose red because it contrasted well on my monitor. Feel free to select your own colors.

```
if (guessArray[i] == chosenArray[j]) {
    colorCode[i] = RED;
}
```

Notice that we recorded `colorCode[i]` to match `guessArray[i]`, since we'll be displaying the user's guess in the final result.

Now we'll go back through, comparing to see if the letters of the guess were in the right position. This means a single loop rather than a nested loop.

```
for (int i = 0; i < 5; i++) {
    if (guessArray[i] == chosenArray[i]) {
        colorCode[i] = GREEN;
    }
}    
```

We did this check *after* the check whether the letter was in the word at all in case the guessed word, as letters that appear may not all appear at the right position, but some might. 

## Building the Word to Be Printed

In order to print with these colors, we build a string that alternates `colorCode` followed by `guessArray`.

```
// build a word to be printed with colors from colorCodes
String printWord = new String();

// loop builds string alternate code & letters
for (int i = 0; i < 5; i++) {
    printWord += colorCodes[i] + guessArray[i];
}

// reset at end of word
printWord += RESET;
```

As we've seen before, the `+=` will concatenate a new piece on the end of the string. The final `RESET` isn't strictly necessary, but without it, the terminal print will be in the color of the last letter until the next time it's reset or changed.

Add a print statement that displays `printWord`. Test this to see if it looks good for you.

## Extensibility

We should always be on the lookout for ways to make our program a little better. In all the preceding code, you have loops of length 5 because both the guess and the chosen word had 5 letters. In case you might want to make a similar game with a different number of letters, you should change all the 5s to `chosenWord.length()`.

## Accepting User Input

On the route to making this program into an actual game, we need to accept user input. We should test our program on other word matches, too, so let's change the section about establishing the word to compare.

Create a `Scanner` object and prompt the user to input a five-letter word. People being people, they'll mess that up. If the length of `guessArray` and `choiceArray` are different, our program will either encounter a syntax error or do something unexpected.

```
// establish word to compare
Scanner scan = new Scanner(System.in);

System.out.print("A 5-letter word was chosen. What is your guess? ");
String guessWord = scan.next().toUpperCase();

while (guessWord.length() != 5) {
    System.out.println("Your guess didn't have 5 letters!");
    System.out.print("Guess again. ");
    guessWord = scan.next().toUpperCase();
}
```

This will hang until the user guesses a 5-letter word. Technically, they can give numbers or symbols. The original game consults their private list of 5-letter words and disallows any entry that's not on it. We can implement this later, but it seems restrictive at this point.

Alternately, you could replace the `while` loop with this, which simply discards anything over 5 letters.

```
guessWord = guessWord.substring(5);
```

Don't delete the part where you define `guessArray`!

## Loop for 6 Guesses

We could literally repeat all this code 5 more times, but that wouldn't be very good coding practice. We know how to make a loop, so it makes sense to simply use a `for` loop.

```
for (int k = 0; k < 6; k++) {
    // establish word to compare
        <LOTS OF CODE>
    System.out.println(printWord);
}
```

When you run this, you should get errors. At multiple points in this section of code, you declared variables. As the program tries to loop through this code, it encounters a declaration for the same variable over again - and as we've discovered, that causes an error.

To fix this, you have to move your declarations to precede this loop. Don't remove code that establishes the values, but the declaration (and for some an initial instantiation) needs to precede the loop. Use the following, but this will still take some thought. to find what to remove.

```
// declare color code & guess variables
String[] colorCodes = {RESET + " ", RESET + " ", RESET + " ", RESET + " ", RESET + " "};
String guessWord = new String();
char[] guessArray = new char[5];
String printWord = new String();

// instantiate a scanner
Scanner scan = new Scanner(System.in);
```

## Handling a Correct Guess

Right now, the code forces a user to guess 6 times, even if the first guess was correct. To fix that, we use a `break`. Insert the following after printing `printWord` but before the end of the loop body.

```
if (chosenWord.equals(guessWord)) {
    break;
}
```

This will throw the program out of the loop if the user guessed correctly. But that doesn't quite end the way we want. The user should get a congratulatory message if they guessed correctly or be shown the correct answer if they didn't. Add the following after the loop.

```
if (chosenWord.equals(guessWord)) {
    System.out.println("Good job!");
} else {
    System.out.println("Correct answer was " + chosenWord);
}
```

After you get this to work, figure out a way to replace `chosenWord` with a string that has spaces between the letters. Use some color other than the default.

## Randomizing the Word Choice

Now return to the line where `chosenWord` was defined. Instantiate a randomizer and use it to randomly choose from the members of `words` like you did in Word Guess.

## Wrapping Up

As usual, test your program. Make sure you have comments that tell you what each piece does. We will return to this program later and you need to know what each piece did.

Save it if you haven't. Commit it to Github Classroom. Then let me know you're done on Canvas.