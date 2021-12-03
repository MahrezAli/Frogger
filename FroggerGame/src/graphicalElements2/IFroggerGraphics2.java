package graphicalElements2;

import gameCommons2.IFrogGreen;
import gameCommons2.IFrogBlue;
import graphicalElements.Element;

import javax.swing.*;

public interface IFroggerGraphics2 {

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
    public void setFrog(IFrogGreen frog);
    public void setFrog2(IFrogBlue frog2);
    /**
     * Lance un écran de fin de partie
     * @param message le texte à afficher
     */
    public void endGameScreen(String message);
    public void setStraw(int straw);
    public void remove(Element e);
    public int getNbJoueur();
    public void restart();
    public void beginGameScreen(String s);
    public Boolean getJeuEnCour();
    public int getStraw();
    public void setNbJoueur(int nbJoueur);
    public JFrame getFrame();
}
