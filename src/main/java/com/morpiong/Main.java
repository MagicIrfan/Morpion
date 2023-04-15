package com.morpiong;

import com.morpiong.controller.MainMenuController;
import com.morpiong.utils.ImageUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/morpiong/mainmenu-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Morpion");
        stage.setScene(scene);
        stage.setWidth(600);
        stage.setHeight(600);
        stage.show();
        stage.setResizable(false);
    }
    public static void main(String[] args) {
        launch();
    }
}