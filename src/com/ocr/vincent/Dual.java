package com.ocr.vincent;

import org.apache.log4j.Logger;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Dual {

    private static Logger logger = Logger.getLogger(Combination.class);

    /**
     * LANCEMENT DU MODE DUEL MODE 3
     * @param len : longueur de la combinaison (nbr de charactères défini dans le .properties)
     */
    public void run(int len) {

        Combination combination = new Combination();
        Result result = new Result();
        int userTry = 0;
        int cpuTry = 0;
        boolean gameIsOver = false;
        String userReply; // Résultat après comparaison (+ - =)
        String cpuReply; // Résultat après comparaison (+ - =)
        String inputReply ="";
        String player1 = this.firstPlayer();
        String player2 ="";
        if (player1.equals("USER")) {
            player2 = "CPU";
        } else if (player1.equals("CPU")){
            player2 = "USER";
        }

        String cpuSecret = combination.generate(len); // combinaison secrète générée aléatoirement par CPU
        System.out.println("Joueur CPU a créé sa combinaison");
        System.out.println("Création de ma combinaison :");
        String userSecret = combination.ask(len);  // Combinaison secrète saisie par l'utilisateur

        logger.info("combination.ask : userSecret = " + userSecret);
        logger.info("combination.generate : cpuSecret = " + cpuSecret);

        combination.solution(cpuSecret); // en mode DEV
        String player = player1;

        do {

            if (!gameIsOver & player.equals("CPU")) {
                // TOUR ORDINATEUR
                cpuTry += 1;
                String cpuInput = combination.find(len);
                logger.info("combination.generate : cpuInput = " + cpuInput);
                combination.display(cpuInput, player, cpuTry);
                cpuReply = combination.compare(userSecret, cpuInput, player, len);
                inputReply = result.ask(cpuReply, len, userSecret, cpuInput);
                logger.info("combination.compare : cpuReply = " + cpuReply);
                gameIsOver = result.run(player, userSecret, inputReply , Settings.winReply, gameIsOver, cpuTry);
            }

            if (!gameIsOver & player.equals("USER")){
                // TOUR UTILISATEUR
                userTry += 1;
                System.out.println("Saisir votre suggestion :");
                String userInput = combination.ask(len);
                logger.info("combination.ask : userInput = " + userInput);
                combination.display(userInput, player, userTry);
                userReply = combination.compare(cpuSecret, userInput, player, len);
                logger.info("combination.compare : userReply = " + userReply);
                gameIsOver = result.run(player, cpuSecret, userReply , Settings.winReply, gameIsOver, userTry);
            }

            if (player.equals(player1)) {
                player = player2;
            } else if (player.equals(player2)){
                player = player1;
            }

        } while (!gameIsOver);
    }

    /**
     * Choix du joueur qui commencera en premier de jouer
     * @return player1 "USER ou "CPU"
     */
    private String firstPlayer() {
           String player1;
            int min = 1;
            int max = 6;
            int userValue;
            int cpuValue;
            int values[] = new int [4];

            do {
                for (int i=0; i<4; i++) {
                    values[i] = min + (int)(Math.random() * ((max - min) + 1));
                }
                userValue = values[0] + values[1];
                cpuValue = values[2] + values[3];
                System.out.println("Jet de dés : [USER] -> " + values[0] + " et " + values[1] + " [CPU] -> " + values[2] + " et " + values[3]);

                if (cpuValue==userValue) {
                    System.out.println("Egalité, nouveau jet de dés");
                }

            } while (cpuValue==userValue);
            if (cpuValue>userValue) {
                player1 = "CPU";
            } else {
                player1 = "USER";
            }
        System.out.println("[" + player1  + "] jouera en premier");
        logger.info("Random : USER -> " + values[0] + " et " + values[1] + "Random : CPU --> " + values[2] + " et " + values[3]);
        logger.info("[" + player1 + "] jouera en premier");
        return player1;
    }
}