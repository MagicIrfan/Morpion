package com.morpiong;

import com.morpiong.controller.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenuController.class.getResource("/com/morpiong/mainmenu-view.fxml"));
        Parent root = fxmlLoader.load();
        root.getStylesheets().add("/com/morpiong/styles/button.css");
        root.getStylesheets().add("/com/morpiong/styles/mainMenu.css");
        Scene scene = new Scene(root);
        stage.setTitle("Morpion");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}