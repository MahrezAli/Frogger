package gameCommons;

import util.Case;
import util.Direction2;

public interface IFrog2 {

    /**
     * Donne la position actuelle de la grenouille
     * @return
     */
    public Case getPosition();

    /**
     * Donne la direction de la grenouille, c'est à dire de son dernier mouvement
     * @return
     */
    public Direction2 getDirection();

    /**
     * Déplace la grenouille dans la direction donnée et teste la fin de partie
     * @param key
     */
    public void move(Direction2 key);

    public void setLeftToRight(int leftToRight);
    public void deplacer(boolean x);
    public boolean estPasDansLaGrille();
    public void setCases(Case cases);
    public boolean estDansLaCase(Case c);








}
