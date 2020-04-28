package main;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import start.DifficultyForm;

public class MainForm extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Scene scene;
    private BorderPane mainBorderPane;
    private Button startButton;
    private Button quitButton;
    private VBox vBox;
    private Label titleLabel;

    @Override
    public void start(Stage window) {
        window.setScene(scene(window));
        window.setTitle("Guessing Game");
        window.setResizable(false);
        window.getIcons().add(new Image("/icons/windowIcon.png"));

        window.setOnCloseRequest(e -> {
            e.consume();

            closeProgram(window);
        });

        window.show();
    }

    private void closeProgram(Stage window) {
        AlertBox alertBox = new AlertBox(window);

        BooleanProperty answer = alertBox.display("Exit Program", "Are you sure?", "Yes", "No");

        if (answer.getValue()) {
            window.close();
        }
    }

    public Label createTitle() {
        titleLabel = new Label();
        titleLabel.setText("Guessing Game");
        titleLabel.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 50));
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setPadding(new Insets(0, 0, 100, 0));
        VBox.setVgrow(titleLabel, Priority.ALWAYS);

        return titleLabel;
    }

    public Scene scene(Stage window) {
        mainBorderPane = new BorderPane();

        mainBorderPane.setCenter(createVBox(window));

        scene = new Scene(mainBorderPane, 800, 600);
        scene.getStylesheets().add("Design.css");

        return scene;
    }

    private VBox createVBox(Stage window) {
        vBox = new VBox();

        startButton = new Button("Start");
        startButton.setPrefWidth(200);
        startButton.setPrefHeight(50);
        startButton.setCursor(Cursor.HAND);

        startButton.setOnAction(e -> {
            DifficultyForm difficultyForm = new DifficultyForm();

            window.setScene(difficultyForm.scene(window));
        });

        function.Button.hovering(startButton);

        quitButton = new Button("Quit");
        quitButton.setPrefWidth(200);
        quitButton.setPrefHeight(50);
        quitButton.setCursor(Cursor.HAND);

        quitButton.setOnAction(e -> {
            closeProgram(window);
        });

        function.Button.hovering(quitButton);

        vBox.getChildren().addAll(createTitle(), startButton, quitButton);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(0, 0, 0, 0));

        return vBox;
    }
}
