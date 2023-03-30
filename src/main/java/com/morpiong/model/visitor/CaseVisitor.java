package com.morpiong.model.visitor;

import com.morpiong.model.Case;

/**
 * L'interface Visitor définit une méthode visit pour chaque type d'élément visitable.
 */
public interface CaseVisitor {

    /**
     * Visite l'élément de type Case.
     * @param caseElement l'élément de type Case à visiter.
     */
    void visit(Case caseElement);
}
