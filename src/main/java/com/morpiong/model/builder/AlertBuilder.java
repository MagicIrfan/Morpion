package com.morpiong.model.builder;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AlertBuilder implements DialogBuilder<ButtonType> {
    private Alert.AlertType type;
    private String title;
    private String headerText;
    private String contentText;
    private EventHandler<DialogEvent> handler;
    private final List<String> styleSheets;
    public AlertBuilder(){
        this.styleSheets = new ArrayList<>();
    }
    public AlertBuilder setType(Alert.AlertType type) {
        this.type = type;
        return this;
    }

    public AlertBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public AlertBuilder setHeaderText(String headerText) {
        this.headerText = headerText;
        return this;
    }

    public AlertBuilder setContentText(String contentText) {
        this.contentText = contentText;
        return this;
    }

    public AlertBuilder addStyleSheet(String styleSheets){
        this.styleSheets.add(styleSheets);
        return this;
    }

    public AlertBuilder setOnCloseRequest(EventHandler<DialogEvent> handler){
        this.handler = handler;
        return this;
    }

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
