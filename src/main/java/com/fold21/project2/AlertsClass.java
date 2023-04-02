package com.fold21.project2;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;


public class AlertsClass {


    public Alert alertError(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
        return alert;
    }

    public Alert alertInformation(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
        return alert;

    }


    public static ButtonType YesButton() {
        return new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
    }
    public static ButtonType CancelButton() {
        return new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
    }



    public Alert alertConfirm(String title, String content) {
        // Create the custom dialog.
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        return alert;
    }

    public Alert alertConfirm1(String title, String content, Runnable onCloseAction) {
        // Create the custom dialog.
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
        if (onCloseAction != null) {
            onCloseAction.run();
        }
        return alert;
    }


}
