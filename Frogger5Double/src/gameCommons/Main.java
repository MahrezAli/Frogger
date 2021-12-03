package gameCommons;

import environment.Environment;
import frog.Frog;
import frog.Frog2;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {

        //Caractéristiques du jeu
        int width = 26;
        int height = 24;
        int tempo = 110;
        int minSpeedInTimerLoops = 3;
        double defaultDensity = 0.1;

        //Création de l'interface graphique
        IFroggerGraphics graphic = new FroggerGraphic(width, height);
        //Création de la partie
        Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);
        //Création et liason de la grenouille
        IFrog frog = new Frog(game);
        IFrog2 frog2 = new Frog2(game);
        game.setFrog(frog);
        game.setFrog2(frog2);
        graphic.setFrog(frog);
        graphic.setFrog2(frog2);
        //Création et liaison de l'environnement
        IEnvironment env = new Environment(game);
        game.setEnvironment(env);

        //Boucle principale : l'environnement s'acturalise tous les tempo milisecondes
        Timer timer = new Timer(tempo, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    game.update();
                    graphic.repaint();
            }
        });
        timer.setInitialDelay(0);
        timer.start();


    }
}
