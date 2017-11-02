import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.lang.reflect.Method;

public class Button {

    Rectangle button;

    Button(double x, double y, double width, double height, Image hover, Image noHover, Group root){
        button = new Rectangle(x, y, width, height);
        button.setFill(new ImagePattern(noHover));
        root.getChildren().add(button);

        button.setOnMouseEntered(event -> {
            button.setFill(new ImagePattern(hover));
        });
        button.setOnMouseExited(event -> {
            button.setFill(new ImagePattern(noHover));
        });
    }
}
