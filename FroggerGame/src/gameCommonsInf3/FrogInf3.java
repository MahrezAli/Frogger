package gameCommonsInf3;

import gameCommons.Music;
import gameCommons3.Game3;
import gameCommons3.IFrog3;
import util.Case;
import util.Direction;


import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FrogInf3 implements IFrog3 {
    private Game3 game;
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


    public FrogInf3(Game3 game){
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
        URL test = getClass().getResource("/Music/JumpFrog.wav");
        this.direction = key;

        if (key == Direction.up && this.cases.ord + 1 < this.game.height && estEnVie) {
            this.cases = new Case(this.cases.absc, this.cases.ord + 1);
            music.playSound(test);
            this.game.score++;
            if (this.game.score >= 3) {
                if (this.game.score > this.game.scoreMax) {
                    this.game.scoreMax = this.game.score;
                    this.game.ajouterLaDerniereLigne();
                    this.game.height++;
                }
            } else {
                this.game.scoreMax = this.game.score;
            }

        }
        if (key == Direction.down && this.cases.ord - 1 >= 0 && estEnVie) {
            this.cases = new Case(this.cases.absc, this.cases.ord - 1);
            music.playSound(test);
            this.game.score--;
            this.game.height--;
            bas = 0;

        }
        if (key == Direction.left && this.cases.absc - 1 >= 0 && estEnVie) {
            this.cases = new Case(this.cases.absc - 1, this.cases.ord);
            music.playSound(test);
            gauche = 0;

        }
        if (key == Direction.right && this.cases.absc + 1 < this.game.width && estEnVie) {
            this.cases = new Case(this.cases.absc + 1, this.cases.ord);
            music.playSound(test);
            droite = 0;

        }
    }
}

