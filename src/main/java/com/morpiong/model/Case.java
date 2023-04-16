package com.morpiong.model;
import com.morpiong.model.visitor.*;
import javafx.beans.property.*;

/**

 La classe Case représente une case d'un plateau de jeu.
 */
public class Case {

    private final BooleanProperty selectionned;
    private final StringProperty urlShapeImage;
    private Symbol symbol;
    private final int XCoord;
    private final int YCoord;

    /**
     Constructeur de la classe Case.
     @param XCoord coordonnée X de la case
     @param YCoord coordonnée Y de la case
     */
    public Case(int XCoord, int YCoord){
        this.XCoord = XCoord;
        this.YCoord = YCoord;
        this.selectionned = new SimpleBooleanProperty(false);
        this.urlShapeImage = new SimpleStringProperty();
        this.symbol = Symbol.NONE;
    }

    /**
     Retourne la propriété de l'URL de l'image de la forme.
     @return la propriété de l'URL de l'image de la forme.
     */
    public StringProperty urlShapeImageProperty(){
        return this.urlShapeImage;
    }

    /**
     Retourne l'URL de l'image de la forme.
     @return l'URL de l'image de la forme.
     */
    public String getUrlShape(){
        return this.urlShapeImage.get();
    }

    /**
     Retourne la propriété de sélection de la case.
     @return la propriété de sélection de la case.
     */
    public BooleanProperty selectionnedProperty(){
        return this.selectionned;
    }

    /**
     Retourne vrai si la case est sélectionnée.
     @return vrai si la case est sélectionnée.
     */
    public boolean isSelectionned(){
        return this.selectionned.get();
    }

    /**
     Accepte le visiteur donné en paramètre pour la visite de la case.
     @param visitor le visiteur à accepter.
     */
    public void accept(CaseVisitor visitor){
        visitor.visit(this);
    }

    /**
     * Change le symbole de la case
     * @param symbol le symbole de la case à changer
     */
    public void setSymbol(Symbol symbol){
        this.symbol = symbol;
    }

    /**
     * Retourne le symbole de la case
     * @return le symbole de la case
     */
    public Symbol getSymbol(){
        return this.symbol;
    }

    /**
     Retourne la coordonnée X de la case
     @return la coordonnée X de la case
     */
    public int getXCoord(){
        return this.XCoord;
    }

    /**
     Retourne la coordonnée Y de la case
     @return la coordonnée Y de la case
     */
    public int getYCoord(){
        return this.YCoord;
    }

    /**
     * Permet de savoir si la case est vide
     * @return un booléen permettant de savoir si la case est vide
     */
    public boolean isEmpty(){
        return this.symbol == Symbol.NONE;
    }
}
