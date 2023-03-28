package com.morpiong.model.Player;

import com.morpiong.model.Case;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe abstraite représentant une stratégie de jeu jouable.
 * Une stratégie jouable est caractérisée par une image représentant sa forme.
 */
public abstract class PlayableStrategy {

    /**
     * Propriété de l'URL de l'image représentant la future case cliquée.
     */
    protected final StringProperty urlShapeImage;

    /**
     * Constructeur de la classe PlayableStrategy.
     * @param urlShapeImage l'URL de l'image représentant la future case cliquée.
     */
    protected PlayableStrategy(String urlShapeImage) {
        this.urlShapeImage = new SimpleStringProperty(urlShapeImage);
    }

    /**
     * Méthode abstraite permettant de choisir une case dans le tableau de cases donné.
     * @param cases le tableau de cases.
     */
    abstract public void chooseCase(Case[][] cases);

    /**
     * Retourne la propriété de l'URL de l'image représentant la future case cliquée
     *.
     * @return la propriété de l'URL de l'image représentant la future case cliquée.
     */
    public StringProperty urlShapeImageProperty(){
        return this.urlShapeImage;
    }

    /**
     * Retourne l'URL de l'image représentant la future case cliquée.
     * @return l'URL de l'image représentant la future case cliquée.
     */
    public String getUrlShape(){
        return this.urlShapeImage.get();
    }
}
