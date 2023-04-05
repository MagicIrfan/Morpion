package com.morpiong.model.Factory;

import com.morpiong.model.Case;
import com.morpiong.view.CasePane;

/**
 * La classe CaseFactory permet de créer une nouvelle instance de la classe Case.
 * Elle contient une méthode statique "create" qui crée et renvoie une nouvelle instance de la classe Case.
 */
public class CaseFactory{

    /**
     * Crée une nouvelle instance de la classe Case.
     * @param XCoord la coordonnée X de la case
     * @param YCoord la coordonnée Y de la case
     * @return la nouvelle instance de la classe Case
     */
    public static Case create(int XCoord, int YCoord)
    {
        return new Case(XCoord,YCoord);
    }
}
