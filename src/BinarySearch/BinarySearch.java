package BinarySearch;

import java.util.Scanner;
import java.util.stream.IntStream;

public class BinarySearch {
    public void start() {
//        int[] intArray = IntStream.range(0, 24).map(e -> new Random().nextInt(100)).sorted().toArray();

        int[] intArray = IntStream.range(1, 26).toArray();

        IntStream.range(0, intArray.length).forEach(e -> {
            System.out.print(e % 3 == 0 ? intArray[e] + "\n" : intArray[e] + "\t");
        });

        int search = 15;

        System.out.print("\nUser Input: ");
        int userInput = new Scanner(System.in).nextInt();
        System.out.println("\nSEARCH: " + search);

        System.out.println(binarySearch(intArray, userInput, search));

    }

    public boolean binarySearch(int[] intArray, int userInput, int value) {
        return binarySearch(intArray, userInput, value, 0, intArray.length);
    }

    private boolean binarySearch(int[] intArray, int userInput, int value, int left, int right) {
        if (left > right) {
            return false;
        }

        if (userInput == value) {
            return true;
        }

        else if (userInput < value) {
            return binarySearch(intArray, userInput, value, userInput + 1, right);
        }

        else if (userInput > value) {
            return binarySearch(intArray, userInput, value, 0, userInput - 1);
        }

        return false;
    }

//    private boolean binarySearch(int value, int[] intArray) {
//        if (intArray.length != 1) {
//            int midPoint = intArray[intArray.length / 2];
//
//            if (midPoint == value) {
//                return true;
//            }
//
//            final int[] tempIntArray = intArray;
//
//            if (value > midPoint) {
//                intArray = IntStream.range(tempIntArray.length / 2, tempIntArray.length).map(e -> tempIntArray[e]).toArray();
//                return binarySearch(value,  intArray);
//            }
//
//            else {
//                intArray = IntStream.range(0, tempIntArray.length / 2).map(e -> tempIntArray[e]).toArray();
//                return binarySearch(value, intArray);
//            }
//        }
//
//        return false;
//    }
}
