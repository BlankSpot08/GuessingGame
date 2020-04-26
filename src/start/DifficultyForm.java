package start;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DifficultyForm {
    private Scene scene;
    private BorderPane borderPane;
    private Label difficultyLabel;
    private Button easyButton;
    private Button normalButton;
    private Button hardButton;
    private Button backButton;
    private VBox vBox;

    public Scene scene(Stage window) {
        scene = new Scene(createBorderPane(window), 800, 600);
        scene.getStylesheets().add("Choo.css");

        return scene;
    }

    private VBox createVBox(Stage window) {
        vBox = new VBox();
        vBox.setMinSize(300, 250);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        difficultyLabel = new Label("Difficulty: ");

        easyButton = new Button("Easy");
        easyButton.setPrefWidth(200);
        easyButton.setPrefHeight(50);
        easyButton.setCursor(Cursor.HAND);

        easyButton.setOnAction(e -> {
            StartForm startForm = new StartForm("Easy");

            window.setScene(startForm.scene());
        });

        normalButton = new Button("Normal");
        normalButton.setPrefWidth(200);
        normalButton.setPrefHeight(50);
        normalButton.setCursor(Cursor.HAND);

        hardButton = new Button("Hard");
        hardButton.setPrefWidth(200);
        hardButton.setPrefHeight(50);
        hardButton.setCursor(Cursor.HAND);

        backButton = new Button("Back");
        backButton.setPrefWidth(200);
        backButton.setPrefHeight(50);
        backButton.setCursor(Cursor.HAND);

        vBox.getChildren().addAll(difficultyLabel, easyButton, normalButton, hardButton, backButton);

        return vBox;
    }

    private BorderPane createBorderPane(Stage window) {
        borderPane = new BorderPane();

        borderPane.setCenter(createVBox(window));

        return borderPane;
    }
}
