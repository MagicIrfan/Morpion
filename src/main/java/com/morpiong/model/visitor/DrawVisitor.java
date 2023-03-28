package com.morpiong.model.visitor;

import com.morpiong.model.Case;
import com.morpiong.view.CasePane;

import java.util.Objects;

/**
 * Un visiteur pour dessiner une forme sur une case.
 */
public class DrawVisitor implements Visitor {

    private final String imageUrl;

    /**
     * Constructeur de la classe DrawVisitor.
     * @param imageUrl l'URL de l'image représentant la forme à dessiner.
     */
    public DrawVisitor(String imageUrl){
        this.imageUrl = imageUrl;
    }

    /**
     * Visite la case donnée et dessine la forme sur celle-ci.
     * @param caseElement la case à visiter.
     */
    public void visit(Case caseElement){
        caseElement.urlShapeImageProperty().set(imageUrl);
        ((CasePane) caseElement.getPane()).setImage(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(caseElement.getUrlShape())));
    }
}
