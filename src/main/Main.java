package main;

import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    public void start() {
        int[] intArray = IntStream.range(1, 101).toArray();

        int numberToFind = random.nextInt(100);
        int userGuest = -1;

        while (numberToFind != userGuest) {
            System.out.println("THIS IS THE NUMBER TO FIND: " + numberToFind);

            IntStream.of(intArray).forEach(e -> {
                System.out.print(e + " ");
                if (e % 10 == 0) {
                    System.out.println();
                }
            });

            System.out.print("\nGuess the number: ");
            userGuest = scanner.nextInt();

            if (userGuest == numberToFind) {
                System.out.println("Congrats You Won");
            }

            else {
                if (userGuest > numberToFind) {
                    intArray = IntStream.range(1, userGuest).toArray();
                    System.out.println("TOO BIG");
                }

                else {
                    intArray = IntStream.range(userGuest + 1, intArray.length).toArray();
                    System.out.println("TOO SMALL");
                }
            }
        }
    }
}
