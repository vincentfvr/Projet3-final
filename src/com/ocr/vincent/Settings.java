package com.ocr.vincent;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Settings {

    static int combLen; /**nbr de chiffres de la combinaison par défaut à charger depuis les paramètres xml */
    static int nbTryLimit; /** nombre d'essais autorisés avant de trouver la bonne solution */
    static boolean devMode; /** false Normal - true Mode Développeur*/
    static String winReply = ""; /** Résultat de la combinaison à obtenir (=) x combLen */
    static List<Integer> maxValues = new ArrayList<Integer>(combLen); /** Mémorise toutes les valeurs max*/
    static List<Integer> minValues = new ArrayList<Integer>(combLen); /** Mémorise toutes les valeurs min*/
    static List<Integer> theValues = new ArrayList<Integer>(combLen); /** Mémorise toutes les valeurs justes*/

    /**
     * Chargement des paramètres depuis le fichier settings.properties
     * Initialisation des 3 listes integer en fonction de comLen
     */
    public static void load () {
        try (InputStream input = new FileInputStream("resources/settings.properties")) {
            Properties prop = new Properties();
            /** load a properties file */
            prop.load(input);
            /** get the property value and print it out */
            //String temp = prop.getProperty("combLen");
            combLen = Integer.valueOf(prop.getProperty("combLen"));
            nbTryLimit = Integer.valueOf(prop.getProperty("nbTryLimit"));
            devMode = Boolean.parseBoolean(prop.getProperty("devMode"));

            /** Caclul de winReply : contient combLen x "=" */
            for (int i=0; i< combLen; i++) {
                winReply =  winReply + "=";
                // les listes suivantes sont pour le joueur CPU, mode defender et duel
                maxValues.add(9);
                minValues.add(1);
                theValues.add(0);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Remise à zèro des 3 listes integer (pour le mode defender et dual uniquement), joueur CPU
     */
    public static void clear () {
        /** Remise à zéro avec replay true*/
        winReply = "";
        theValues.clear();
        maxValues.clear();
        minValues.clear();
    }
}