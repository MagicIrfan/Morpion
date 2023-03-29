package com.morpiong.utils;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**

 Classe utilitaire pour changer la scène JavaFX.
 */
public class SceneChangerUtils {

    /**
     Change la scène JavaFX avec le panneau spécifié.
     @param pane le panneau qui contient la scène actuelle
     @param newPane le nouveau panneau qui remplacera le panneau actuel
     @throws IOException si une erreur survient lors de la lecture du panneau FXML
     */
    public static void changeScene(Pane pane, Pane newPane) throws IOException {
        pane.getChildren().clear(); // Supprime tous les enfants du panneau
        Stage currentStage = (Stage) pane.getScene().getWindow();
        currentStage.getScene().setRoot(newPane);
    }
}
