package environment;

import gameCommons.Game;
import graphicalElements.Element;
import obstacles.Arbre;
import obstacles.Coin;
import obstacles.ToileAraignee;
import util.Case;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Lane{
    private Game game;
    private int ord;
    private int speed;
    private ArrayList<Car> cars;
    private ArrayList<Raft> rafts;
    private int leftToRight;
    private double density;
    private int tick = 0;
    private ArrayList<Arbre> arbres;
    private ArrayList<ToileAraignee> toile;
    private ArrayList<Coin> coin;
    private boolean car;
    //private Color c;

    public Lane(Game game, int ordonne, double density, boolean car) {//constructeur de la route avec une densite speciale
        this.game = game;
        this.ord = ordonne;
        this.speed = genererInt(1,3);
        this.leftToRight = genererInt(0,2);
        this.density = density;
        this.arbres = new ArrayList<Arbre>();
        this.car = car;
        this.coin = new ArrayList<Coin>();
        this.toile = new ArrayList<ToileAraignee>();
        this.cars = new ArrayList<Car>();
        this.rafts = new ArrayList<Raft>();
    }

    public Lane(Game game, int ord, boolean x) {//constructeur de la route avec une densité par defaut
        this(game, ord, game.defaultDensity, x);

    }

    public boolean getCar() {
        return car;
    }

    public void isInRaft(boolean x){
        for(int i = 0; i < this.rafts.size(); i++){
            if(this.leftToRight == 0) {//rouge
                if (this.rafts.get(i).estDansLaCase(this.game.getFrog().getPosition())) {
                    this.game.getFrog().setLeftToRight(this.leftToRight);
                    this.game.getFrog().deplacer(x);
                }
            }
            if(this.leftToRight == 1) {//rose
                if (this.rafts.get(i).estDansLaCases(this.game.getFrog().getPosition())) {
                    this.game.getFrog().setLeftToRight(this.leftToRight);
                    this.game.getFrog().deplacer(x);
                }
            }
        }
    }

    public double getDensity() {
        return density;
    }

    public int getOrd() {
        return ord;
    }

    private void addToGraphics(Color x) {
        for (int i = 0; i < this.game.width; i++) {
            Color color = x;
            game.getGraphic().add(new Element(i, this.ord, x));
        }
    }

    public int genererInt(int borneInf, int borneSup){
        Random random = new Random();
        int nb;
        nb = borneInf + random.nextInt(borneSup - borneInf);
        return nb;

    }

    public void update() {
        this.tick++;
        if(this.car == true) {
            this.addToGraphics(new Color(0x9A9A19));
            if (this.coin.size() > 0) {
                for (int i = 0; i < this.coin.size(); i++) {
                    this.coin.get(i).addToGraphics();
                }
            }
            if (this.toile.size() > 0) {
                for (int i = 0; i < this.toile.size(); i++) {
                    this.toile.get(i).addToGraphics();
                }
            }
            if (this.tick < this.speed) {
                this.bougerVoiture(false);
            } else {
                this.bougerVoiture(true);
                this.enleveVoiture();
                this.mayAddCar();
                this.tick = 0;
            }
        }
        else{
            this.addToGraphics(Color.BLUE);
            if (this.tick < this.speed) {
                this.bougerRaft(false);
                this.isInRaft(false);
            } else {
                this.bougerRaft(true);
                this.isInRaft(true);
                this.enleveRaft();
                this.mayAddRaft();
                this.tick = 0;
            }
        }
        if(this.density == 0){
            this.addToGraphics(Color.LIGHT_GRAY);
            this.addArbre();
            for(int i = 0; i < this.arbres.size(); i++) {
                if (this.arbres.size() > 0) {
                    this.arbres.get(i).addToGraphics();
                }
            }
        }
    }
    private void bougerRaft(boolean x){
        for(int i = 0; i < this.rafts.size(); i++){
            this.rafts.get(i).deplacer(x);
        }
    }

    private void enleveRaft(){
        for(int i = 0; i < this.rafts.size(); i++){
            if(this.rafts.get(i).estPasDansLaGrille()){
                this.rafts.remove(this.rafts.get(i));
            }
        }
    }
    private void bougerVoiture(boolean x){ //faire bouger la voiture dans la route
        for(int i = 0; i < this.cars.size(); i++){
            this.cars.get(i).deplacer(x);
        }
    }

    private void enleveVoiture(){ //enlever la voiture du tableau de voiture si elle n'est plus dans la grille
        for(int i = 0; i < this.cars.size(); i++){
            if(this.cars.get(i).estPasDansLaGrille()){
                this.cars.remove(this.cars.get(i));
            }
        }
    }

    public boolean isSafe(Case c){//si la case 'c' de la route est safe pour la grenouille
        for(int i = 0; i < this.cars.size(); i++){
            if(this.cars.get(i).estDansLaCase(c)){
                return false;
            }
        }
        return true;
    }

    public boolean isInCase(Case c){ // si l'arbre est dans la case
        for(int i = 0; i < this.arbres.size(); i++){
            if(this.arbres.get(i).estDansLaCase(c)){
                //System.out.println("dans la case");
                return true;
            }
        }
        //System.out.println("pas dans la case");
        return false;
    }

    public boolean positionToile(){// si la grenouille est dans la toile
        if(this.car == true) {
            if (this.toile.size() > 0) {
                if (this.toile.get(0).estDansLaCase(this.game.getFrog().getPosition())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean positionCoin(){// si la grenouille est dans la piece
        if(this.coin.size() > 0){
            if(this.coin.get(0).estDansLaCase(this.game.getFrog().getPosition())){
                return true;
            };
        }
        return false;
    }

    public boolean contientObjet(Case c){ //methode qui retourne un bouleen pour savoir si la case 'c' dans la route contient un arbre ou une toile
        if(this.toile.size() > 0 || this.arbres.size() > 0){
            if(this.toile.size() > 0){
                if(this.toile.get(0).estDansLaCase(c)){
                    return true;
                }
            }
            if(this.arbres.size() > 0){
                for(int i = 0 ; i < this.arbres.size(); i++){
                    if(this.arbres.get(i).estDansLaCase(c)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void addArbre() { //methode pour rajouter un arbre dans la route (si elle est safe)
        if(this.arbres.size() < 4 && this.ord > 0) {
            arbres.add(new Arbre(game, new Case(this.genererInt(0, this.game.width), this.ord)));
        }
    }

    public void addToile() { //methode pour rajouter une toile dans la route
        if(this.toile.size() < 1) {
            toile.add(new ToileAraignee(this.game, new Case(this.genererInt(0, this.game.width), this.ord)));
        }
    }

    public void addCoin() { //methode pour rajouter une piece dans la route
        while(this.coin.size() < 1) {
            Case c = new Case(this.genererInt(0, this.game.width), this.ord);
            if(!this.contientObjet(c)) {
                this.coin.add(new Coin(this.game, c));
            }
        }
    }

    public void removeCoin() { // methode pour enlever la piece de la route
        if(this.coin.size() > 0){
            this.coin.remove(this.coin.get(0));
        }
    }
    public boolean isSafeForRafts(Case c){
        for(int i = 0; i < this.rafts.size(); i++){
            if(this.rafts.get(i).estDansLaCases(c)){
                return false;
            }
        }
        return true;
    }

    public boolean isSafeForFrog(Case c){
        for(int i = 0; i < this.rafts.size(); i++){
            if(this.rafts.get(i).estDansLaCase(c)){
                return true;
            }
        }
        return false;
    }

    /*
     * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase()
     */
    /**
     * Ajoute une voiture au début de la voie avec probabilité égale à la
     * densité, si la première case de la voie est vide
     */
    private void mayAddRaft() {
        if (isSafeForRafts(getFirstCase()) && isSafeForRafts(getBeforeFirstCase())) {
            if (game.randomGen.nextDouble() < density) {
                rafts.add(new Raft(game, getBeforeFirstCase(), this.leftToRight));
            }
        }
    }
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
