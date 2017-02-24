package terminal.scenes;

import javafx.beans.NamedArg;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import terminal.components.TerminalApplication;
import terminal.components.TerminalScene;
import terminal.media.AnimationManager;


/**
 * Created by Chris Coppola on 2/5/2017.
 */
public class SearchScene extends TerminalScene {
    final String LAYOUT_FILENAME = "views/search.fxml";

    public SearchScene(@NamedArg("root") Parent root) {
        super(root);
    }

    @Override
    public void init() {
        setKeyHandlers();
    }

    public void onFocus() {
        Node searchBox = getRoot().lookup("#SearchBox");
        System.out.println("Focusing");
        searchBox.requestFocus();
    }

    public void setKeyHandlers() {
        addEventHandler(KeyEvent.KEY_PRESSED, key -> {
            if (key.isControlDown()) {
                switch (key.getCode()) {
                    case Q: {
                        System.out.println("Click on Ctrl + Q");
                        AnimationManager.fadeSceneOut(this, 400, (event) -> TerminalApplication.exit());
                    }
                    break;
                }
            } else {
                switch (key.getCode()) {
                    case ESCAPE: {
                        AnimationManager.fadeSceneOut(this, 400, (event) -> TerminalApplication.getPrimaryStage().minimizeToSystemTray());
                    }
                    break;
                }
            }
        });
    }
}
