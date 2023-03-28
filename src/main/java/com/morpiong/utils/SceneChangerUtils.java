package com.morpiong.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 Classe utilitaire pour changer la scène JavaFX.
 */
public class SceneChangerUtils {

    /**
     Change la scène JavaFX avec le fichier FXML spécifié.
     @param pane le panneau qui contient la scène actuelle
     @param url l'URL du fichier FXML de la nouvelle scène
     @throws IOException si une erreur survient lors de la lecture du fichier FXML
     */
    public static void changeScene(Pane pane, String url) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneChangerUtils.class.getResource(url));
        Parent root = loader.load();
        Scene mainMenuScene = new Scene(root);
        Stage primaryStage = (Stage) pane.getScene().getWindow();
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }
}
