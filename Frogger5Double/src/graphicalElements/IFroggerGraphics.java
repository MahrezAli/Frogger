package graphicalElements;

import gameCommons.IFrog;
import gameCommons.IFrog2;

import java.awt.event.KeyEvent;

public interface IFroggerGraphics {

    /**
     * Ajoute l'élément aux éléments à afficher
     * @param e
     */
    public void add(Element e);

    /**
     * Enlève tous les éléments actuellement affichés
     */
    public void clear();

    /***
     * Actualise l'affichage
     */
    public void repaint();

    /**
     * Lie la grenouille à l'environneemnt graphique
     * @param frog
     */
    public void setFrog(IFrog frog);
    public void setFrog2(IFrog2 frog2);
    /**
     * Lance un écran de fin de partie
     * @param message le texte à afficher
     */
    public void endGameScreen(String message);

    public void remove(Element e);
}
