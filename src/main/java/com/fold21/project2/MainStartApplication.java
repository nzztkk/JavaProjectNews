package com.fold21.project2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainStartApplication extends Application {

    private void setupSceneSize(Stage stage, double minWidth, double minHeight, double maxWidth, double maxHeight) {
        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
        stage.setMaxWidth(maxWidth);
        stage.setMaxHeight(maxHeight);
    }


    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("Login-view.fxml"));
        Parent loginRoot = loginLoader.load();
        LoginController loginController = loginLoader.getController();

        FXMLLoader regLoader = new FXMLLoader(getClass().getResource("Reg-view.fxml"));
        Parent regRoot = regLoader.load();
        RegController regController = regLoader.getController();

        FXMLLoader newsLoader = new FXMLLoader(getClass().getResource("NewsPage-view.fxml"));
        Parent newsRoot = newsLoader.load();

        // Настраиваем контроллеры
        LoginController.setRegController(regController);
        RegController.setLoginController(loginController);


        Scene regScene = new Scene(regRoot, 1000, 600);
        stage.setScene(regScene);
        setupSceneSize(stage, 1050, 650, 1050, 650);
        stage.setTitle("NEWS plus+ - Registration");


        Scene loginScene = new Scene(loginRoot, 1000, 600);
        stage.setScene(loginScene);
        setupSceneSize(stage, 1050, 650, 1050, 650);
        stage.setTitle("NEWS plus+ - Login");
        stage.show();

        Stage newsStage = new Stage();
        Scene newsScene = new Scene(newsRoot, 1000, 800);
        newsStage.setScene(newsScene);
        newsStage.setTitle("News plus+ - News");
        newsStage.setMinWidth(1600);
        newsStage.setMinHeight(800);
        newsStage.setMaximized(true);

    }





    public static void main(String[] args) {
        launch();
    }
}
