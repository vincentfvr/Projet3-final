package com.ocr.vincent;

import org.apache.log4j.Logger;

public class Controls {

    private static Logger logger = Logger.getLogger(Combination.class);

    /**
     * Input Control : char by char isDigit
     * @param test chaine string à controler
     * @return b1 état du controle d'entrée. False par défaut. Renvoie True si une erreur est trouvée
     */
    public boolean run(String test) {
        boolean b1 = false; //
        boolean b2; // variable True/false du test isDigit()
        char unit; // variable temporaire pour la boucle. Stocke caractère par caractère de la chaine testComb

        for (int i=0 ; i < test.length(); i++) {
            unit = test.charAt(i);
            b2 = Character.isDigit(unit);
            if (!b2){
                b1 = true;
            }
        }
        if (b1) {
            System.out.println("Saisir uniquement des chiffres");
            logger.info("[ERREUR DE SAISIE] Saisir uniquement des chiffres");
        }
        return b1;
    }
}
