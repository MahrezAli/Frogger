package gameCommons2;


import util.Case;
import util.Direction;

public interface IFrogBlue {
    public void setRestart(boolean b);
    public boolean getRestart();
    public void setLife(Boolean b);
    /**
     * Donne la position actuelle de la grenouille
     * @return
     */
    public Case getPosition();

    /**
     * Donne la direction de la grenouille, c'est à dire de son dernier mouvement
     * @return
     */
    public Direction getDirection();

    /**
     * Déplace la grenouille dans la direction donnée et teste la fin de partie
     * @param key
     */
    public void move(Direction key);

    public void setLeftToRight(int leftToRight);
    public void deplacer(boolean x);
    public boolean estPasDansLaGrille();
    public void setCases(Case cases);
    public boolean estDansLaCase(Case c);

}
