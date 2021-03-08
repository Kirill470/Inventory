package sample.window;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.util.logging.LogManager;
import java.util.logging.Logger;


public class Notification {

    public Notification(String name, String info) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(name);
                alert.setHeaderText(null);
                alert.setContentText(info);
                alert.showAndWait();
            }
        });
    }

    public Notification(String name, String info, Alert.AlertType alert) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert nAlert = new Alert(alert);
                nAlert.setTitle(name);
                nAlert.setHeaderText(null);
                nAlert.setContentText(info);
                nAlert.showAndWait();
            }
        });

    }
}
