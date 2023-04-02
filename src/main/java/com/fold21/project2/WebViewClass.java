package com.fold21.project2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebViewClass extends Application {

    @Override
    public void start(Stage stage) {
        // Создаем компонент WebView
        WebView webView = new WebView();

        // Загружаем страницу Google
        webView.getEngine().load("https://www.google.com");

        // Создаем сцену и добавляем на нее WebView
        Scene scene = new Scene(webView);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
