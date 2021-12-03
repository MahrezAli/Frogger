package gameCommons;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

import java.awt.*;
import java.net.URL;
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
    private Chrono chrono;
    Music music = new Music();
    Music musicWater = new Music();
    Music musicCar = new Music();
    public int score = 0;
    public int scoreMax = 0;
    public boolean jeuEnCour = true;
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
        URL music = getClass().getResource("/Music/FroggerMusic" + toString(genererInt(1,3)) + ".wav");
        this.music.playSound(music);
        this.chrono = new Chrono();
        this.chrono.start();

    }

    private String toString(int x) {
        return String.valueOf(x);
    }

    public int genererInt(int borneInf, int borneSup){
        Random random = new Random();
        int nb;
        nb =  borneInf + random.nextInt(borneSup - borneInf) ;
        return nb;
    }

    public Music getMusic() {
        return music;
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
            frog.setLife(false);
            this.music.stopSound();
            URL test = getClass().getResource("/Music/SoundCar.wav");
            this.musicCar.playSound(test);
            jeuEnCour = false;
            this.graphic.endGameScreen("<html><center> You lose ! </center><br> <center>Votre temps de jeu est de " + this.chrono.getDureeSec() + " secondes </center><br> <center>Votre score est de " + this.scoreMax + " points</center></html>");
            return true;
        }
        else{
            return false;
        }
    }
    public boolean testLoses() {
        if(!this.environment.isSafef(this.frog.getPosition())){
            this.chrono.stop();
            frog.setLife(false);
            this.music.stopSound();
            URL test = getClass().getResource("/Music/WaterSplash.wav");
            this.musicWater.playSound(test);
            jeuEnCour = false;
            this.graphic.endGameScreen("<html><center> You lose ! </center><br> <center>Votre temps de jeu est de " + this.chrono.getDureeSec() + " secondes </center><br> <center>Votre score est de " + this.scoreMax + " points</center></html>");
            return true;
        }
        else{
            return false;
        }
    }

    public void testLosess() {
        this.chrono.stop();
        frog.setLife(false);
        this.music.stopSound();
        URL test = getClass().getResource("/Music/WaterSplash.wav");
        this.musicWater.playSound(test);
        jeuEnCour = false;
        this.graphic.endGameScreen("<html><center> You lose ! </center><br> <center>Votre temps de jeu est de " + this.chrono.getDureeSec() + " secondes </center><br> <center>Votre score est de " + this.scoreMax + " points</center></html>");
    }

    public void setGame(Game g){
        this.graphic = g.graphic;
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
            frog.setLife(false);
            this.music.stopSound();
            jeuEnCour = false;
            this.graphic.endGameScreen("<html><center> You win ! </center><br> <center>Votre temps de jeu est de " + this.chrono.getDureeSec() + " secondes </center><br> <center>Votre score est de " + this.scoreMax + " points</center></html>");
            return true;
        }
        else{
            return false;
        }
    }

    public IEnvironment getEnvironment() {
        return environment;
    }






    /**
     * Actualise l'environnement, affiche la grenouille et verifie la fin de
     * partie.
     */
    public void update() {
        graphic.clear();
        environment.update();
        this.graphic.add(new Element(frog.getPosition(), Color.GREEN));
        if(jeuEnCour) {
            if (this.graphic.getJeuEnCour() == false) {
                testLose();
                testWin();
                for (int i = 0; i < this.getEnvironment().getLane().size(); i++) {
                    if (this.getFrog().getPosition().ord == this.getEnvironment().getLane().get(i).getOrd() && this.getEnvironment().getLane().get(i).getCar() == false) {
                        if (this.getEnvironment().getLane().get(i).getDensity() > 0.0) {
                            if (this.getFrog().estPasDansLaGrille()) {
                                testLosess();
                            }
                            testLoses();
                        }
                        if (this.getEnvironment().getLane().get(i).getDensity() == 0.0) {
                            testLose();
                        }
                    }
                }
            }
        }
    }

}
