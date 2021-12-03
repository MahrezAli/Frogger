package gameCommons;

import java.awt.Color;
import java.util.Random;

import gameCommonsInf.EnvInf;
import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

public class Game {

    public final Random randomGen = new Random();

    // Caracteristique de la partie
    public final int width;
    public int height;
    public final int minSpeedInTimerLoops;
    public final double defaultDensity;
    public int score = 0;
    public int scoreMax = 0;

    // Lien aux objets utilisés
    private IEnvironment environment;
    private IFrog frog;
    private IFroggerGraphics graphic;

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
    }

    /**
     * Lie l'objet frog à la partie
     *
     * @param frog
     */
    public void setFrog(IFrog frog) {
        this.frog = frog;
    }

    //public EnvInf getEnvironnement(){
    //    return (EnvInf) this.environment;
    //}

    public IFrog getFrog(){
        return this.frog;
    }
    /**
     * Lie l'objet environment a la partie
     *
     * @param environment
     */
    public void setEnvironment(IEnvironment environment) {
        this.environment = environment;
    }

    public void ajouterLaDerniereLigne(){
        this.environment.ajouterLaDerniereLigne();
    }

    /*
    public void prendLaPlace(){
        //this.environment.ajouterLaDerniereLigne();
        this.environment.prendLaPlace();
        //this.environment.update();
    }
    */

    /**
     *
     * @return l'interface graphique
     */
    public IFroggerGraphics getGraphic() {
        return graphic;
    }

    /**
     * Teste si la partie est perdue et lance un écran de fin approprié si tel
     * est le cas
     *
     * @return true si le partie est perdue
     */
    public boolean testLose() {
        if(!this.environment.isSafe(this.frog.getPosition())){
            this.graphic.endGameScreen("You lose !  Votre score est de : " + this.scoreMax);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Teste si la partie est gagnee et lance un écran de fin approprié si tel
     * est le cas
     *
     * @return true si la partie est gagnée
     */
    /*
    public boolean testWin() {
        if(this.environment.isWinningPosition(this.frog.getPosition())){
            this.graphic.endGameScreen("You win !");
            return true;
        }
        else{
            return false;
        }
    }
    */

    /**
     * Actualise l'environnement, affiche la grenouille et verifie la fin de
     * partie.
     */
    public void update() {
        graphic.clear();
        environment.update();
        if(this.frog.getPosition().ord < 3) {
            this.graphic.add(new Element(this.frog.getPosition(), Color.GREEN));
        }
        if(this.frog.getPosition().ord >= 3) {
            this.graphic.add(new Element(this.frog.getPosition().absc, 3, Color.GREEN));
        }
        this.testLose();
        //this.testWin();
    }

}
