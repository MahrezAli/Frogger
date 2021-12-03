package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import gameCommons.IFrog2;
import util.Case;
import util.Direction;
import util.Direction2;

import javax.swing.*;
import java.awt.*;

public class Frog2 implements IFrog2 {
    private Game game;
    private Direction2 direction;
    private Case cases;
    int droite = 0;
    int gauche = 0;
    int haut = 0;
    int bas = 0;

    private int leftToRight;


    public Frog2(Game game){
        this.game = game;
        this.direction  = Direction2.Z;
        this.cases = new Case(this.game.width / 2 - 3,0);

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

    public Direction2 getDirection() {
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

    public void move(Direction2 key){

        this.direction = key;

        if (key == Direction2.Z && this.cases.ord + 1 < this.game.height && !this.game.getEnvironment().getLane(this.cases.ord + 1).isInCase(new Case(this.cases.absc, this.cases.ord + 1))) {
            if(this.game.getEnvironment().frogSurLaToile() && haut < 3){
                haut++;
            }else {
                this.cases = new Case(this.cases.absc, this.cases.ord + 1);
                this.game.score2++;
                if( this.game.scoreMax2 <= this.game.score2){
                    this.game.scoreMax2 = this.game.score2;
                }
                haut = 0;
            }
        }
        if (key == Direction2.S && this.cases.ord - 1 >= 0 && !this.game.getEnvironment().getLane(this.cases.ord - 1).isInCase(new Case(this.cases.absc, this.cases.ord - 1))) {
            if(this.game.getEnvironment().frogSurLaToile() && bas < 3){
                bas++;
            }else {
                this.cases = new Case(this.cases.absc, this.cases.ord - 1);
                this.game.score2--;
                bas = 0;
            }
        }
        if (key == Direction2.Q && this.cases.absc - 1 >= 0 && !this.game.getEnvironment().getLane(this.cases.ord).isInCase(new Case(this.cases.absc - 1, this.cases.ord))) {
            if(this.game.getEnvironment().frogSurLaToile() && gauche < 3){
                gauche++;
            }else {
                this.cases = new Case(this.cases.absc - 1, this.cases.ord);
                gauche = 0;
            }
        }
        if (key == Direction2.D && this.cases.absc + 1 < this.game.width && !this.game.getEnvironment().getLane(this.cases.ord).isInCase(new Case(this.cases.absc + 1, this.cases.ord))) {
            if(this.game.getEnvironment().frogSurLaToile() && droite < 3){
                droite++;
            }else {
                this.cases = new Case(this.cases.absc + 1, this.cases.ord);
                droite = 0;
            }
        }
        this.game.getEnvironment().frogSurLeCoin2();
    }
}

