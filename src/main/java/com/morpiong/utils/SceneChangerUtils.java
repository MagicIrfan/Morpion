package com.morpiong.utils;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**

 Cette classe utilitaire fournit des méthodes pour changer de scène dans une application JavaFX.

 Elle utilise un exécuteur de thread unique pour garantir que les changements de scène se produisent dans le thread de l'application JavaFX.
 */
public class SceneChangerUtils {

    private static SceneChangerUtils instance = null;
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     Constructeur privé pour empêcher l'instanciation directe de la classe.
     */
    private SceneChangerUtils() {
    }

    /**
     Retourne l'instance singleton de la classe SceneChangerUtils.
     @return l'instance singleton de la classe SceneChangerUtils.
     */
    public static synchronized SceneChangerUtils getInstance() {
        if (instance == null) {
            instance = new SceneChangerUtils();
        }
        return instance;
    }

    /**
     Change de scène en définissant un nouveau nœud racine pour la scène de la fenêtre.
     Tous les enfants du nœud d'origine sont supprimés avant de définir le nouveau nœud racine.
     @param node le nœud dont la scène doit être changée.
     @param newParent le nouveau nœud parent à définir comme racine de la scène.
     @throws IOException si une erreur se produit lors du chargement de la nouvelle scène.
     */
    public void changeScene(Node node, Parent newParent) throws IOException {
        Stage currentStage = (Stage) node.getScene().getWindow();
        executor.submit(() -> {
            Platform.runLater(() -> {
                // Supprime tous les enfants du nœud parent avant de définir le nouveau nœud racine
                clearParentChildren(node.getParent());
                currentStage.getScene().setRoot(newParent);
            });
        });
    }

    private void clearParentChildren(Parent parent) {
        if (parent instanceof Pane) {
            ((Pane) parent).getChildren().clear();
        }
        else if (parent instanceof Group) {
            ((Group) parent).getChildren().clear();
        }
    }

    /**
     Arrête le service d'exécuteur utilisé par cette classe.
     Cela devrait être appelé lorsque l'application se ferme pour éviter les fuites de ressources.
     */
    public void shutdown() {
        executor.shutdown();
    }
}