package obstacles;

import gameCommons.Game;
import graphicalElements.Element;
import util.Case;

import java.awt.*;

public class Coin{
    private Game game;
    private Case position;
    private final Color colorA = Color.ORANGE;

    public Coin(Game game, Case position){
        this.game = game;
        this.position = position;
    }

    public boolean estDansLaCase(Case c){
        if(c.ord != this.position.ord){
            return false;
        }
        else{
            if(c.absc < this.position.absc){
                return false;
            }
            else{
                if(c.absc > this.position.absc ){
                    return false;
                }
            }
        }
        return true;
    }

    public void addToGraphics() {
        Color color = colorA;
        game.getGraphic().add(new Element(this.position.absc , this.position.ord, color));
    }

    public void removeToGraphics() {
        Color color = colorA;
        game.getGraphic().remove(new Element(this.position.absc , this.position.ord, color));
    }

    public Case getPosition(){
        return this.position;
    }




}
