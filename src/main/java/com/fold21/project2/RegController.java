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

public class RegController {


    private static LoginController loginController;
    private static Stage stage;

    public static void setStage(Stage stage) {
        RegController.stage = stage;
    }

    @FXML
    private Button btnSingUp;

    @FXML
    private Hyperlink linkToLogPage;

    @FXML
    private PasswordField passwordFieldReg;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private RadioButton rbMale;

    @FXML
    private TextField textLineName;

    @FXML
    private TextField textLineRegUsername;

    @FXML
    private TextField textLineSurname;




    @FXML
    void handleLinkClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) linkToLogPage.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("LogIn-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1000, 600); // задаем размер для нового окна
        stage.setScene(scene);
    }
    AlertsClass alertsClass = new AlertsClass();

    public static void setLoginController(LoginController loginController) {
        RegController.loginController = loginController;
    }

    @FXML
    public void initialize() {
        ToggleGroup toggleGroupGender = new ToggleGroup();
        rbMale.setToggleGroup(toggleGroupGender);
        rbFemale.setToggleGroup(toggleGroupGender);

        rbMale.setOnAction(event -> {
            rbFemale.setSelected(false);
        });

        rbMale.setOnAction(event -> {
            rbFemale.setSelected(false);
        });

        btnSingUp.setOnAction(event -> {
            String firstName = textLineName.getText().trim();
            String lastName = textLineSurname.getText().trim();
            String username = textLineRegUsername.getText().trim();
            String password = passwordFieldReg.getText().trim();


            RadioButton selectedGender = (RadioButton) toggleGroupGender.getSelectedToggle();

            if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || selectedGender == null) {
                alertsClass.alertError("Error",null,"Please fill in all the required fields and select a gender.");
            } else if (!firstName.matches("[a-zA-Zа-яА-Я]+") || !lastName.matches("[a-zA-Zа-яА-Я]+") || !username.matches("[a-zA-Zа-яА-Я0-9]+")) {
                alertsClass.alertError("Error",null,"Please enter only letters and digits in the name, surname, and username fields.");
            } else {
                // Сохраняем данные пользователя в файл
                try {
                    SaveAndReadDataInFile.saveUser(firstName, lastName, username, password, selectedGender.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Alert alert = alertsClass.alertConfirm("Registration","Registration successful. Do you want to log in?");
                ButtonType loginButtonType = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancelButtonType = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(loginButtonType, cancelButtonType);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == loginButtonType) {
                    // Go to login page
                    try {
                        handleLinkClick(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Stay on registration page
                    textLineRegUsername.setText("");
                    passwordFieldReg.setText("");
                    textLineName.setText("");
                    textLineSurname.setText("");
                }


            }

        });
    }
}
