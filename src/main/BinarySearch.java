package main;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class BinarySearch {
    public void start() {

        int[] intArray = IntStream.range(0, 25).map(e -> new Random().nextInt(100)).toArray();

        IntStream.range(0, intArray.length).forEach(e -> {
            System.out.print(e % 3 == 0 ? intArray[e] + "\n" : intArray[e] + "\t");
        });


        int search = new Random().nextInt(100);

        System.out.println("SEARCH: " + search);
        System.out.println(binarySearch(search, intArray));

    }

    private boolean binarySearch(int value, int[] intArray) {
        if (intArray.length != 0) {
            int midPoint = intArray[intArray.length / 2];
            System.out.println("MIDPOINT: " + midPoint);

            if (midPoint == value) {
                return true;
            }

            if (midPoint > value) {
                intArray = IntStream.range(midPoint, intArray.length).toArray();
                return binarySearch(value,  intArray);
            }

            else if (midPoint < value) {
                intArray = IntStream.range(1, midPoint).toArray();
                return binarySearch(value, intArray);
            }

        }

        return false;
    }
}
