package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import environment.Environment;
import frog.Frog;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;

public class Main {

    public static void main(String[] args) {

        //Caractéristiques du jeu
        int width = 26;
        int height = 20;
        int tempo = 100;
        int minSpeedInTimerLoops = 3;
        double defaultDensity = 0.1;
        //Création de l'interface graphique
        IFroggerGraphics graphic = new FroggerGraphic(width, height);
        //Création de la partie
        final Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);
        //Création et liason de la grenouille
        IFrog frog = new Frog(game);
        game.setFrog(frog);
        graphic.setFrog(frog);
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
