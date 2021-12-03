package obstacles;

import gameCommons.Game;
import gameCommons2.Game2;
import graphicalElements.Element;
import util.Case;

import java.awt.*;

public class ToileAraignee {
    private Game game;
    private Game2 game2;
    private Case position;
    private final Color colorA = Color.WHITE;

    public ToileAraignee(Game game, Case position){
        this.game = game;
        this.position = position;
    }

    public ToileAraignee(Game2 game, Case position){
        this.game2 = game;
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
        if(this.game != null){
            game.getGraphic().add(new Element(this.position.absc , this.position.ord, color));
        }
        if(this.game2 != null){
            game2.getGraphic().add(new Element(this.position.absc , this.position.ord, color));
        }    }


    public Case getPosition(){
        return this.position;
    }




}
