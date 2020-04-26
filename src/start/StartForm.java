package start;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Random;
import java.util.stream.IntStream;

public class StartForm {
    public StartForm(String difficulty) {

        easy = false;
        normal = false;
        hard = false;

        switch (difficulty) {
            case "Easy":
                easy = true;
                boxHeight = 40;
                boxWidth = 82.5f;
                boxHorizontal = 10;
                boxVertical = 10;
                toBeSearched = new Random().nextInt(100);
                intArray = IntStream.range(0, 101).toArray();
            case "Normal":
                normal = true;
                boxes = 200;
            case "Hard":
                hard = true;
                boxes = 300;
        }

        i = 1;

        System.out.println("TO BE SEARCHED: " + toBeSearched);
    }

    private int[] intArray;
    private boolean easy;
    private boolean normal;
    private boolean hard;
    private int boxes;
    private int boxHorizontal;
    private int boxVertical;
    private int i;

    private int toBeSearched;

    private float boxWidth;
    private float boxHeight;

    private Scene scene;
    private BorderPane borderPane;
    private HBox hBox;
    private VBox vBox;

    public Scene scene() {
        scene = new Scene(createBorderPane(), 800, 600);
        scene.getStylesheets().add("Choo.css");

        return scene;
    }

    private HBox createHBoxes() {
        hBox = new HBox();
        hBox.setPadding(new Insets(0.5, 5, 0, 5));
        hBox.setSpacing(1);

        for (int j = 0; j < boxHorizontal; j++, i++) {
            Button button = new Button(String.valueOf(intArray[i]));
            button.setPrefWidth(boxWidth);
            button.setPrefHeight(boxHeight);
            button.setCursor(Cursor.HAND);

            button.setOnAction(e -> {
                if (button.getText().equals(String.valueOf(toBeSearched))) {
                    System.out.println("Correct");
                }

                else {
//                    binarySearch(intArray, toBeSearched);
                }
            });

            hBox.getChildren().add(button);
        }

        return hBox;
    }

    private VBox createBoxes() {
        vBox = new VBox();
        vBox.setPadding(new Insets(5, 5, 0, 5));
        vBox.setSpacing(1);

        for (int i = 0; i < boxVertical; i++) {
            vBox.getChildren().add(createHBoxes());
        }

        return vBox;
    }

    private BorderPane createBorderPane() {
        borderPane = new BorderPane();

        borderPane.setTop(createBoxes());

        return borderPane;
    }

//    public boolean binarySearch(int[] intArray, int value) {
//        return binarySearch(intArray, value, 0, intArray.length);
//    }
//
//    private boolean binarySearch(int[] intArray, int value, int left, int right) {
//    }
//
//    public void deactiveButtons(int start, int end) {
//        for (int i = start; i < end; i++) {
//
//        }
//    }
}
