package function;

public class Button {
    public static void hovering(javafx.scene.control.Button button) {
        button.setOnMouseEntered(e -> {
            button.setId("button-hovered");
        });

        button.setOnMouseExited(e -> {
            button.setId("button-default");
        });
    }
}
