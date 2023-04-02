package com.fold21.project2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;

public class NewsPageController {

    @FXML
    private Button ExitButton;

    @FXML
    private WebView WebView;

    @FXML
    private Hyperlink linkToEconomyNews;

    @FXML
    private Hyperlink linkToPoliticsNews;

    @FXML
    private Hyperlink linkToSportNews;

    @FXML
    private Hyperlink linkToTechNews;


    @FXML
    void handleLinkClick0(ActionEvent event) {
        //open new page with tech news
        WebView.getEngine().load("https://www.bbc.com/news/technology");

    }

    @FXML
    void handleLinkClick1(ActionEvent event) {
        WebView.getEngine().load("https://www.bbc.com/sport");
    }

    @FXML
    void handleLinkClick2(ActionEvent event) {
        WebView.getEngine().load("https://www.bbc.com/news/business");

    }

    @FXML
    void handleLinkClick3(ActionEvent event) {
        WebView.getEngine().load("https://www.bbc.com/news/business-45489065");

    }

    public void initData(String username) {
        WebView.getEngine().load("https://www.bbc.com/news");
    }

    @FXML
    void handleExitButton(ActionEvent event) throws IOException {

        // загрузить fxml-файл для страницы авторизации
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login-view.fxml"));
        Parent root = loader.load();

        // получить stage текущего окна и установить сцену для страницы авторизации
        Stage stage = (Stage) ExitButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void initialize() {
        ExitButton.setOnAction(event -> {
                    try {
                        handleExitButton(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
    }



}
