package environment;


import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Case;

import java.util.ArrayList;
import java.util.Random;

public class Environment implements IEnvironment {
    private ArrayList<Lane> lane;
    private Game game;

    public Environment(Game game){
        this.game = game;
        this.lane = new ArrayList<Lane>();

        this.lane.add(new Lane(this.game, 0, 0,true)); //cree la premiere ligne sans voiture
        for(int i = 1; i < this.game.height -8 ; ++i) {
            if(i % 5 == 0){
                this.lane.add(new Lane(this.game, i, 0,true));
            }else {
                this.lane.add(new Lane(this.game, i,true));// crée les lignes intermediaires avec des voiture
            }
        }
        for(int i = this.game.height -8; i < this.game.height -1 ; ++i) {
            if(i % 5 == 0){
                this.lane.add(new Lane(this.game, i, 0,false));
            }else {
                this.lane.add(new Lane(this.game, i,false));// crée les lignes intermediaires avec des voiture
            }
        }
        this.lane.add(new Lane(this.game, this.game.height-1 , 0, false)); //creer la derniere ligne sans voiture

        ArrayList<Integer> baba = new ArrayList<>(this.lanePouvantContenirToile(3));
        for(int i = 0; i < this.lane.size(); i++) {
            for (int j = 0; j < baba.size(); j++) {
                if (baba.get(j) == i) {
                    this.lane.get(i).addToile();
                }
            }
        }
        ArrayList<Integer> mama = new ArrayList<>(this.lanePouvantContenirCoins(8));
        for(int i = 0; i < this.lane.size(); i++) {
            for (int j = 0; j < mama.size(); j++) {
                if (mama.get(j) == i) {
                    this.lane.get(i).addCoin();
                }
            }
        }
    }

    public Lane getLane(int x) {
        return this.lane.get(x);
    }

    public boolean isSafe(Case c) {
        return (this.lane.get(c.ord)).isSafe(c);
    }
    public boolean isSafef(Case c) { // safe pour la grenouille
        return (this.lane.get(c.ord)).isSafeForFrog(c);
    }

    public boolean isWinningPosition(Case c){
        if(c.ord == this.game.height - 1){
            return true;
        }else{
            return false;
        }
    }

    public int genererInt(int borneInf, int borneSup){
        Random random = new Random();
        int nb;
        nb = borneInf + random.nextInt(borneSup - borneInf);
        return nb;

    }

    public ArrayList<Lane> getLane() {
        return lane;
    }

    public ArrayList<Integer> lanePouvantContenirToile(int x){
        int i = 0;
        ArrayList<Integer>hav = new ArrayList<>();
        while(i < x){
            int s = genererInt(1,this.game.height-9);
            if(this.lane.get(s).getDensity() > 0.0) {
                hav.add(s);
                i++;
            }
        }
        return hav;
    }

    public ArrayList<Integer> lanePouvantContenirCoins(int x){
        int i = 0;
        ArrayList<Integer>hav = new ArrayList<>();
        while(i < x){
            int s = genererInt(1,this.game.height-9);
            if(!hav.contains(s)){
                hav.add(s);
                i++;
            }
        }
        return hav;
    }

    public boolean frogSurLaToile(){
        for(int i = 0; i < this.lane.size(); i++){
            if(this.lane.get(i).positionToile()){
                return true;
            }
        }
        return false;
    }

    public boolean frogSurLeCoin(){
        for(int i = 0; i < this.lane.size(); i++){
            if(this.lane.get(i).positionCoin()){
                this.game.score++;
                this.lane.get(i).removeCoin();
                return true;
            }
        }
        return false;
    }

    public void update(){
        for(int i = 0; i < this.lane.size(); i++){
            this.lane.get(i).update();
        }
    }
}
