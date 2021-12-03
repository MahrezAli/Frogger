package gameCommons2;


import gameCommons.Chrono;
import gameCommons.Music;
import graphicalElements.Element;
import graphicalElements2.IFroggerGraphics2;
import util.Case;

import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Game2 {

    public final Random randomGen = new Random();

    // Caracteristique de la partie
    public final int width;
    public final int height;
    public final int minSpeedInTimerLoops;
    public final double defaultDensity;

    // Lien aux objets utilisés
    private IEnvironment2 environment;
    private IFrogGreen frog;
    private IFrogBlue frog2;
    private IFroggerGraphics2 graphic;
    private Chrono chrono;
    Music music = new Music();
    Music musicWater = new Music();
    Music musicCar = new Music();
    public int score = 0;
    public int scoreMax = 0;
    public int score2 = 0;
    public int scoreMax2 = 0;
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
    public Game2(IFroggerGraphics2 graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
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
    public void setFrog(IFrogGreen frog) {
        this.frog = frog;
    }
    public void setFrog2(IFrogBlue frog2) {
        this.frog2 = frog2;
    }
    /**
     * Lie l'objet environment a la partie
     *
     * @param environment
     */
    public void setEnvironment(IEnvironment2 environment) {
        this.environment = environment;
    }

    /**
     *
     * @return l'interface graphique
     */
    public IFroggerGraphics2 getGraphic() {
        return graphic;
    }

    public IFrogGreen getFrog() {
        return frog;
    }
    public IFrogBlue getFrog2() {
        return frog2;
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
            frog2.setLife(false);
            jeuEnCour = false;
            this.music.stopSound();
            URL test = getClass().getResource("/Music/SoundCar.wav");
            this.musicCar.playSound(test);
            this.graphic.endGameScreen("<html><center><font color='green'>Frog vert </font>, You lose ! </center><br> <center>Votre temps de jeu est de " + this.chrono.getDureeSec() + " secondes </center><br> <center>Votre score est de " + this.scoreMax + " points</center><br><center>  <font color='blue'>Frog bleu </font>, Votre score est de " + this.scoreMax2 + " points</center> </html>");
            this.frog.setCases(new Case(0,0));
            this.frog2.setCases(new Case(0,0));
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
            frog2.setLife(false);
            jeuEnCour = false;
            this.music.stopSound();
            URL test = getClass().getResource("/Music/WaterSplash.wav");
            this.musicWater.playSound(test);
            this.graphic.endGameScreen("<html><center> <font color='green'>Frog vert </font>, lose ! </center><br> <center>Votre temps de jeu est de " + this.chrono.getDureeSec() + " secondes </center><br> <center>Votre score est de " + this.scoreMax+ " points</center><br><center>  <font color='blue'>Frog bleu </font>, Votre score est de " + this.scoreMax2 + " points</center> </html>");
            this.frog.setCases(new Case(0,0));
            this.frog2.setCases(new Case(0,0));
            return true;
        }
        else{
            return false;
        }
    }
    public boolean testLoses2() {
        if(!this.environment.isSafef(this.frog2.getPosition())){
            this.chrono.stop();
            frog.setLife(false);
            frog2.setLife(false);
            jeuEnCour = false;
            this.music.stopSound();
            URL test = getClass().getResource("/Music/WaterSplash.wav");
            this.musicWater.playSound(test);
            this.graphic.endGameScreen("<html><center> <font color='blue'>Frog bleu </font>, You lose ! </center><br> <center>Votre temps de jeu est de " + this.chrono.getDureeSec() + " secondes </center><br> <center>Votre score est de " + this.scoreMax2 + " points</center><br><center> <font color='green'>Frog vert </font>, Votre score est de " + this.scoreMax + " points</center> </html>");
            this.frog.setCases(new Case(0,0));
            this.frog2.setCases(new Case(0,0));
            return true;
        }
        else{
            return false;
        }
    }
    public boolean testLose2() {
        if(!this.environment.isSafe(this.frog2.getPosition())){
            this.chrono.stop();
            frog.setLife(false);
            frog2.setLife(false);
            jeuEnCour = false;
            this.music.stopSound();
            URL test = getClass().getResource("/Music/SoundCar.wav");
            this.musicCar.playSound(test);
            this.graphic.endGameScreen("<html><center><font color='blue'>Frog bleu </font>, You lose !  </center><br> <center>Votre temps de jeu est de " + this.chrono.getDureeSec() + " secondes </center><br> <center>Votre score est de " + this.scoreMax2 + " points</center><br><center> <font color='green'>Frog vert </font>, Votre score est de " + this.scoreMax + " points</center> </html>");
            this.frog.setCases(new Case(0,0));
            this.frog2.setCases(new Case(0,0));
            return true;
        }
        else{
            return false;
        }
    }

    public void testLosess() {
        this.chrono.stop();
        frog.setLife(false);
        frog2.setLife(false);
        jeuEnCour = false;
        this.music.stopSound();
        URL test = getClass().getResource("/Music/WaterSplash.wav");
        this.musicWater.playSound(test);
        this.graphic.endGameScreen("<html><center> <font color='green'>Frog vert </font>, lose ! </center><br> <center>Votre temps de jeu est de " + this.chrono.getDureeSec() + " secondes </center><br> <center>Votre score est de " + this.scoreMax + " points</center><br><center>  <font color='blue'>Frog bleu </font>, Votre score est de " + this.scoreMax2 + " points</center> </html>");
        this.frog.setCases(new Case(0,0));
        this.frog2.setCases(new Case(0,0));
    }
    public void testLosess2() {
        this.chrono.stop();
        frog.setLife(false);
        frog2.setLife(false);
        jeuEnCour = false;
        this.music.stopSound();
        URL test = getClass().getResource("/Music/WaterSplash.wav");
        this.musicWater.playSound(test);
        this.graphic.endGameScreen("<html><center><font color='blue'>Frog bleu </font>, lose ! </center><br> <center>Votre temps de jeu est de " + this.chrono.getDureeSec() + " secondes </center><br> <center>Votre score est de " + this.scoreMax2 + " points</center><br><center>  <font color='green'>Frog vert </font>, Votre score est de " + this.scoreMax + " points</center> </html>");
        this.frog.setCases(new Case(0,0));
        this.frog2.setCases(new Case(0,0));
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
            frog2.setLife(false);
            jeuEnCour = false;
            this.music.stopSound();
            this.graphic.endGameScreen("<html><center> <font color='green'>Frog vert </font>, You win ! </center><br> <center>Votre temps de jeu est de " + this.chrono.getDureeSec() + " secondes </center><br> <center>Votre score est de " + this.scoreMax + " points</center><br><center>  <font color='blue'>Frog bleu </font>, Votre score est de " + this.scoreMax2 + " points</center> </html>");
            this.frog.setCases(new Case(0,0));
            this.frog2.setCases(new Case(0,0));
            return true;
        }
        else{
            return false;
        }
    }

    public IEnvironment2 getEnvironment() {
        return environment;
    }

    public boolean testWin2() {
        if(this.environment.isWinningPosition(this.frog2.getPosition())){
            this.chrono.stop();
            frog.setLife(false);
            frog2.setLife(false);
            jeuEnCour = false;
            this.music.stopSound();
            this.graphic.endGameScreen("<html><center> <font color='blue'>Frog bleu </font>, You win ! </center><br> <center>Votre temps de jeu est de " + this.chrono.getDureeSec() + " secondes </center><br> <center>Votre score est de " + this.scoreMax2 + " points</center><br><center>  <font color='green'>Frog vert </font>, Votre score est de " + this.scoreMax + " points</center> </html>");
            this.frog.setCases(new Case(0,0));
            this.frog2.setCases(new Case(0,0));
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Actualise l'environnement, affiche la grenouille et verifie la fin de
     * partie.
     */
    public void update() {
        graphic.clear();
        environment.update();
        this.graphic.add(new Element(frog.getPosition(), Color.GREEN));
        this.graphic.add(new Element(frog2.getPosition(), new Color(0x20208A)));
        if(jeuEnCour) {
            if (this.graphic.getJeuEnCour() == false) {
                testLose();
                testWin();
                testLose2();
                testWin2();
                for (int i = 0; i < this.getEnvironment().getLane().size(); i++) {
                    if (this.getFrog().getPosition().ord == this.getEnvironment().getLane().get(i).getOrd() && this.getEnvironment().getLane().get(i).getCar() == false) {
                        if (this.getEnvironment().getLane().get(i).getDensity() > 0.0) { // test lose dans la riviere avec raft
                            if (this.getFrog().estPasDansLaGrille()) {
                                testLosess();
                            } else {
                                testLoses();
                            }
                        }
                        if (this.getEnvironment().getLane().get(i).getDensity() == 0.0) {// test lose dans la riviere sans raft
                            testLose();
                        }
                    }
                }
                for (int i = 0; i < this.getEnvironment().getLane().size(); i++) {
                    if (this.getFrog2().getPosition().ord == this.getEnvironment().getLane().get(i).getOrd() && this.getEnvironment().getLane().get(i).getCar() == false) {
                        if (this.getEnvironment().getLane().get(i).getDensity() > 0.0) {
                            if (this.getFrog2().estPasDansLaGrille()) {
                                testLosess2();
                            } else {
                                testLoses2();
                            }
                        }
                        if (this.getEnvironment().getLane().get(i).getDensity() == 0.0) {
                            testLose2();
                        }
                    }
                }
            }
        }
    }
}
