package com.morpiong.model.builder;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**

 Cette classe est un constructeur de la boîte de dialogue Alert de JavaFX. Elle implémente l'interface DialogBuilder

 et permet de construire une boîte de dialogue Alert en utilisant la méthode build().
 */
public class AlertBuilder implements DialogBuilder<ButtonType> {

    private Alert.AlertType type;
    private String title;
    private String headerText;
    private String contentText;
    private EventHandler<DialogEvent> handler;

    /**
     Cette méthode permet de définir le type d'alerte.
     @param type le type d'alerte
     @return l'instance de AlertBuilder
     */
    public AlertBuilder setType(Alert.AlertType type) {
        this.type = type;
        return this;
    }

    /**
     Cette méthode permet de définir le titre de l'alerte.
     @param title le titre de l'alerte
     @return l'instance de AlertBuilder
     */
    public AlertBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     Cette méthode permet de définir le texte d'en-tête de l'alerte.
     @param headerText le texte d'en-tête de l'alerte
     @return l'instance de AlertBuilder
     */
    public AlertBuilder setHeaderText(String headerText) {
        this.headerText = headerText;
        return this;
    }

    /**
     Cette méthode permet de définir le texte principal de l'alerte.
     @param contentText le texte principal de l'alerte
     @return l'instance de AlertBuilder
     */
    public AlertBuilder setContentText(String contentText) {
        this.contentText = contentText;
        return this;
    }

    /**
     Cette méthode permet de définir l'événement de fermeture de l'alerte.
     @param handler l'événement de fermeture de l'alerte
     @return l'instance de AlertBuilder
     */
    public AlertBuilder setOnCloseRequest(EventHandler<DialogEvent> handler){
        this.handler = handler;
        return this;
    }

    /**
     Cette méthode construit une instance de la boîte de dialogue Alert en utilisant les paramètres définis dans
     les méthodes précédentes.
     @return l'instance de Alert construite
     */
    @Override
    public Alert build() {
        Alert alert = new Alert(this.type);
        alert.setTitle(this.title);
        alert.setHeaderText(this.headerText);
        alert.setContentText(this.contentText);
        alert.setOnCloseRequest(this.handler);
        return alert;
    }
}
