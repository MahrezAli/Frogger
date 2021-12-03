package gameCommonsInf3;

import java.util.ArrayList;

import environment2.Lane2;
import environment3.Lane3;
import util.Case;
import gameCommons3.Game3;
import gameCommons3.IEnvironment3;

public class EnvInf3 implements IEnvironment3 {
    private ArrayList<Lane3> lane;
    private Game3 game;

    public EnvInf3(Game3 game){
        this.game = game;
        this.lane = new ArrayList<>();
        this.lane.add(new Lane3(game, 0, 0, true)); //cree la premiere ligne sans voiture
        for(int i = 1; i < game.height  ; ++i) {
            if (i % 5 == 0) {
                this.lane.add(new Lane3(game, i, 0, true));
            } else {
                boolean x = getRandomBoolean();
                if(x == true) {
                    this.lane.add(new Lane3(game, i, x));// crée les lignes intermediaires avec des voiture
                }else{
                    this.lane.add(new Lane3(this.game, i, 0.15 - game.defaultDensity, x));// crée les lignes intermediaires avec des voiture

                }
            }
        }
    }
    public Lane3 getLane(int x) {
        return this.lane.get(x);
    }

    public ArrayList<Lane3> getLane() {
        return lane;
    }

    public void ajouterLaDerniereLigne(){
        if(this.lane.size() % 4 == 0){
            this.lane.add(new Lane3(game, this.lane.size(),0, getRandomBoolean()));
        }else {
            boolean x = getRandomBoolean();
            if(x == true) {
                this.lane.add(new Lane3(game, this.lane.size(), x));
            }else{
                this.lane.add(new Lane3(this.game, this.lane.size(), 0.28 - game.defaultDensity, x));

            }
        }
    }
    public boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }
    public boolean isSafe(Case c) {
        return (this.lane.get(c.ord)).isSafe(c);
    }
    public boolean isSafef(Case c) { // safe pour la grenouille
        return (this.lane.get(c.ord)).isSafeForFrog(c);
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
