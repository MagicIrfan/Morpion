package com.morpiong.model.Player;

import com.morpiong.model.Plate;
import com.morpiong.model.Symbol;
import com.morpiong.utils.ImageUtils;
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

    protected final Symbol symbol;

    /**
     * Constructeur de la classe PlayableStrategy.
     */
    protected PlayableStrategy(Symbol symbol) {
        this.symbol = symbol;
        this.urlShapeImage = new SimpleStringProperty();
        if(this.symbol == Symbol.X){
            this.urlShapeImage.set(ImageUtils.X);
        }
        else if (this.symbol == Symbol.O){
            this.urlShapeImage.set(ImageUtils.O);
        }

    }

    /**
     * Méthode abstraite permettant de choisir une case dans le tableau de cases donné.
     * @param plate le plateau du jeu
     */
    public abstract void chooseCase(Plate plate);

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

    public Symbol getSymbol(){
        return this.symbol;
    }
}
