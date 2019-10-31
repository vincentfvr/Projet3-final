package com.ocr.vincent;

public abstract class Mode {
    Combination combination = new Combination();
    Result result = new Result();
    int nbTry = 0;
    boolean gameIsOver = false;
    String secret = ""; // Combinaison secrète
    String input = "";  // Suggestion saisie par l'utilisateur
    String reply = ""; // Résultat après comparaison (+ - =)

    /**
     * Lance le mode sélectionné
     * @param player : nom du joueur
     * @param len : : longueur de la combinaison (nbr de charactères défini dans le .properties)
     */
    void run(String player, int len) {

    }

}
