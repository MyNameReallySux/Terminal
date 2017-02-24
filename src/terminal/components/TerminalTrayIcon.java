package terminal.components;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

/**
 * Created by Chris Coppola on 2/5/2017.
 */
public class TerminalTrayIcon extends TrayIcon {
    private static TerminalTrayIcon instance;
    private static boolean firstTime = true;

    private TerminalTrayIcon(Image image, String tooltip, PopupMenu popup) {
        super(image, tooltip, popup);
    }

    public static TerminalTrayIcon getInstance() {
        if (instance == null)
            createTrayIcon(TerminalApplication.getPrimaryStage()).ifPresent((icon) -> instance = icon);
        return instance;
    }

    private static Optional<TerminalTrayIcon> createTrayIcon(final TerminalStage stage) {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = null;
            try {
                URL url = new URL("http://www.digitalphotoartistry.com/rose1.jpg");
                image = ImageIO.read(url);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            stage.setOnCloseRequest((event) -> {
                stage.minimizeToSystemTray();
            });

            PopupMenu popup = createPopupMenu(stage);

            TerminalTrayIcon icon = new TerminalTrayIcon(image, "Terminal", popup);
            icon.addActionListener((event) -> stage.restoreFromSystemTray());

            try {
                tray.add(icon);
            } catch (AWTException ex) {
                ex.printStackTrace();
            }

            return Optional.of(icon);
        } else {
            return Optional.empty();
        }
    }

    public static PopupMenu createPopupMenu(TerminalStage stage) {
        PopupMenu popup = new PopupMenu("Menu");

        stage.setOnCloseRequest((event) -> stage.minimizeToSystemTray());

        MenuItem showItem = new MenuItem("Show");
        showItem.addActionListener((event) -> stage.restoreFromSystemTray());

        MenuItem closeItem = new MenuItem("Close");
        closeItem.addActionListener((event) -> TerminalApplication.exit());

        popup.add(showItem);
        popup.add(closeItem);

        return popup;
    }

    public void showAlert(String caption, String text) {
        if (firstTime) {
            getInstance().displayMessage(caption, text, TrayIcon.MessageType.INFO);
            firstTime = false;
        }
    }
}
