package graphicalElements;

import gameCommons.IFrog;

import javax.swing.*;

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

    /**
     * Lance un écran de fin de partie
     * @param message le texte à afficher
     */
    public void endGameScreen(String message);
    public int getNbJoueur();
    public void remove(Element e);
    public void restart();
    public void beginGameScreen(String s);
    public Boolean getJeuEnCour();
    public int getStraw();
    public JFrame getFrame();
    public void setNbJoueur(int nbJoueur);
    public void setStraw(int straw);
}
