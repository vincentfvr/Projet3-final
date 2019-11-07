package com.ocr.vincent;

import org.apache.log4j.Logger;

public class Dual {

    private static Logger logger = Logger.getLogger(Combination.class);

    /**
     * LANCEMENT DU MODE DUEL MODE 3
     * @param len : longueur de la combinaison (nbr de charactères défini dans le .properties)
     */
    public void run(int len) {

        Combination combination = new Combination();
        Result result = new Result();
        int nbTry = 0;
        boolean gameIsOver = false;

        System.out.println("Création de ma combinaison :");

        String userSecret = combination.ask(len);  // Combinaison secrète saisie par l'utilisateur
        String cpuSecret = combination.generate(len); // combinaison secrète générée aléatoirement par CPU

        logger.info("combination.ask : userSecret = " + userSecret);
        logger.info("combination.generate : cpuSecret = " + cpuSecret);

        String userReply; // Résultat après comparaison (+ - =)
        String cpuReply; // Résultat après comparaison (+ - =)

        System.out.println("Joueur CPU a créé sa combinaison");
        combination.solution(cpuSecret); // en mode DEV

        do {
            nbTry += 1;
            System.out.println("Saisir votre suggestion :");
            String userInput = combination.ask(len);
            String cpuInput = combination.find(len);
            logger.info("combination.ask : userInput = " + userInput);
            logger.info("combination.generate : cpuInput = " + cpuInput);
            String player;

            if (!gameIsOver) {
                // TOUR ORDINATEUR
                System.out.println("----------------------------------");
                player = "CPU";
                combination.display(cpuInput, player, nbTry);
                cpuReply = combination.compare(userSecret, cpuInput, player, len);
                logger.info("combination.compare : cpuReply = " + cpuReply);
                gameIsOver = result.run(player, userSecret, cpuReply , Settings.winReply, gameIsOver, nbTry);
            }

            if (!gameIsOver){
                // TOUR UTILISATEUR
                System.out.println("----------------------------------");
                player ="USER";
                combination.display(userInput, player, nbTry);
                userReply = combination.compare(cpuSecret, userInput, player, len);
                logger.info("combination.compare : userReply = " + userReply);
                gameIsOver = result.run(player, cpuSecret, userReply , Settings.winReply, gameIsOver, nbTry);
                System.out.println("----------------------------------");
            }
        } while (!gameIsOver);
    }
}