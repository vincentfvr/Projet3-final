package com.ocr.vincent;

import org.apache.log4j.Logger;

import java.util.Scanner;

public class Result {

    private static Logger logger = Logger.getLogger(Combination.class);

    /**
     * Résultat fin de partie : Partie gagnée, nouvel essai ou partie perdue (nombre d'essais dépassé)
     * @param player : nom du joueur
     * @param secret : la combinaison secrète
     * @param theReply : Contient le résultat de la comparaison comb1/comb2 (+, -, =)
     * @param winReply : Contient le résultat attendu ( "=" x longueur de la combinaison)
     * @param gameIsOver : true si partie terminée (qu'elle soit gagnée ou perdue)
     * @param nbTry : compteur d'essai
     * @return gameIsOver
     */
    public boolean run(String player, String secret, String theReply, String winReply, boolean gameIsOver, int nbTry) {

        String newLine = System.getProperty("line.separator");

        // Affichage des indications en fonction de la suggestion
            System.out.println("Indication --> " + theReply);
            logger.info("Indication --> " + theReply);

        // Affichage des indications en mode Développeur uniquement
        if (Settings.devMode & player=="CPU") {
            System.out.println("maxValues : " + Settings.maxValues);
            System.out.println("minValues : " + Settings.minValues);
            System.out.println("theValues : " + Settings.theValues);
            logger.info("maxValues : " + Settings.maxValues);
            logger.info("minValues : " + Settings.minValues);
            logger.info("theValues : " + Settings.theValues);
        }

        // Affichage fin de partie : Partie gagnée, nouvel essai ou partie perdue (nombre d'essais dépassé)
        if (theReply.contains(winReply)) {
            gameIsOver = true;
            System.out.println(newLine + "**********************************************************");
            System.out.println("   Joueur " + player + " A GAGNÉ! Combinaison trouvée en " + nbTry + " coups.");
            System.out.println("**********************************************************");
            logger.info("Joueur " + player + " A GAGNÉ! Combinaison trouvée en " + nbTry + " coups.");
        } else {

            if (nbTry == Settings.nbTryLimit) {
                gameIsOver = true;
                System.out.println(newLine + "[PARTIE PERDUE] Nombre d'essais dépassé (" + Settings.nbTryLimit + ")");
                System.out.println("La bonne combinaison était : " + secret);
                logger.info("[PARTIE PERDUE] La bonne combinaison était : " + secret);
            } else {
                if (player=="USER"){
                    System.out.println(newLine + "[NOUVEL ESSAI] (" + (nbTry + 1) + "/" + Settings.nbTryLimit + ")");
                    logger.info("[NOUVEL ESSAI] (" + (nbTry + 1) + "/" + Settings.nbTryLimit + ")");
                }
            }
        }
        return gameIsOver;
    }

    public String ask (String reply, int len, String secret, String input) {

        Scanner sc = new Scanner(System.in);
        String myReply;

        System.out.println("Combinaison -> " + secret);
        System.out.println("Résultat ? ");
        logger.info("Résultat ? Secret : " + secret + "Suggestion : " + input);
        do {
            myReply = sc.nextLine(); // scanner
            if (myReply.length() != len) { // Control du nombre de caractères saisis
                System.out.println("Saisir le résultat pour la combinaison de " + len + " chiffre(s) (+ ou - ou =)");
                logger.info("[ERREUR DE SAISIE] : nombre de caractères");
            } else if (!myReply.equals(reply)) {
                System.out.println("Saisir le bon résultat. Le résultat proposé est incorrect");
                logger.info("[ERREUR DE SAISIE] : Le résultat proposé est incorrect");
            }
        } while (!myReply.equals(reply));
        return myReply;
    }


}
