package gameCommons3;

import environment3.Lane3;
import util.Case;

import java.util.ArrayList;

public interface IEnvironment3 {

    /**
     * Teste si une case est sure, c'est à dire que la grenouille peut s'y poser
     * sans mourir
     *
     * @param c
     *            la case à tester
     * @return vrai s'il n'y a pas danger
     */
    public boolean isSafe(Case c);
    public ArrayList<Lane3> getLane();
    public Lane3 getLane(int x);
    public boolean isSafef(Case c);
    /**
     * Teste si la case est une case d'arrivee
     *
     * @param c
     * @return vrai si la case est une case de victoire
     */
    public boolean isWinningPosition(Case c);

    /**
     * Effectue une étape d'actualisation de l'environnement
     */
    public void update();
    //public void ajouteRoute(int i);
    public void ajouterLaDerniereLigne();

    public void prendLaPlace();

}
