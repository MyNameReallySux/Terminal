package terminal.components;

import javafx.beans.NamedArg;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import java.io.IOException;

/**
 * Created by Chris Coppola on 2/5/2017.
 */
public abstract class TerminalScene extends Scene {
    static final Color DEFAULT_BACKGROUND = Color.TRANSPARENT;

    public TerminalScene(@NamedArg("root") Parent root) {
        super(root);
        setDefaults();
        init();
    }

    private void setDefaults() {
        setFill(DEFAULT_BACKGROUND);
    }

    protected Parent getRootFromFilename(String filename) throws IOException {
        return FXMLLoader.load(getClass().getResource(filename));
    }

    protected void onFocus() {
    }

    public abstract void init();
}
