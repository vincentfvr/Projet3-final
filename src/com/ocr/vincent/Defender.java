package com.ocr.vincent;

public class Defender extends Mode{

    /**
     * LANCEMENT DU MODE DEFENDER MODE 2
     * @param player : nom du joueur
     * @param len : longueur de la combinaison (nbr de charactères défini dans le .properties)
     */
    @Override
    public void run(String player, int len) {
        System.out.println("Saisir une combinaison de " + len + " chiffre(s) (entre 1 et 9) et valider avec Entrée");
        secret = combination.ask(len); // combination secrète du joueur USER
        String myReply ="";

        do {
            nbTry += 1;
            input = combination.find(len);
            combination.display (input, player, nbTry);
            reply = combination.compare(secret, input, player, len);
            myReply = result.ask(reply, len, secret, input);
            gameIsOver = result.run(player, secret, myReply , Settings.winReply, gameIsOver, nbTry);
        } while (!gameIsOver);
    }
}
