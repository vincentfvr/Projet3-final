package com.ocr.vincent;

import java.util.Scanner;
import org.apache.log4j.Logger;

public class Combination {

    private static Logger logger = Logger.getLogger(Combination.class);
    boolean devMode = Settings.devMode;

    /**
     * Demande à l'utilisateur de saisir une suggestion de combinaison
     * + Procédure de controle de saisie : nombre de caractère +
     * charControl : True si le controle doit être effectué, false si le controle n'est plus à faire
     * @param len : longueur de la combinaison (nbr de charactères défini dans le .properties)
     * @return userComb = Combinaison saisie par l'utilisateur
     */
    public String ask(int len) {

        Scanner sc = new Scanner(System.in);
        Controls controls = new Controls();
        String userComb ="";
        boolean charControl = true;

        do {
            userComb = sc.nextLine(); // scanner
            if (userComb.length() != len) { // CONTROLE DU NOMBRE DE CHARACTÈRES SAISIS
                System.out.println("Saisir une combinaison de " + len + " chiffre(s) (entre 1 et 9) et valider avec ENTER");
                charControl = true;
            } else if (userComb.length() == len) {
                charControl = controls.run(userComb); // CONTROLE QUE TOUS LES CHARACTERES SOIENT DES CHIFFRES
            }
        } while (charControl);
        return userComb;
    }

    /**
     *  Génere de manière aléatoire une combinaison à X chiffres
     * @param len : longueur de la combinaison (nbr de charactères défini dans le .properties)
     * @return code = la combinaison génèrée
     */
    public String generate(int len) {
        int num;
        String code ="";

        for (int i = 0; i < len; i++) {
            do {
                num = (int) (Math.random() * 10);     /** avec 0 < nb < 9 */
                if (num >= 1) {
                    code += num;
                }
            } while (num == 0);
        }
        return code;
    }

    /**
     * COMPARATEUR DE COMBINAISONS
     * @param comb1 : Combinaison à trouver
     * @param comb2 : Combinaison suggerée
     * @param player : nom du joueur
     * @param len : longueur de la combinaison (nbr de charactères défini dans le .properties)
     * @return theReply : Contient le résultat de la comparaison comb1/comb2 (+, -, =)
     */
    public String compare (String comb1, String comb2, String player, int len) {
        String theReply = "";
        int secret;
        int suggest;

        for (int i = 0 ; i<len; i++) {
            secret = Character.getNumericValue(comb1.charAt(i));
            suggest = Character.getNumericValue(comb2.charAt(i));
            if (secret == suggest) { // CAS 1 - LES VALEURS SONT ÉGALES
                if (player=="CPU") { // Ne pas enlever la condition CPU sinon, bug en mode dual
                    Settings.theValues.set(i, secret);
                    Settings.maxValues.set(i, 0);
                    Settings.minValues.set(i, 0);
                }
                theReply += "=";
            } else if (secret < suggest) { // CAS 2 - LE RÉSULTAT DU TEST en INFÉRIEUR [-]
                    if (Settings.theValues.get(i) == 0 & player=="CPU") Settings.maxValues.set(i, suggest - 1);
                theReply += "-";
            } else if (secret > suggest) { // CAS 3 - LE RÉSULTAT DU TEST en INFÉRIEUR [-]
                if (Settings.theValues.get(i) == 0 & player=="CPU") Settings.minValues.set(i, suggest + 1);
                theReply += "+";
            }
        }
        return theReply;
    }

    /**
     * Recherche par le CPU d'une combinaison en fonction des suggestions précédentes
     * @param len : longueur de la combinaison (nbr de charactères défini dans le .properties)
     * @return cpuComb : Suggestion faite par le CPU
     */
    public String find(int len) {
        String cpuComb="";
        int nb;

        for (int i = 0; i < len; i++) {
            do {
                // Si trouvé au tour précédent, alors nb = valeur précédente enregistrée dans la liste thevalues
                if (Settings.theValues.get(i) > 0) {
                    nb = Settings.theValues.get(i);
                } else {
                    // Si il ne reste que deux possiblités : random pour l'une ou l'autre
                    if (Settings.maxValues.get(i) - Settings.minValues.get(i) == 1) {
                        nb = Settings.minValues.get(i) + (int) (Math.random() * Settings.maxValues.get(i)) - 1;
                        if (nb >= Settings.maxValues.get(i)) {
                            nb = Settings.maxValues.get(i);
                        } else if (nb <= Settings.minValues.get(i)) {
                            nb = Settings.minValues.get(i);
                        }
                    } else {
                        nb = (Settings.minValues.get(i) + Settings.maxValues.get(i)) / 2;
                    }
                }
                cpuComb += nb;
            } while (nb <= 0);

            if (devMode) {
                System.out.println("CPU_COMB -> " + cpuComb);
                logger.info("CPU_COMB -> " + cpuComb);
                System.out.println("Char " + (i + 1) + " = " + nb + " max " + Settings.maxValues.get(i) + " min " + Settings.minValues.get(i));
                logger.info("Char " + (i + 1) + " = " + nb + " max " + Settings.maxValues.get(i) + " min " + Settings.minValues.get(i));
            }
        }
        return cpuComb;
    }

    /**
     * Affiche la proposition faite avec + le nombre d'essais
     * @param secret : combinaison secrète (ne s'affiche qu'en mode développeur)
     * @param suggest : combinaison suggèrée
     * @param player : nom du joueur
     * @param nbTry : compteur d'essai
     */
    public void display(String secret, String suggest, String player, int nbTry) {
        if (devMode) {
            System.out.println("SECRET-COMBINATION   : " + secret);
            logger.info("SECRET-COMBINATION : " + secret);
        }
            System.out.println( "[" + player + "] Essai " + nbTry);
            System.out.println("Suggestion --> " + suggest);
            logger.info( "[" + player + "] Essai " + nbTry);
            logger.info("Suggestion --> " + suggest);
    }
}