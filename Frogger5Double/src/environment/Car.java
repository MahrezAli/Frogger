package environment;

import gameCommons.Game;
import graphicalElements.Element;
import util.Case;

import java.awt.*;
import java.util.Random;

public class Car {
    private Game game;
    private Case leftPosition;
    private int leftToRight;
    private int length;
    private  final Color colorLtR = Color.BLACK; // noir 1
    private  final Color colorRtL = Color.DARK_GRAY;//gris 1
    private  final Color x = Color.MAGENTA;//vert 2
    private  final Color y = Color.YELLOW;//beige 2


    public Car(Game game, Case position, int leftToRight){
        this.game = game;
        this.leftPosition = new Case(position.absc - this.length, position.ord);
        this.length =  genererInt(1, 3);
       // this.length =  2;
        this.leftToRight = leftToRight;
    }

    public int genererInt(int borneInf, int borneSup){
        Random random = new Random();
        int nb;
        nb =  borneInf + random.nextInt(borneSup - borneInf);
        return nb;
    }

    public int getLength() {
        return length;
    }

    public void deplacer(boolean x){
        if(x) {
            if(this.leftToRight == 1){
                this.leftPosition = new Case(leftPosition.absc + 1, leftPosition.ord);
            }if(this.leftToRight == 0){
                this.leftPosition = new Case(leftPosition.absc - 1, leftPosition.ord);
            }
        }
        this.addToGraphics();
    }

    public boolean estPasDansLaGrille() {
        if(this.leftPosition.absc + this.length <= 0 || this.leftPosition.absc >= this.game.width){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean estDansLaCase(Case c){
        if(c.ord != this.leftPosition.ord){ // si 'c' n'a pas le même ordonée que celle de la voiture
            return false;
        }
        else{
            if(c.absc < this.leftPosition.absc){ // si la case de 'c' est avant celle de la voiture
                return false;
            }
            else{
                if(c.absc >= this.leftPosition.absc + this.length){ //si la case 'c' es devant la voiture + sa longueur
                    return false;
                }
            }
        }
        return true;
    }

    // Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture
    private void addToGraphics() {
        //for (int i = 0; i < length; i++) {
        Color color = null;
        if (this.leftToRight == 0){
            if(this.length == 1){
                color = colorRtL;
            }
            else if(this.length == 2){
                color = y;
            }
        }
         if (this.leftToRight == 1){
            if(this.length == 1){
                color = colorLtR;
            }
            else if(this.length == 2){
                color = x;
            }
        }
        game.getGraphic().add(new Element(leftPosition.absc , leftPosition.ord, color));
       // }
    }

}
