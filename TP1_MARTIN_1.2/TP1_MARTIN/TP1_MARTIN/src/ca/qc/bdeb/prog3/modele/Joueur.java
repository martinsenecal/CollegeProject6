/**
 * Classe Joueur qui se retrouve dans le modèle:
 * Détermine ce qu'est un joueur
 */
package ca.qc.bdeb.prog3.modele;

import java.awt.Color;

/**
 * Classe Joueur
 * @author 1737787
 */
public class Joueur {
    
    private Color couleur;

    private int nbPoints;

    /**
     * Constructeur du Joueur
     * @param couleur //Couleur de ses cases
     * @param nbPoints //Nbre de points scoré par le joueur
     */
    public Joueur(Color couleur,  int nbPoints) {
        this.couleur = couleur;
       
        this.nbPoints = nbPoints;
    }

    /**
     * Getter Couleur
     * @return couleur
     */
    public Color getCouleur() {
        return couleur;
    }

    /**
     * Setter du nombre de pts
     * @param nbPoints nb de Points
     */
    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }
 
    /**
     * Getter Nombre de points
     * @return nbPoints
     */
    public int getNbPoints() {
        return nbPoints;
    }

    /**
     * Setter couleur
     * @param couleur couleur joueur 
     */
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
    
    
    
    
    
}
