package com.morpiong.model.visitor;

import com.morpiong.model.Case;
import com.morpiong.view.CasePane;
import javafx.scene.layout.Pane;

import java.util.Objects;

/**
 * Un visiteur pour dessiner une forme sur une case.
 */
public class DrawCaseVisitor implements CaseVisitor {

    private final String imageUrl;
    private final Pane casePane;

    /**
     * Constructeur de la classe DrawVisitor.
     * @param imageUrl l'URL de l'image représentant la forme à dessiner.
     * @param casePane la représentation graphique de la case
     */
    public DrawCaseVisitor(String imageUrl,Pane casePane){
        this.imageUrl = imageUrl;
        this.casePane = casePane;
    }

    /**
     * Visite la case donnée et dessine la forme sur celle-ci.
     * @param caseElement la case à visiter.
     */
    public void visit(Case caseElement){
        caseElement.urlShapeImageProperty().set(imageUrl);
        ((CasePane) casePane).setImage(Objects.requireNonNull(getClass().getResourceAsStream(caseElement.getUrlShape())));
    }
}
