package environment;

import gameCommons.Game;
import gameCommons2.Game2;
import gameCommons3.Game3;
import graphicalElements.Element;
import util.Case;

import java.awt.*;
import java.util.Random;

public class Raft {
    private Game game;
    private Game2 game2;
    private Game3 game3;
    private Case leftPosition;
    private int leftToRight;
    private int length;
    private  final Color colorLtR = Color.PINK;
    private  final Color colorRtL = Color.RED;
    private  final Color colorRtL2 = new Color(0xA6115C);

    public Raft(Game game, Case position, int leftToRight){
        this.game = game;
        this.leftPosition = new Case(position.absc - this.length, position.ord);
        this.length =  genererInt(2, 4);
        this.leftToRight = leftToRight;
    }

    public Raft(Game2 game2, Case position, int leftToRight){
        this.game2 = game2;
        this.leftPosition = new Case(position.absc - this.length, position.ord);
        this.length =  genererInt(2, 4);
        this.leftToRight = leftToRight;
    }

    public Raft(Game3 game3, Case position, int leftToRight){
        this.game3 = game3;
        this.leftPosition = new Case(position.absc - this.length, position.ord);
        this.length =  genererInt(2, 4);
        this.leftToRight = leftToRight;
    }

    public int genererInt(int borneInf, int borneSup){
        Random random = new Random();
        int nb;
        nb = borneInf + random.nextInt(borneSup - borneInf);
        return nb;
    }

    public void deplacer(boolean x){
        if(x) {
            if(this.leftToRight == 1){
                this.leftPosition = new Case(leftPosition.absc + 1, leftPosition.ord);
            }if(this.leftToRight == 0){
                this.leftPosition = new Case(leftPosition.absc - 1, leftPosition.ord);
            }
        }
        if(this.leftToRight == 1){
            this.addToGraphicRedFor3();
        }
        if(this.leftToRight == 0){
            this.addToGraphicPinkFor3();
        }
    }

    public boolean estPasDansLaGrille() {
        if(this.leftPosition.absc + this.length <= 0 || this.leftPosition.absc >= this.game.width ){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean estPasDansLaGrille2() {
        if(this.leftPosition.absc + this.length <= 0 || this.leftPosition.absc >= this.game2.width ){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean estPasDansLaGrille3() {
        if(this.leftPosition.absc + this.length <= 0 || this.leftPosition.absc >= this.game3.width ){
            return true;
        }
        else{
            return false;
        }
    }


    public boolean estDansLaCase(Case c){ // gere la mise en case de la buche rouge
        if(c.ord != this.leftPosition.ord){ // si 'c' n'a pas le même ordonée que celle du radeau
            return false;
        }
        else{
            if(c.absc < this.leftPosition.absc){ // si la case de 'c' est avant celle du radeau
                return false;
            }
            else{
                if(c.absc > this.leftPosition.absc + this.length){ //si la case 'c' est devant le radeau + sa longueur
                    return false;
                }
            }
        }
        return true;
    }

    public boolean estDansLaCases(Case c){ // gere la mise en case de la buche rose
        if(c.ord != this.leftPosition.ord){ // si 'c' n'a pas le même ordonée que celle du radeau
            return false;
        }
        else{
            if(c.absc < this.leftPosition.absc -1){ // si la case de 'c' est avant celle du radeau
                return false;
            }
            else{
                if(c.absc > this.leftPosition.absc + this.length){ //si la case 'c' est devant le radeau + sa longueur
                    return false;
                }
            }
        }
        return true;
    }

    // Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la buche
    private void addToGraphicRedFor3() {// affichage buche rose
        for (int i = 0; i <= length; i++) {
            Color color = colorLtR;
            if(this.game != null){
                game.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord, color));
            }
            if(this.game2 != null){
                game2.getGraphic().add(new Element(leftPosition.absc + i , leftPosition.ord, color));
            }
            if(this.game3 != null) {
                if (this.game3.score < 3) {
                    game3.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord, color));
                } else {
                    game3.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord - this.game3.score + 3, color));
                }
            }
        }
    }
    private void addToGraphicPinkFor3() { // affichage buche rouge
        Color color = null;
        if(this.length == 2){
            color = colorRtL;
        }
        else if(this.length == 3){
            color = colorRtL2;
        }
        if(this.game != null){
            game.getGraphic().add(new Element(leftPosition.absc , leftPosition.ord, color));
        }
        if(this.game2 != null){
            game2.getGraphic().add(new Element(leftPosition.absc , leftPosition.ord, color));
        }
        if(this.game3 != null){
            if(this.game3.score < 3){
                game3.getGraphic().add(new Element(leftPosition.absc , leftPosition.ord, color));
            }else{
                game3.getGraphic().add(new Element(leftPosition.absc , leftPosition.ord - this.game3.score + 3, color));
            }
        }
    }




}
