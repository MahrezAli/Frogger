package gameCommons;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;
import util.Case;

import java.awt.*;
import java.util.Random;

public class Game {

    public final Random randomGen = new Random();

    // Caracteristique de la partie
    public final int width;
    public final int height;
    public final int minSpeedInTimerLoops;
    public final double defaultDensity;

    // Lien aux objets utilisés
    private IEnvironment environment;
    private IFrog frog;
    private IFroggerGraphics graphic;
    //long startTime = System.currentTimeMillis();
    private Chrono chrono;
    public int score = 0;
    public int scoreMax = 0;
    /**
     *
     * @param graphic
     *            l'interface graphique
     * @param width
     *            largeur en cases
     * @param height
     *            hauteur en cases
     * @param minSpeedInTimerLoop
     *            Vitesse minimale, en nombre de tour de timer avant déplacement
     * @param defaultDensity
     *            densite de voiture utilisee par defaut pour les routes
     */
    public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
        super();
        this.graphic = graphic;
        this.width = width;
        this.height = height;
        this.minSpeedInTimerLoops = minSpeedInTimerLoop;
        this.defaultDensity = defaultDensity;
        this.chrono = new Chrono();
        this.chrono.start();
    }

    /**
     * Lie l'objet frog à la partie
     *
     * @param frog
     */
    public void setFrog(IFrog frog) {
        this.frog = frog;
    }

    /**
     * Lie l'objet environment a la partie
     *
     * @param environment
     */
    public void setEnvironment(IEnvironment environment) {
        this.environment = environment;
    }

    /**
     *
     * @return l'interface graphique
     */
    public IFroggerGraphics getGraphic() {
        return graphic;
    }

    public IFrog getFrog() {
        return frog;
    }

    /**
     * Teste si la partie est perdue et lance un écran de fin approprié si tel
     * est le cas
     *
     * @return true si le partie est perdue
     */

    public boolean testLose() {
        if(!this.environment.isSafe(this.frog.getPosition())){
            this.chrono.stop();
            this.graphic.endGameScreen("<html><center> You lose ! </center><br> <center>Votre temps de jeu est de " + this.chrono.getDureeSec() + " secondes </center><br> <center>Votre score est de " + this.scoreMax + " points</center></html>");
            /*
            try {
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - this.startTime;
                long tempsEnSeconde = totalTime / 1000;
                this.graphic.endGameScreen("You lose ! temps de jeu " + tempsEnSeconde + " secondes");
                TimeUnit.SECONDS.sleep(10);
                System.out.println("Votre temps de jeu est de " + tempsEnSeconde + " secondes");

            }
            catch(InterruptedException e){
                e.printStackTrace();;
            }
            */
            return true;
        }
        else{
            return false;
        }
    }
    public boolean testLoses() {
        if(!this.environment.isSafef(this.frog.getPosition())){
            this.chrono.stop();
            this.graphic.endGameScreen("<html><center> You lose ! </center><br> <center>Votre temps de jeu est de " + this.chrono.getDureeSec() + " secondes </center><br> <center>Votre score est de " + this.scoreMax + " points</center></html>");
            return true;
        }
        else{
            return false;
        }
    }

    public void testLosess() {
            this.chrono.stop();
            this.graphic.endGameScreen("<html><center> You lose ! </center><br> <center>Votre temps de jeu est de " + this.chrono.getDureeSec() + " secondes </center><br> <center>Votre score est de " + this.scoreMax + " points</center></html>");
    }


    /**
     * Teste si la partie est gagnee et lance un écran de fin approprié si tel
     * est le cas
     *
     * @return true si la partie est gagnée
     */
    public boolean testWin() {
        if(this.environment.isWinningPosition(this.frog.getPosition())){
            this.chrono.stop();
            this.graphic.endGameScreen("<html><center> You win ! </center><br> <center>Votre temps de jeu est de " + this.chrono.getDureeSec() + " secondes </center><br> <center>Votre score est de " + this.scoreMax + " points</center></html>");
            return true;
        }
        else{
            return false;
        }
    }/*
    public long tempsEcoule(){
        long tempsEnSeconde = 0;
        //ArrayList<Long>test = new ArrayList<Long>();
        if(this.testLose() || this.testWin()) {
            long endTime = System.nanoTime();
            long totalTime = endTime - this.startTime;
            tempsEnSeconde = totalTime / 1000000000;
            //test.add(tempsEnSeconde);
            System.out.println("Votre temps de jeu est de " + tempsEnSeconde + " secondes");
        }
        //return test.get(0);
        return tempsEnSeconde;
    }
    */

    public IEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Actualise l'environnement, affiche la grenouille et verifie la fin de
     * partie.
     */
    public void update() {
        //this.tempsEcoule();
        graphic.clear();
        environment.update();
        this.graphic.add(new Element(frog.getPosition(), Color.GREEN));
        testLose();
        testWin();
        for(int i = 0; i < this.getEnvironment().getLane().size();i++){
            if(this.getFrog().getPosition().ord >= this.height -8 && this.getFrog().getPosition().ord == this.getEnvironment().getLane().get(i).getOrd()){
                if(this.getEnvironment().getLane().get(i).getDensity() > 0.0 ){
                    if(this.getFrog().estPasDansLaGrille()){
                        testLosess();
                    }
                    testLoses();
                }if(this.getEnvironment().getLane().get(i).getDensity() == 0.0){
                    testLose();
                }
            }
        }
        /*
        if(this.testLose() || this.testWin()) {
            if (this.testWin()) {
                try {
                    testWin();
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (this.testLose()) {
                try {
                    testLose();
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

         */
    }

}
