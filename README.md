# GuessingGame

What is the Guessing Game?
The game is two-person game called "Guess the Number". The first player thinks of an integer within a known range. The second player tries to guess the number. If the guess is incorrect, then the first player tells the second player whether the guess was too high or too low. Eventually, the second player guesses the correct number. The second player's score equals the number of guesses he made. The players then reverse their roles and repeat the game. The winner is the player who gets the correct answer with the fewest guesses.

The key strategy in this game is to generate a clever guess. If, for example, the second player knows the number is between 0 and 100, then a reasonable first guess is 50. This choice evenly splits the range, giving you the maximum amount of information about the next guess. If the first player says the guess is too low, then the second player splits the reduced range and guesses 75. If the player says the guess is too high, then the optimal guess is 25. It can be shown that by splitting the remaining range in half after each guess, it will, at worst, take the second player no more than $\log_2 n$ guesses to find the unknown number where $n$ is the initial range. So if the unknown number lies between 0 and 7, then it can be guessed in no more than $\log_2 8 = 3$ guesses.

Installer Link: https://drive.google.com/open?id=1OkoeGuBKgjTmf9du_-wFZi4JtG533v9t

![Screenshot (738)](https://user-images.githubusercontent.com/40406575/80440577-a86ccd80-893b-11ea-99e6-683b956b9709.png)
