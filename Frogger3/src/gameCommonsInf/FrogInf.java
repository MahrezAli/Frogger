package gameCommonsInf;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class FrogInf implements IFrog {
    private Game game;
    private Direction direction;
    private Case cases;

    public FrogInf(Game game){
        this.game = game;
        this.direction  = Direction.up;
        this.cases = new Case(this.game.width / 2,0); // placer la grenouille au milieu de l'écran.
    }

    public Case getPosition() {
        return this.cases;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void move(Direction key){
        if(this != null) {
            this.direction = key;
            if (key == Direction.up && this.cases.ord + 1 < this.game.height) {
                this.cases = new Case(this.cases.absc, this.cases.ord + 1);
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
            if (key == Direction.down && this.cases.ord - 1 > 0 && !this.game.testLose()) {
                this.cases = new Case(this.cases.absc, this.cases.ord - 1);
                this.game.score--;
                this.game.height--;
                //System.out.println("l'odonnée de la grenouille est : " + this.cases.ord);
            }
            if (key == Direction.left && this.cases.absc - 1 > 0 && !this.game.testLose()) {
                this.cases = new Case(this.cases.absc - 1, this.cases.ord);
            }
            if (key == Direction.right && this.cases.absc + 1 < this.game.width && !this.game.testLose()) {
                this.cases = new Case(this.cases.absc + 1, this.cases.ord);
            }
        }
    }
}




















