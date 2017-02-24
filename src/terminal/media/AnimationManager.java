package terminal.media;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.WritableValue;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.function.Consumer;

/**
 * Created by Chris Coppola on 2/5/2017.
 */
public class AnimationManager {
    public static void animateProperty(WritableValue<Number> property, Integer endValue, double duration, Consumer<ActionEvent> onFinished) {
        Timeline timeline = new Timeline();
        KeyFrame frame = new KeyFrame(Duration.millis(duration),
                new KeyValue(property, endValue));
        timeline.getKeyFrames().add(frame);
        timeline.setOnFinished(onFinished::accept);
        timeline.play();
    }

    public static void fadeSceneOut(Stage stage, double duration, Consumer<ActionEvent> onFinished) {
        fadeSceneOut(stage.getScene(), duration, onFinished);
    }

    public static void fadeSceneOut(Scene scene, double duration, Consumer<ActionEvent> onFinished) {
        animateProperty(scene.getWindow().opacityProperty(), 0, duration, onFinished);
    }

    public static void fadeSceneIn(Stage stage, double duration, Consumer<ActionEvent> onFinished) {
        fadeSceneIn(stage.getScene(), duration, onFinished);
    }

    public static void fadeSceneIn(Scene scene, double duration, Consumer<ActionEvent> onFinished) {
        animateProperty(scene.getWindow().opacityProperty(), 1, duration, onFinished);
    }
}
