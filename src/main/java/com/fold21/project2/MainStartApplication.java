package com.fold21.project2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainStartApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("Login-view.fxml"));
        Parent loginRoot = loginLoader.load();
        LoginController loginController = loginLoader.getController();

        FXMLLoader regLoader = new FXMLLoader(getClass().getResource("Reg-view.fxml"));
        Parent regRoot = regLoader.load();
        RegController regController = regLoader.getController();

        // Настраиваем контроллеры
        LoginController.setRegController(regController);
        RegController.setLoginController(loginController);

        Scene scene = new Scene(loginRoot);
        Scene scene1 = new Scene(regRoot);
        stage.setScene(scene);
        stage.setScene(scene1);
        //min width and height only for login page and reg page
//        stage.setMinWidth(1000);
//        stage.setMinHeight(600);
//        stage.setMaxWidth(1000);
//        stage.setMaxHeight(600);
        stage.setTitle("NEWS plus+");
        stage.show();



    }

    public static void main(String[] args) {
        launch();
    }
}
