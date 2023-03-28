package com.morpiong.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.InputStream;

/**

 Un pane personnalisé JavaFX qui représente l'affichage d'une case de morpion.

 L'image peut être définie à l'aide de la méthode {@link #setImage(InputStream)}.
 */
public class CasePane extends Pane {

    private final ImageView image;

    /**
     Crée un nouveau {@code CasePane} avec une {@code ImageView} vide.
     La taille par défaut de l'image est de 200x200 pixels et une bordure noire est ajoutée au pane.
     */
    public CasePane(){
        this.image = new ImageView();
        this.image.setFitWidth(200);
        this.image.setFitHeight(200);
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
        this.getChildren().add(this.image);
    }

    /**
     Définit l'image de ce {@code CasePane} à celle lue depuis l'InputStream donné.
     @param stream l'InputStream à partir duquel lire l'image.
     @throws NullPointerException si l'{@code InputStream} est nul.
     */
    public void setImage(InputStream stream){
        this.image.setImage(new Image(stream));
    }
}
