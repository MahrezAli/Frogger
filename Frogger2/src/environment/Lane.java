package environment;

import java.util.ArrayList;
import java.util.Random;

import util.Case;
import gameCommons.Game;

public class Lane {
    private Game game;
    private int ord;
    private int speed;
    private ArrayList<Car> cars;
    private int leftToRight;
    private double density;
    private int tick = 0;

    public Lane(Game game, int ordonne, double density) {
        this.game = game;
        this.ord = ordonne;
        this.speed = genererInt(1,3);
        this.cars = new ArrayList();
        this.leftToRight = genererInt(0,2);
        this.density = density;
    }

    public Lane(Game game, int ord) {
        this(game, ord, game.defaultDensity);

    }

    public int genererInt(int borneInf, int borneSup){
        Random random = new Random();
        int nb;
        nb = borneInf + random.nextInt(borneSup - borneInf);
        return nb;

    }

    public void update() {
        this.tick++;
        if (this.tick < this.speed) {
            this.bougerVoiture(false);
        }else {
            this.bougerVoiture(true);
            this.enleveVoiture();
            this.mayAddCar();
            this.tick = 0;
        }
        // Toutes les voitures se déplacent d'une case au bout d'un nombre "tic
        // d'horloge" égal à leur vitesse
        // Notez que cette méthode est appelée à chaque tic d'horloge

        // Les voitures doivent etre ajoutes a l interface graphique meme quand
        // elle ne bougent pas

        // A chaque tic d'horloge, une voiture peut être ajoutée
    }

    private void bougerVoiture(boolean x){
        for(int i = 0; i < this.cars.size(); i++){
            this.cars.get(i).deplacer(x);
        }
    }

    private void enleveVoiture(){
        for(int i = 0; i < this.cars.size(); i++){
            if(this.cars.get(i).estPasDansLaGrille()){
                this.cars.remove(this.cars.get(i));
            }
        }
    }

    /*
     * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase()
     */

    public boolean isSafe(Case c){
        for(int i = 0; i < this.cars.size(); i++){
            if(this.cars.get(i).estDansLaCase(c)){
                return false;
            }
        }
        return true;
    }

    /**
     * Ajoute une voiture au début de la voie avec probabilité égale à la
     * densité, si la première case de la voie est vide
     */
    private void mayAddCar() {
        if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
            if (game.randomGen.nextDouble() < density) {
                cars.add(new Car(game, getBeforeFirstCase(), this.leftToRight));
            }
        }
    }

    private Case getFirstCase() {
        if (leftToRight == 1) {
            return new Case(0, ord);
        } else
            return new Case(game.width - 1, ord);
    }

    private Case getBeforeFirstCase() {
        if (leftToRight == 1) {
            return new Case(-1, ord);
        } else
            return new Case(game.width, ord);
    }

}
