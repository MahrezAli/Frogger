package gameCommons2;

import util.Case;
import util.Direction;

import java.awt.*;

public interface IFrogGreen {
    public void setRestart(boolean b);
    public boolean getRestart();
    public void setCases(Case cases) ;
    public boolean estDansLaCase(Case c);
    public void deplacer(boolean x);
    public void setLeftToRight(int leftToRight);
    public boolean estPasDansLaGrille();
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

}
