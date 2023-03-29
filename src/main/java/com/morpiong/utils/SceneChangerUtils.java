package com.morpiong.utils;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Classe utilitaire pour changer la scène JavaFX.
 */
public class SceneChangerUtils {

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * Change la scène JavaFX avec le panneau spécifié de manière asynchrone.
     *
     * @param pane    le panneau qui contient la scène actuelle
     * @param newPane le nouveau panneau qui remplacera le panneau actuel
     * @throws IOException si une erreur survient lors de la lecture du panneau FXML
     */
    public static void changeScene(Pane pane, Pane newPane) throws IOException {
        pane.getChildren().clear(); // Supprime tous les enfants du panneau
        Stage currentStage = (Stage) pane.getScene().getWindow();
        executor.submit(() -> {
            Platform.runLater(() -> {
                currentStage.getScene().setRoot(newPane);
            });
        });
    }

    /**
     * Arrête proprement le thread de changement de scène.
     */
    public static void shutdown() {
        executor.shutdown();
    }
}
