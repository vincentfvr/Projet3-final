package com.ocr.vincent;

public class Main {

    public static void main(String[] args) {

        boolean replay; // true : Rejouer, false : fin de partie
        Game game = new Game();

        do {
            /** Load settings.properties */
            Settings.load();
            /** Start game and replay request */
            game.play();
            replay = game.replay();
            if (replay) Settings.clear(); // Réinitialisation des Settings
        }  while (replay);
    }
}
