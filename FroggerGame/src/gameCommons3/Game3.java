package gameCommons3;

import java.awt.Color;
import java.net.URL;
import java.util.Random;

import gameCommons.Chrono;
import gameCommons.Music;
import graphicalElements.Element;
import graphicalElements3.IFroggerGraphics3;

public class Game3 {

    public final Random randomGen = new Random();

    // Caracteristique de la partie
    public final int width;
    public int height;
    public final int minSpeedInTimerLoops;
    public final double defaultDensity;
    public int score = 0;
    public int scoreMax = 0;
    private Chrono chrono;
    Music music = new Music();
    Music musicWater = new Music();
    Music musicCar = new Music();
    // Lien aux objets utilisés
    private IEnvironment3 environment;
    private IFrog3 frog;
    private IFroggerGraphics3 graphic;
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
    public Game3(IFroggerGraphics3 graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
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
    public void setFrog(IFrog3 frog) {
        this.frog = frog;
    }

    //public EnvInf getEnvironnement(){
    //    return (EnvInf) this.environment;
    //}

    public IFrog3 getFrog(){
        return this.frog;
    }
    /**
     * Lie l'objet environment a la partie
     *
     * @param environment
     */
    public void setEnvironment(IEnvironment3 environment) {
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
    public IFroggerGraphics3 getGraphic() {
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
            this.chrono.stop();
            frog.setLife(false);
            jeuEnCour = false;
            this.music.stopSound();
            URL test = getClass().getResource("/Music/SoundCar.wav");
            this.musicCar.playSound(test);
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
            jeuEnCour = false;
            this.music.stopSound();
            URL test = getClass().getResource("/Music/WaterSplash.wav");
            this.musicWater.playSound(test);
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
        jeuEnCour = false;
        this.music.stopSound();
        URL test = getClass().getResource("/Music/WaterSplash.wav");
        this.musicWater.playSound(test);
        this.graphic.endGameScreen("<html><center> You lose ! </center><br> <center>Votre temps de jeu est de " + this.chrono.getDureeSec() + " secondes </center><br> <center>Votre score est de " + this.scoreMax + " points</center></html>");
    }

    public IEnvironment3 getEnvironment() {
        return environment;
    }

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
        if(jeuEnCour) {
            if (this.graphic.getJeuEnCour() == false) {
                testLose();
                for (int i = 0; i < this.getEnvironment().getLane().size(); i++) {
                    if (this.getFrog().getPosition().ord == this.getEnvironment().getLane().get(i).getOrd() && this.getEnvironment().getLane().get(i).getCar() == false) {
                        if (this.getEnvironment().getLane().get(i).getDensity() > 0.0) {
                            if (this.getFrog().estPasDansLaGrille()) {
                                testLosess();
                            } else {
                                testLoses();
                            }
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
