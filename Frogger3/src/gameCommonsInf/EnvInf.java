package gameCommonsInf;

import java.util.ArrayList;

import environment.Lane;
import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class EnvInf implements IEnvironment {
    private ArrayList<Lane> lane;
    private Game game;

    public EnvInf(Game game){
        this.game = game;
        this.lane = new ArrayList<Lane>();
        this.lane.add(new Lane(game, 0, 0)); //cree la premiere ligne sans voiture
        for(int i = 1; i < game.height  ; ++i) {
            this.lane.add(new Lane(game, i));// crée les lignes intermediaires avec des voiture
        }
    }

    public void ajouterLaDerniereLigne(){
        this.lane.add(new Lane(game, this.lane.size()));
    }

    public boolean isSafe(Case c) {
        return (this.lane.get(c.ord)).isSafe(c);
    }

    public boolean isWinningPosition(Case c){
        if(c.ord == this.game.height -1){
            return true;
        }else{
            return false;
        }
    }

    public void prendLaPlace(){
        for(int i = 0; i < this.lane.size(); i++){
            this.lane.set(i, this.lane.get(i+1));
        }
        //this.update();
    }

    public void update(){
        for(int i = 0; i < this.lane.size(); i++){
            this.lane.get(i).update();
        }
    }

}
