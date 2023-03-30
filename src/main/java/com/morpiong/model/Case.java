package com.morpiong.model;
import com.morpiong.model.visitor.*;
import javafx.beans.property.*;
import javafx.scene.layout.Pane;

/**

 La classe Case représente une case d'un plateau de jeu.
 */
public class Case {

    private final Pane casePane;
    private final BooleanProperty selectionned;
    private final StringProperty urlShapeImage;
    private boolean isPair;

    /**
     Constructeur de la classe Case.
     @param pane le Pane représentant la case.
     */
    public Case(Pane pane){
        this.casePane = pane;
        this.selectionned = new SimpleBooleanProperty(false);
        this.urlShapeImage = new SimpleStringProperty();
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
     Retourne le Pane représentant la case.
     @return le Pane représentant la case.
     */
    public Pane getPane(){
        return this.casePane;
    }

    /**
     Accepte le visiteur donné en paramètre pour la visite de la case.
     @param visitor le visiteur à accepter.
     */
    public void accept(CaseVisitor visitor){
        visitor.visit(this);
    }

    /**
     Définit si la case est paire ou impaire.
     @param isPair vrai si la case est paire, faux sinon.
     */
    public void setPair(boolean isPair){
        this.isPair = isPair;
    }

    /**
     Retourne vrai si la case est paire.
     @return vrai si la case est paire.
     */
    public boolean isPair(){
        return this.isPair;
    }
}
