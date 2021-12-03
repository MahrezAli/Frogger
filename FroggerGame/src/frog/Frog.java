package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import gameCommons.Music;
import util.Case;
import util.Direction;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Frog implements IFrog {
    private Game game;
    private Direction direction;
    private Case cases;
    private boolean estEnVie;
    private boolean restart;
    int droite = 0;
    int gauche = 0;
    int haut = 0;
    int bas = 0;
    private Music music = new Music();
    private int leftToRight;


    public Frog(Game game){
        this.game = game;
        this.direction  = Direction.up;
        this.cases = new Case(this.game.width / 2,0);
        this.estEnVie = true;
    }

    public void setRestart(boolean b){
        restart = b;
    }

    public boolean getRestart(){
        return restart;
    }
    public boolean getLife(){
        return estEnVie;
    }

    public void setLife(Boolean b){
        this.estEnVie = b;
    }
    public void setLeftToRight(int leftToRight) {
        this.leftToRight = leftToRight;
    }

    public void deplacer(boolean x){
        if(x) {
            if(this.leftToRight == 1){
                this.cases = new Case(cases.absc + 1, cases.ord);
            }if(this.leftToRight == 0){
                this.cases = new Case(cases.absc - 1, cases.ord);
            }
        }
    }

    public boolean estPasDansLaGrille() {
        if(this.cases.absc < 0 || this.cases.absc >= this.game.width){
            return true;
        }
        else{
            return false;
        }
    }

    public void setCases(Case cases) {
        this.cases = cases;
    }

    public Case getPosition() {
        return this.cases;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public boolean estDansLaCase(Case c){
        if(c.ord != this.cases.ord ){
            return false;
        }
        if(c.absc < this.cases.absc){ // si la case de 'c' est avant celle du radeau
            return false;
        }
        else{
            if(c.absc >= this.cases.absc){ //si la case 'c' est devant le radeau
                return false;
            }
        }
        return true;
    }

    public void move(Direction key){

        this.direction = key;
        URL test = getClass().getResource("/Music/JumpFrog.wav");
        if (key == Direction.up && this.cases.ord + 1 < this.game.height && !this.game.getEnvironment().getLane(this.cases.ord + 1).isInCase(new Case(this.cases.absc, this.cases.ord + 1)) && estEnVie) {
            if(this.game.getEnvironment().frogSurLaToile() && haut < 3){
                haut++;
            }else {
                this.cases = new Case(this.cases.absc, this.cases.ord + 1);
                music.playSound(test);
                this.game.score++;
                if( this.game.scoreMax <= this.game.score){
                    this.game.scoreMax = this.game.score;
                }
                haut = 0;
            }
        }
        if (key == Direction.down && this.cases.ord - 1 >= 0 && !this.game.getEnvironment().getLane(this.cases.ord - 1).isInCase(new Case(this.cases.absc, this.cases.ord - 1)) && estEnVie) {
            if(this.game.getEnvironment().frogSurLaToile() && bas < 3){
                bas++;
            }else {
                this.cases = new Case(this.cases.absc, this.cases.ord - 1);
                music.playSound(test);
                this.game.score--;
                bas = 0;
            }
        }
        if (key == Direction.left && this.cases.absc - 1 >= 0 && !this.game.getEnvironment().getLane(this.cases.ord).isInCase(new Case(this.cases.absc - 1, this.cases.ord))) {
            if(this.game.getEnvironment().frogSurLaToile() && gauche < 3){
                gauche++;
            }else {
                this.cases = new Case(this.cases.absc - 1, this.cases.ord);
                music.playSound(test);
                gauche = 0;
            }
        }
        if (key == Direction.right && this.cases.absc + 1 < this.game.width && !this.game.getEnvironment().getLane(this.cases.ord).isInCase(new Case(this.cases.absc + 1, this.cases.ord))) {
            if(this.game.getEnvironment().frogSurLaToile() && droite < 3){
                droite++;
            }else {
                this.cases = new Case(this.cases.absc + 1, this.cases.ord);
                music.playSound(test);
                droite = 0;
            }
        }
        this.game.getEnvironment().frogSurLeCoin();
    }
}

