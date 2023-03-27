package com.morpiong.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChangerUtils {

    public static void changeScene(Pane pane, String url) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneChangerUtils.class.getResource(url));
        Parent root = loader.load();
        Scene mainMenuScene = new Scene(root);
        Stage primaryStage = (Stage) pane.getScene().getWindow();
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }
}
