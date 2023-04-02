package com.fold21.project2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ResetPasswordController {

    @FXML
    private Button btnResetPassword;
    @FXML
    private Hyperlink linkToLoginPage;
    @FXML
    private PasswordField textLineNewPassword;
    @FXML
    private TextField textLineResetPassUsername;


    @FXML
    public void handleLinkClick(ActionEvent event) throws IOException {
        // переход на страницу логина
        Stage stage = (Stage) linkToLoginPage.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("LogIn-view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    SaveAndReadDataInFile saveAndReadDataInFile = new SaveAndReadDataInFile();
    AlertsClass alertsClass = new AlertsClass();
    @FXML
    void handleResetPasswordButtonClick(ActionEvent event) {
        String username = textLineResetPassUsername.getText();
        String newPassword = textLineNewPassword.getText();
        // Проверяем, существует ли пользователь с введенным именем пользователя
        if (saveAndReadDataInFile.isUserExist(username)) {
            // Если пользователь существует, обновляем пароль
            // вызываем метод updatePassword в вашем классе, который вы не предоставили в коде выше.
            saveAndReadDataInFile.updatePassword(username, newPassword);
            alertsClass.alertInformation("Password Reset", "Your password has been reset successfully.", "Your new password is: " + newPassword);
            Alert alert = alertsClass.alertConfirm("Password Reset",  "Do you want to go to the login page?");
            ButtonType loginButtonType = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButtonType = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(loginButtonType, cancelButtonType);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == loginButtonType) {
                // переход на страницу авторизации
                try {
                    handleLinkClick(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Если пользователь не существует, выводим сообщение об ошибке
            alertsClass.alertError("Password Reset", "Reset password failed.", "User with username " + username + " does not exist.");
        }
    }


    public void initialize() {
        btnResetPassword.setOnAction(this::handleResetPasswordButtonClick);

        }
    }

