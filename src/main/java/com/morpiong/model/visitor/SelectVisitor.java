package com.morpiong.model.visitor;

import com.morpiong.model.Case;

/**

 Implémentation de l'interface Visitor permettant de sélectionner une case.
 */
public class SelectVisitor implements Visitor {

    /**
     Visite la case donnée en paramètre et la sélectionne en mettant sa propriété de sélection à vrai.
     @param caseElement la case à sélectionner.
     */
    public void visit(Case caseElement){
        caseElement.selectionnedProperty().set(true);
    }
}
