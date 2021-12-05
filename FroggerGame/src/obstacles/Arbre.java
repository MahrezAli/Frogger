package obstacles;

import gameCommons.Game;
import gameCommons2.Game2;
import graphicalElements.Element;
import util.Case;

import java.awt.*;

public class Arbre{
    private Game game;
    private Game2 game2;
    private Case position;
    //private  boolean appear;
    private final Color colorA = Color.CYAN;

    public Arbre(Game game, Case position){
        this.game = game;
        this.position = position;
    }

    public Arbre(Game2 game, Case position){
        this.game2 = game;
        this.position = position;
    }

    public boolean estDansLaCase(Case c){
        if(c.ord != this.position.ord){ // si 'c' n'a pas le même ordonée que celle de la voiture
            return false;
        }
        else{
            if(c.absc < this.position.absc){ // si la case de 'c' est avant celle de la voiture
                return false;
            }
            else{
                if(c.absc > this.position.absc ){ //si la case 'c' es devant la voiture + sa longueur
                    return false;
                }
            }
        }
        return true;
    }

    public void addToGraphics() {
        //for (int i = 0; i < 1; i++) {
        Color color = colorA;
        if(this.game != null){
            game.getGraphic().add(new Element(this.position.absc , this.position.ord, color));
        }
        if(this.game2 != null){
            game2.getGraphic().add(new Element(this.position.absc , this.position.ord, color));
        }        //}
    }





}
