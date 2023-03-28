package com.morpiong.model.Factory;

import com.morpiong.model.Case;
import com.morpiong.view.CasePane;

/**
 * La classe CaseFactory permet de créer une nouvelle instance de la classe Case.
 *
 * Elle contient une méthode statique "create" qui crée et renvoie une nouvelle instance de la classe Case.
 */
public class CaseFactory {

    /**
     * Crée une nouvelle instance de la classe Case.
     *
     * @return la nouvelle instance de la classe Case
     */
    public static Case create(){
        return new Case(new CasePane());
    }
}
