package com.ocr.vincent;

import java.util.Scanner;
import org.apache.log4j.Logger;

public class Game {

    private static Logger logger = Logger.getLogger(Combination.class);

    /**
     * LANCEMENT DU JEU : Choix du mode
     */
    void play() {
        int mode; // Choix du mode de jeu  1 ou 2 ou 3
        Scanner sc = new Scanner(System.in);
        System.out.println("[NOUVELLE PARTIE]");
        logger.info("[NOUVELLE PARTIE] game.run ");
            do {
                System.out.println("CHOISIR UN MODE DE JEU : 1 - CHALLENGER, 2 - DÉFENSEUR, 3 - DUEL");
                if (Settings.devMode) System.out.println("[MODE DÉVELOPPEUR ACTIF]");
                mode = sc.nextInt();
                // Lancement du mode de jeu choisi
                switch (mode) {
                    case 1: // MODE CHALLENGER
                        System.out.println("[MODE CHALLENGER] : Trouver la bonne combinaison ");
                        logger.info("[MODE CHALLENGER] defender.run");
                        Challenger challenger = new Challenger();
                        challenger.run("USER", Settings.combLen);
                        break;
                    case 2: // MODE DEFENSEUR
                        System.out.println("[MODE DÉFENSEUR] : Définir une combinaison secrète");
                        logger.info("[MODE DÉFENSEUR] defender.run");
                        Defender defender = new Defender();
                        defender.run("CPU", Settings.combLen);
                        break;
                    case 3: // MODE DUEL
                        System.out.println("[MODE DUEL]");
                        logger.info("[MODE DUEL] dual.run");
                        Dual dual = new Dual();
                        dual.run(Settings.combLen);
                        break;
                    case 123: // "123" active le mode Développeur false par defaut dans le .properties
                        //System.out.println("[MODE DÉVELOPPEUR ACTIF]");
                        Settings.devMode = true;
                        logger.info("[MODE DÉVELOPPEUR ACTIF]");
                        break;
                    default:
                        System.out.println("Erreur de saisie : Taper 1, 2 ou 3");
                        logger.info("[Erreur de saisie] choix du mode");
                        break;
                }
            } while (mode < 1 || mode > 4);
        }

    /**
     * Méthode permettant de relancer une partie ou quitter
     * @return b1 True = Rejouer, False = Quitter le jeu
     */
    boolean replay() {

        Scanner sc = new Scanner(System.in);
        Controls controls = new Controls();
        boolean b1 = true; // rue -> Rejouer, False = Quitter le jeu
        boolean b2 ; // Controle de saisie True -> erreur, false -> pas d'erreur
        int myChoise; // Choix 1 ou 2 pour rejouer ou quitter
        String myChoiseStr; // Choix au format string pour le control de saisie

        do {
            System.out.println("Rejouer une partie ? : Taper : 1 -> oui ou 2 -> non");
            myChoiseStr = sc.nextLine();
            b2 = controls.run(myChoiseStr); // Controle de saisie : true -> erreur trouvée (char et non int)
            if (!b2) {
                myChoise = Integer.parseInt(myChoiseStr);
                switch (myChoise) {
                    case 1:
                        b1 = true;
                        break;
                    case 2:
                        System.out.println("[FIN DE PARTIE]");
                        logger.info("[FIN DE PARTIE] game.replay ");
                        b1 = false;
                        break;
                }
            }
        } while (!myChoiseStr.equals("1") && !myChoiseStr.equals("2")); //(!b2)
        return b1;
    }
}
