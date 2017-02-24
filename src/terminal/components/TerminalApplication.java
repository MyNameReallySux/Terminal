package terminal.components;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import terminal.config.CommandMapReader;


public class TerminalApplication extends Application {

    private static TerminalStage primaryStage;

    public static void exit() {
        Platform.exit();
        System.exit(0);
    }

    public static TerminalStage getPrimaryStage() {
        return primaryStage;
    }

    private static void setPrimaryStage(TerminalStage stage) {
        primaryStage = stage;
    }

    public static void main(String[] args) {
        TerminalApplication.launch(args);
    }

    @Override
    public void start(Stage basicStage) {
        TerminalStage stage = new TerminalStage("search");
        Platform.setImplicitExit(false);
        setPrimaryStage(stage);
        stage.createTrayIcon();
        stage.show();

        CommandMapReader cmr = new CommandMapReader();
    }
}
