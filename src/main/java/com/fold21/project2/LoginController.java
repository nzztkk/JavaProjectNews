package com.fold21.project2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.Optional;


public class LoginController {
    private static RegController regController;

    @FXML
    private CheckBox checkBoxRememberUser;

    @FXML
    private Hyperlink linkToRegPage;

    @FXML
    private Hyperlink linkToResetPassword;

    @FXML
    private Button btnLogIn;


    @FXML
    private TextField textLineLogUsername;
    //passwpord
    @FXML
    private PasswordField textLinePasswordLog;


    SaveAndReadDataInFile saveAndReadDataInFile = new SaveAndReadDataInFile();

    @FXML
    void handleLinkClick1(ActionEvent event) throws IOException {
        Stage stage = (Stage) linkToRegPage.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Reg-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1000, 600); // задаем размер для нового окна
        stage.setScene(scene);
    }



    @FXML
    void handleLinkClick2(ActionEvent event) throws IOException {

        Stage stage = (Stage) linkToResetPassword.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Reset-password-viev.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    public static void setRegController(RegController regController) {
        LoginController.regController = regController;
    }

    AlertsClass alertsClass = new AlertsClass();
    private void handleLogIn() {
        String username = textLineLogUsername.getText().trim();
        String password = textLinePasswordLog.getText().trim();

        if (!username.equals("") && !password.equals("")) {
            if (saveAndReadDataInFile.loginUser(username, password)) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("NewsPage-view.fxml"));
                    Parent root = loader.load();
                    NewsPageController newsController = loader.getController();
                    newsController.initData(username); // передача имени пользователя в контроллер новостей
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) textLineLogUsername.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                    alertsClass.alertInformation("Success", null, "You are logged in!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                alertsClass.alertError("Error", null, "Login or password is incorrect!");
            }
        } else {
            alertsClass.alertError("Error", null, "Login and password fields cannot be empty!");
        }
    }







    private void handleCheckBox() {
        if (checkBoxRememberUser.isSelected()) {
            String username = textLineLogUsername.getText().trim();
            String password = textLinePasswordLog.getText().trim();
            CheckBoxCheckerClass.saveUserData(username, password);
        } else {
            CheckBoxCheckerClass.deleteUserData();
        }
    }

    @FXML
    public void initialize() {
        btnLogIn.setOnAction(event -> handleLogIn());
        checkBoxRememberUser.setOnAction(event -> handleCheckBox());
    }










}
