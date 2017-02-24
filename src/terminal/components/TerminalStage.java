package terminal.components;

import com.sun.javafx.application.PlatformImpl;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import terminal.media.AnimationManager;
import terminal.media.DisplayManager;
import terminal.scenes.SearchScene;

import java.awt.*;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by Chris Coppola on 2/5/2017.
 */
public class TerminalStage extends Stage {
    static final int STARTING_OPACITY = 1;
    static TerminalTrayIcon appIcon;

    public TerminalStage(String startingScene) {
        super();
        init();
        changeScene(startingScene);
    }

    public void init() {
        initStyle(StageStyle.TRANSPARENT);
        setTitle("Terminal v0.1.0");
        setSizeFromRectangle(DisplayManager.getScreenBounds());

    }

    public void afterSceneChange(TerminalScene scene) {
        setOnFocusedHandlers(scene);
        setSceneOpacity(STARTING_OPACITY);
        setScene(scene);
    }

    public void changeScene(String name) {
        name = name.toLowerCase();
        try {
            Optional<Parent> root = Optional.empty();
            switch (name) {
                case "search": {
                    root = Optional.of(FXMLLoader.load(getClass().getResource("/views/search.fxml")));
                }
                break;
            }
            root.ifPresent((parent) -> {
                TerminalScene scene = new SearchScene(parent);
                setScene(scene);
                afterSceneChange(scene);
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void createTrayIcon() {
        appIcon = TerminalTrayIcon.getInstance();
    }

    public void minimizeToSystemTray() {
        Platform.runLater(() -> {
            if (SystemTray.isSupported()) {
                AnimationManager.fadeSceneOut(this, 400, (actionEvent) -> {
                    hide();
                });
            } else {
                PlatformImpl.tkExit();
                Platform.exit();
            }
            appIcon.showAlert("Terminal minimized!", "Terminal is now minimized! Right click to bring the terminal back up!");
        });
    }

    public void restoreFromSystemTray() {
        Platform.runLater(() -> {
            show();
            AnimationManager.fadeSceneIn(this, 400, (actionEvent) -> onRestoreFromSystemTray());
        });
    }

    private void onRestoreFromSystemTray() {

    }

    public void setSizeFromRectangle(Rectangle2D rect) {
        setX(rect.getMinX());
        setY(rect.getMinY());
        setWidth(rect.getWidth());
        setHeight(rect.getHeight());
    }

    public void setOnFocusedHandlers(TerminalScene scene) {
        focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                AnimationManager.fadeSceneIn(scene, 400, (event) -> scene.onFocus());
            }
        });
    }

    public void setSceneOpacity(double opacity) {
        getScene().getWindow().setOpacity(opacity);
    }

}
