package gameCommons;

import environment.Environment;
import environment2.Environment2;
import frog.Frog;
import frogDuo.FrogGreen;
import frogDuo.FrogBlue;
import gameCommons2.Game2;
import gameCommons2.IEnvironment2;
import gameCommons2.IFrogGreen;
import gameCommons2.IFrogBlue;
import gameCommons3.Game3;
import gameCommons3.IEnvironment3;
import gameCommons3.IFrog3;
import gameCommonsInf3.EnvInf3;
import gameCommonsInf3.FrogInf3;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;
import graphicalElements2.FroggerGraphic2;
import graphicalElements2.IFroggerGraphics2;
import graphicalElements3.FroggerGraphic3;
import graphicalElements3.IFroggerGraphics3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //Caractéristiques du jeu
        int width = 26;
        int height = 24;
        int tempo = 110;
        int minSpeedInTimerLoops = 3;
        double defaultDensity = 0.05;



        // création du graphique du jeu double (Frogger2) et jeu infini (Frogger3)
        IFroggerGraphics2 graphict = new FroggerGraphic2(width, height);
        IFroggerGraphics3 graphic3 = new FroggerGraphic3(width, height);

        // création de la parti pour le jeu normal
        IFroggerGraphics graphic = new FroggerGraphic(width, height, graphict);
        Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);
        game.getMusic().stopSound();
        IFrog frog = new Frog(game);
        game.setFrog(frog);
        graphic.setFrog(frog);
        IEnvironment env = new Environment(game);
        game.setEnvironment(env);
        ArrayList<Game> games = new ArrayList<>();
        ArrayList<IEnvironment> envs = new ArrayList<>();
        ArrayList<IFrog> frogs = new ArrayList<>();
        ArrayList<IFroggerGraphics> graphics = new ArrayList<>();
        games.add(game);
        envs.add(env);
        frogs.add(frog);
        graphics.add(graphic);

        // création de la parti pour le jeu double
        Game2 gamet = new Game2(graphict, width, height, minSpeedInTimerLoops, defaultDensity);
        gamet.getMusic().stopSound();
        IFrogGreen frogt = new FrogGreen(gamet);
        IFrogBlue frog2t = new FrogBlue(gamet);
        gamet.setFrog(frogt);
        gamet.setFrog2(frog2t);
        graphict.setFrog(frogt);
        graphict.setFrog2(frog2t);
        IEnvironment2 envt = new Environment2(gamet);
        gamet.setEnvironment(envt);
        ArrayList<Game2> gamest = new ArrayList<>();
        ArrayList<IEnvironment2> envst = new ArrayList<>();
        ArrayList<IFrogGreen> frogst = new ArrayList<>();
        ArrayList<IFrogBlue> frogst2 = new ArrayList<>();
        ArrayList<IFroggerGraphics2> graphicst = new ArrayList<>();
        gamest.add(gamet);
        envst.add(envt);
        frogst.add(frogt);
        frogst2.add(frog2t);
        graphicst.add(graphict);

        // création de la parti simple pour le jeu infini
        Game3 game3 = new Game3(graphic3, width, height, minSpeedInTimerLoops, defaultDensity);
        game3.getMusic().stopSound();
        IFrog3 frog3 = new FrogInf3(game3);
        game3.setFrog(frog3);
        graphic3.setFrog(frog3);
        IEnvironment3 env3 = new EnvInf3(game3);
        game3.setEnvironment(env3);
        ArrayList<Game3> games3 = new ArrayList<>();
        ArrayList<IEnvironment3> envs3 = new ArrayList<>();
        ArrayList<IFrog3> frogs3 = new ArrayList<>();
        ArrayList<IFroggerGraphics3> graphics3 = new ArrayList<>();
        games3.add(game3);
        envs3.add(env3);
        frogs3.add(frog3);
        graphics3.add(graphic3);


        Timer timer = new Timer(tempo, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(graphict.getNbJoueur() == 1){
                    graphic.getFrame().setVisible(true);
                    graphict.getFrame().setVisible(false);
                    graphic.setNbJoueur(1);
                    graphict.setNbJoueur(2);
                    graphict.setStraw(4);
                }
                if(graphic.getNbJoueur() == 3){
                    graphic3.getFrame().setVisible(true);
                    graphic.getFrame().setVisible(false);
                    graphic3.setNbJoueur(3);
                    graphic.setNbJoueur(1);
                    graphic.setStraw(1);
                }
                if(graphic3.getNbJoueur() == 1){
                    graphic.getFrame().setVisible(true);
                    graphic3.getFrame().setVisible(false);
                    graphic.setNbJoueur(1);
                    graphic3.setNbJoueur(3);
                    graphic3.setStraw(7);

                }if(graphic3.getNbJoueur() == 2){
                    graphict.getFrame().setVisible(true);
                    graphic3.getFrame().setVisible(false);
                    graphict.setNbJoueur(2);
                    graphic3.setNbJoueur(3);
                    graphic3.setStraw(7);
                }
                if(graphics.get(graphics.size()-1).getNbJoueur() == 1) {
                    if (frogs.get(frogs.size() - 1).getRestart() == true) {
                        if (graphics.get(graphics.size() - 1).getStraw() == 1) {
                            games.remove(0);
                            envs.remove(0);
                            frogs.remove(0);
                            games.add(new Game(graphics.get(graphics.size() - 1), width, height, minSpeedInTimerLoops, defaultDensity));
                            envs.add(new Environment(games.get(games.size() - 1)));
                            frogs.add(new Frog(games.get(games.size() - 1)));
                            games.get(games.size() - 1).setFrog(frogs.get(frogs.size() - 1));
                            graphics.get(graphics.size() - 1).setFrog(frogs.get(frogs.size() - 1));
                            games.get(games.size() - 1).setEnvironment(envs.get(envs.size() - 1));
                        }
                        if (graphics.get(graphics.size() - 1).getStraw() == 2) {
                            games.remove(0);
                            envs.remove(0);
                            frogs.remove(0);
                            games.add(new Game(graphic, width, height, minSpeedInTimerLoops, 0.1));
                            envs.add(new Environment(games.get(games.size() - 1)));
                            frogs.add(new Frog(games.get(games.size() - 1)));
                            games.get(games.size() - 1).setFrog(frogs.get(frogs.size() - 1));
                            graphics.get(graphics.size() - 1).setFrog(frogs.get(frogs.size() - 1));
                            games.get(games.size() - 1).setEnvironment(envs.get(envs.size() - 1));
                        }
                        if (graphics.get(graphics.size() - 1).getStraw() == 3) {
                            games.remove(0);
                            envs.remove(0);
                            frogs.remove(0);
                            games.add(new Game(graphic, width, height, minSpeedInTimerLoops, 0.2));
                            envs.add(new Environment(games.get(games.size() - 1)));
                            frogs.add(new Frog(games.get(games.size() - 1)));
                            games.get(games.size() - 1).setFrog(frogs.get(frogs.size() - 1));
                            graphics.get(graphics.size() - 1).setFrog(frogs.get(frogs.size() - 1));
                            games.get(games.size() - 1).setEnvironment(envs.get(envs.size() - 1));
                        }

                    }
                    games.get(games.size() - 1).update();
                    graphics.get(graphics.size() - 1).repaint();
                }
                if( graphicst.get(graphicst.size()-1).getNbJoueur() == 2) {
                    if ( frogst.get(frogst.size() - 1).getRestart() == true || frogst2.get(frogst2.size() - 1).getRestart() == true){
                        if ( graphicst.get(graphicst.size() - 1).getStraw() == 4) {
                            gamest.remove(0);
                            envst.remove(0);
                            frogst.remove(0);
                            frogst2.remove(0);
                            gamest.add(new Game2(graphict, width, height, minSpeedInTimerLoops, defaultDensity));
                            envst.add(new Environment2(gamest.get(gamest.size() - 1)));
                            frogst.add(new FrogGreen(gamest.get(gamest.size() - 1)));
                            frogst2.add(new FrogBlue(gamest.get(gamest.size() - 1)));
                            gamest.get(gamest.size() - 1).setFrog(frogst.get(frogst.size() - 1));
                            gamest.get(gamest.size() - 1).setFrog2(frogst2.get(frogst2.size() - 1));
                            graphicst.get(graphicst.size() - 1).setFrog(frogst.get(frogst.size() - 1));
                            graphicst.get(graphicst.size() - 1).setFrog2(frogst2.get(frogst2.size() - 1));
                            gamest.get(gamest.size() - 1).setEnvironment(envst.get(envst.size() - 1));
                        }
                        if ( graphicst.get(graphicst.size() - 1).getStraw() == 5) {
                            gamest.remove(0);
                            envst.remove(0);
                            frogst.remove(0);
                            frogst2.remove(0);
                            gamest.add(new Game2(graphict, width, height, minSpeedInTimerLoops, 0.1));
                            envst.add(new Environment2(gamest.get(gamest.size() - 1)));
                            frogst.add(new FrogGreen(gamest.get(gamest.size() - 1)));
                            frogst2.add(new FrogBlue(gamest.get(gamest.size() - 1)));
                            gamest.get(gamest.size() - 1).setFrog(frogst.get(frogst.size() - 1));
                            gamest.get(gamest.size() - 1).setFrog2(frogst2.get(frogst2.size() - 1));
                            graphicst.get(graphicst.size() - 1).setFrog(frogst.get(frogst.size() - 1));
                            graphicst.get(graphicst.size() - 1).setFrog2(frogst2.get(frogst2.size() - 1));
                            gamest.get(gamest.size() - 1).setEnvironment(envst.get(envst.size() - 1));
                        }
                        if ( graphicst.get(graphicst.size() - 1).getStraw() == 6) {
                            gamest.remove(0);
                            envst.remove(0);
                            frogst.remove(0);
                            frogst2.remove(0);
                            gamest.add(new Game2(graphict, width, height, minSpeedInTimerLoops, 0.2));
                            envst.add(new Environment2(gamest.get(gamest.size() - 1)));
                            frogst.add(new FrogGreen(gamest.get(gamest.size() - 1)));
                            frogst2.add(new FrogBlue(gamest.get(gamest.size() - 1)));
                            gamest.get(gamest.size() - 1).setFrog(frogst.get(frogst.size() - 1));
                            gamest.get(gamest.size() - 1).setFrog2(frogst2.get(frogst2.size() - 1));
                            graphicst.get(graphicst.size() - 1).setFrog(frogst.get(frogst.size() - 1));
                            graphicst.get(graphicst.size() - 1).setFrog2(frogst2.get(frogst2.size() - 1));
                            gamest.get(gamest.size() - 1).setEnvironment(envst.get(envst.size() - 1));
                        }
                    }
                    gamest.get(gamest.size() - 1).update();
                    graphicst.get(graphicst.size() - 1).repaint();
                }
                if(graphics3.get(graphics3.size()-1).getNbJoueur() == 3) {
                    if (frogs3.get(frogs3.size() - 1).getRestart() == true) {
                        if (graphics3.get(graphics3.size() - 1).getStraw() == 7) {
                            games3.remove(0);
                            envs3.remove(0);
                            frogs3.remove(0);
                            games3.add(new Game3(graphics3.get(graphics3.size() - 1), width, height, minSpeedInTimerLoops, defaultDensity));
                            envs3.add(new EnvInf3(games3.get(games3.size() - 1)));
                            frogs3.add(new FrogInf3(games3.get(games3.size() - 1)));
                            games3.get(games3.size() - 1).setFrog(frogs3.get(frogs3.size() - 1));
                            graphics3.get(graphics3.size() - 1).setFrog(frogs3.get(frogs3.size() - 1));
                            games3.get(games3.size() - 1).setEnvironment(envs3.get(envs3.size() - 1));
                        }
                        if (graphics3.get(graphics3.size() - 1).getStraw() == 8) {
                            games3.remove(0);
                            envs3.remove(0);
                            frogs3.remove(0);
                            games3.add(new Game3(graphics3.get(graphics3.size() - 1), width, height, minSpeedInTimerLoops,0.075));
                            envs3.add(new EnvInf3(games3.get(games3.size() - 1)));
                            frogs3.add(new FrogInf3(games3.get(games3.size() - 1)));
                            games3.get(games3.size() - 1).setFrog(frogs3.get(frogs3.size() - 1));
                            graphics3.get(graphics3.size() - 1).setFrog(frogs3.get(frogs3.size() - 1));
                            games3.get(games3.size() - 1).setEnvironment(envs3.get(envs3.size() - 1));
                        }
                        if (graphics3.get(graphics3.size() - 1).getStraw() == 9) {
                            games3.remove(0);
                            envs3.remove(0);
                            frogs3.remove(0);
                            games3.add(new Game3(graphics3.get(graphics3.size() - 1), width, height, minSpeedInTimerLoops, 0.1));
                            envs3.add(new EnvInf3(games3.get(games3.size() - 1)));
                            frogs3.add(new FrogInf3(games3.get(games3.size() - 1)));
                            games3.get(games3.size() - 1).setFrog(frogs3.get(frogs3.size() - 1));
                            graphics3.get(graphics3.size() - 1).setFrog(frogs3.get(frogs3.size() - 1));
                            games3.get(games3.size() - 1).setEnvironment(envs3.get(envs3.size() - 1));
                        }
                    }
                    games3.get(games3.size() - 1).update();
                    graphics3.get(graphics3.size() - 1).repaint();
                }
            }
        });
        timer.setInitialDelay(0);
        timer.start();

    }
}
