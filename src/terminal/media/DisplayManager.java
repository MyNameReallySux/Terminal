package terminal.media;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 * Created by Chris Coppola on 2/5/2017.
 */
public class DisplayManager {
    static Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

    public static Rectangle2D getScreenBounds() {
        return bounds;
    }
}
