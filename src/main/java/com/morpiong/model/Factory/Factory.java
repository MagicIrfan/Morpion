package com.morpiong.model.Factory;

/**
 * L'interface Factory<T> définit une méthode qui renvoie une instance de type T.
 * Cette interface peut être utilisée pour créer des objets de manière générique,
 * sans spécifier le type d'objet à l'avance.
 */
public interface Factory<T> {

    /**
     * Renvoie une nouvelle instance de type T.
     * @return une instance de type T
     */
    T create();
}