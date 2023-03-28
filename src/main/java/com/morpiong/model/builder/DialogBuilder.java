package com.morpiong.model.builder;

import javafx.scene.control.Dialog;

/**

 Interface représentant un constructeur de dialogues génériques.

 @param <T> le type de l'objet retourné par le dialogue construit.
 */
public interface DialogBuilder<T> {

    /**
     Construit et retourne un dialogue.
     @return le dialogue construit.
     */
    Dialog<T> build();
}
