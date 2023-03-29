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
        Scene newScene = new Scene(root);
        Stage currentStage = (Stage) pane.getScene().getWindow();
        //Scene currentStage = primaryStage.getScene();
        if (currentStage.getScene() != null) {
            currentStage.getScene().getRoot().setDisable(true); // disable the old scene
        }
        currentStage.setScene(newScene);
        currentStage.show();
        if (currentStage.getScene() != null) {
            currentStage.getScene().getRoot().setDisable(false); // enable the new scene
        }
    }
}
