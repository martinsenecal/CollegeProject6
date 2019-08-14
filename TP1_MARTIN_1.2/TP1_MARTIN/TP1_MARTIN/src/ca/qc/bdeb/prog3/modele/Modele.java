package ca.qc.bdeb.prog3.modele;

import java.awt.Color;
import static java.awt.Color.blue;
import static java.awt.Color.red;
import java.util.Observable;

/**
 * Classe Modèle (cerveau du Jeu) Toutes les vérifications se passent ici.
 *
 * @author 1737787
 */
public class Modele extends Observable {

    private boolean tourJoueur1 = true;
    private boolean effacerJeu = false;
    boolean partieFini = false;

    private int tabJeu[][][] = new int[4][4][4];
    private Joueur joueur1 = new Joueur(red, 0);
    private Joueur joueur2 = new Joueur(blue, 0);

    /**
     * Constructeur vide.
     */
    public Modele() {

    }

    /**
     * Méthode servant à donner la position du click sur le boutons tout en
     * changeant les tours. Vérifie les points, les gagnants et appele les
     * changement sur l'interface.
     *
     * @param numQuadHorizontale Position du quad Horizontalement dans le
     * tableau
     * @param numQuadVerticale Position du quad Verticalement dans le tableau
     * @param numBtns Position du boutons dans le quad
     */
    public void clickBoutons(int numQuadHorizontale, int numQuadVerticale, int numBtns) {

        if (tourJoueur1) {
            tabJeu[numQuadHorizontale][numQuadVerticale][numBtns] = 1;

        } else {
            tabJeu[numQuadHorizontale][numQuadVerticale][numBtns] = 2;

        }

        verifierPoint();
        verifierGagnant();

        tourJoueur1 = !tourJoueur1;
        majObserver();

    }

    /**
     * Méthode servant à initialiser le jeu en repartant tout à zéro. Parcourt
     * toutes les cases du tableau 3D en mettant à zéro en plus d'effacer les
     * points des joueurs à zéro
     */
    public void initialiserJeu() {
        for (int i = 0; i < tabJeu.length; i++) {

            for (int j = 0; j < tabJeu[i].length; j++) {
                for (int k = 0; k < tabJeu[i][j].length; k++) {
                    tabJeu[i][j][k] = 0;
                }

            }
        }
        effacerJeu = true;
        joueur1.setNbPoints(0);
        joueur2.setNbPoints(0);
        majObserver();

    }

    /**
     * Méthode servant à parcourir le tableau 3D, et vérifier les points faits
     * pour les deux joueurs! (Appele 3 méthodes différentes)
     */
    private void verifierPoint() {
        joueur1.setNbPoints(0);
        joueur2.setNbPoints(0);

        verifier10Points();
        verifier15Points();
        verifier20Points();

    }

    /**
     * Méthode servant à avertir les update à changer.
     */
    private void majObserver() {
        setChanged();
        notifyObservers();
    }

    /**
     * Getter de la variable qui verifie si le jeu est fini
     *
     * @return variable true ou false
     */
    public boolean isEffacerJeu() {
        return effacerJeu;
    }

    /**
     * Getter du tableau 3d
     *
     * @return tab3d (jeu)
     */

    /**
     * Méthode servant à vérifier les 3 cas possible pour donner 10pts pour les
     * deux joueurs (indépendamment)
     */
    private void verifier10Points() {
        //4pierres de même couleur sur 4 numéros identiques et consécutifs

        //1er: Vérification Horizontalement
        for (int i = 0; i < tabJeu.length; i++) {

            for (int k = 0; k < tabJeu[i].length; k++) {

                if (tabJeu[i][0][k] == 1 && tabJeu[i][1][k] == 1 && tabJeu[i][2][k] == 1 && tabJeu[i][3][k] == 1) {
                    joueur1.setNbPoints(joueur1.getNbPoints() + 10);
                } else if (tabJeu[i][0][k] == 2 && tabJeu[i][1][k] == 2 && tabJeu[i][2][k] == 2 && tabJeu[i][3][k] == 2) {
                    joueur2.setNbPoints(joueur2.getNbPoints() + 10);
                }
            }
        }

        //2e: Vérification Verticalement
        for (int j = 0; j < tabJeu.length; j++) {

            for (int k = 0; k < tabJeu[j].length; k++) {

                if (tabJeu[0][j][k] == 1 && tabJeu[1][j][k] == 1 && tabJeu[2][j][k] == 1 && tabJeu[3][j][k] == 1) {
                    joueur1.setNbPoints(joueur1.getNbPoints() + 10);
                } else if (tabJeu[0][j][k] == 2 && tabJeu[1][j][k] == 2 && tabJeu[2][j][k] == 2 && tabJeu[3][j][k] == 2) {
                    joueur2.setNbPoints(joueur2.getNbPoints() + 10);
                }
            }

        }

        //3e: Vérification Diagonalement
        for (int k = 0; k < tabJeu.length; k++) {

            if (tabJeu[0][0][k] == 1 && tabJeu[1][1][k] == 1 && tabJeu[2][2][k] == 1 && tabJeu[3][3][k] == 1) {
                joueur1.setNbPoints(joueur1.getNbPoints() + 10);
            } else if (tabJeu[0][0][k] == 2 && tabJeu[1][1][k] == 2 && tabJeu[2][2][k] == 2 && tabJeu[3][3][k] == 2) {
                joueur2.setNbPoints(joueur2.getNbPoints() + 10);
            }
        }

        for (int k = 0; k < tabJeu.length; k++) {

            if (tabJeu[3][0][k] == 1 && tabJeu[2][1][k] == 1 && tabJeu[1][2][k] == 1 && tabJeu[0][3][k] == 1) {
                joueur1.setNbPoints(joueur1.getNbPoints() + 10);
            }
            if (tabJeu[3][0][k] == 2 && tabJeu[2][1][k] == 2 && tabJeu[1][2][k] == 2 && tabJeu[0][3][k] == 2) {
                joueur2.setNbPoints(joueur2.getNbPoints() + 10);
            }
        }

    }

    /**
     * Méthode servant à vérifier les 3 cas possible pour donner 15pts pour les
     * deux joueurs (indépendamment)
     */
    private void verifier15Points() {
        //4pierres de même couleur en ordre numérique (1-2-3-4) 

        //1er: Vérification Horizontalement
        for (int i = 0; i < tabJeu.length; i++) {

            if (tabJeu[i][0][0] == 1 && tabJeu[i][1][1] == 1 && tabJeu[i][2][2] == 1 && tabJeu[i][3][3] == 1) {
                joueur1.setNbPoints(joueur1.getNbPoints() + 15);
            }
            if (tabJeu[i][0][3] == 1 && tabJeu[i][1][2] == 1 && tabJeu[i][2][1] == 1 && tabJeu[i][3][0] == 1) {
                joueur1.setNbPoints(joueur1.getNbPoints() + 15);
            }
            if (tabJeu[i][0][0] == 2 && tabJeu[i][1][1] == 2 && tabJeu[i][2][2] == 2 && tabJeu[i][3][3] == 2) {
                joueur2.setNbPoints(joueur2.getNbPoints() + 15);
            }
            if (tabJeu[i][0][3] == 2 && tabJeu[i][1][2] == 2 && tabJeu[i][2][1] == 2 && tabJeu[i][3][0] == 2) {
                joueur2.setNbPoints(joueur2.getNbPoints() + 15);
            }
        }

        //2e: Vérification Verticalement
        for (int j = 0; j < tabJeu.length; j++) {

            if (tabJeu[0][j][0] == 1 && tabJeu[1][j][1] == 1 && tabJeu[2][j][2] == 1 && tabJeu[3][j][3] == 1) {
                joueur1.setNbPoints(joueur1.getNbPoints() + 15);
            }
            if (tabJeu[3][j][0] == 1 && tabJeu[2][j][1] == 1 && tabJeu[1][j][2] == 1 && tabJeu[0][j][3] == 1) {
                joueur1.setNbPoints(joueur1.getNbPoints() + 15);
            }
            if (tabJeu[0][j][0] == 2 && tabJeu[1][j][1] == 2 && tabJeu[2][j][2] == 2 && tabJeu[3][j][3] == 2) {
                joueur2.setNbPoints(joueur2.getNbPoints() + 15);
            }
            if (tabJeu[3][j][0] == 2 && tabJeu[2][j][1] == 2 && tabJeu[1][j][2] == 2 && tabJeu[0][j][3] == 2) {
                joueur2.setNbPoints(joueur2.getNbPoints() + 15);
            }
        }

        //3e: Vérification Diagonalement
        if (tabJeu[0][0][0] == 1 && tabJeu[1][1][1] == 1 && tabJeu[2][2][2] == 1 && tabJeu[3][3][3] == 1) {
            joueur1.setNbPoints(joueur1.getNbPoints() + 15);
        }
        if (tabJeu[3][0][0] == 1 && tabJeu[2][1][1] == 1 && tabJeu[1][2][2] == 1 && tabJeu[0][3][3] == 1) {
            joueur1.setNbPoints(joueur1.getNbPoints() + 15);
        }
        if (tabJeu[0][0][3] == 1 && tabJeu[1][1][2] == 1 && tabJeu[2][2][1] == 1 && tabJeu[3][3][0] == 1) {
            joueur1.setNbPoints(joueur1.getNbPoints() + 15);
        }
        if (tabJeu[3][0][3] == 1 && tabJeu[2][1][2] == 1 && tabJeu[1][2][1] == 1 && tabJeu[0][3][0] == 1) {
            joueur1.setNbPoints(joueur1.getNbPoints() + 15);
        }

        if (tabJeu[0][0][0] == 2 && tabJeu[1][1][1] == 2 && tabJeu[2][2][2] == 2 && tabJeu[3][3][3] == 2) {
            joueur2.setNbPoints(joueur2.getNbPoints() + 15);
        }
        if (tabJeu[3][0][0] == 2 && tabJeu[2][1][1] == 2 && tabJeu[1][2][2] == 2 && tabJeu[0][3][3] == 2) {
            joueur2.setNbPoints(joueur2.getNbPoints() + 15);
        }
        if (tabJeu[0][0][3] == 2 && tabJeu[1][1][2] == 2 && tabJeu[2][2][1] == 2 && tabJeu[3][3][0] == 2) {
            joueur2.setNbPoints(joueur2.getNbPoints() + 15);
        }
        if (tabJeu[3][0][3] == 2 && tabJeu[2][1][2] == 2 && tabJeu[1][2][1] == 2 && tabJeu[0][3][0] == 2) {
            joueur2.setNbPoints(joueur2.getNbPoints() + 15);
        }

    }

    /**
     * Méthode servant à vérifier le cas pour donner 20pts pour les deux joueurs
     * (indépendamment)
     */
    private void verifier20Points() {
        //4 pierres de même couleur sur un même carré

        for (int i = 0; i < tabJeu.length; i++) {
            for (int j = 0; j < tabJeu[i].length; j++) {
                if (tabJeu[i][j][0] == 1 && tabJeu[i][j][1] == 1 && tabJeu[i][j][2] == 1 && tabJeu[i][j][3] == 1) {
                    joueur1.setNbPoints(joueur1.getNbPoints() + 20);
                } else if (tabJeu[i][j][0] == 2 && tabJeu[i][j][1] == 2 && tabJeu[i][j][2] == 2 && tabJeu[i][j][3] == 2) {
                    joueur2.setNbPoints(joueur2.getNbPoints() + 20);
                }
            }
        }

    }

    /**
     * Méthode servant à changer la couleur du joueur selon son choix (appelé
     * dans la classe fenêtre)
     *
     * @param valueJoueur1 Couleur du joueur 1
     * @param valueJoueur2 Couleur du joueur 2
     * @return booléen qui dit vrai si on peut pas changer la couleurs parce que
     * identique.
     */
    public boolean changementCouleurs(String valueJoueur1, String valueJoueur2) {
        boolean erreurCouleur = false;

        if (valueJoueur1.equals(valueJoueur2)) {
            erreurCouleur = true;
        } else {

            switch (valueJoueur1) {
                case "ROUGE":
                    joueur1.setCouleur(Color.RED);
                    break;

                case "BLEU":
                    joueur1.setCouleur(Color.BLUE);
                    break;

                case "JAUNE":
                    joueur1.setCouleur(Color.YELLOW);
                    break;

                case "VERT":
                    joueur1.setCouleur(Color.GREEN);
                    break;

            }

            switch (valueJoueur2) {
                case "ROUGE":
                    joueur2.setCouleur(Color.RED);
                    break;

                case "BLEU":
                    joueur2.setCouleur(Color.BLUE);
                    break;

                case "JAUNE":
                    joueur2.setCouleur(Color.YELLOW);
                    break;

                case "VERT":
                    joueur2.setCouleur(Color.GREEN);
                    break;

            }

        }

        majObserver();
        return erreurCouleur;

    }

    /**
     * Méthode servant à vérifier qui est le gagnant à la fin du jeux
     *
     * @return numéro du joueur gagnant soit (1)- (2) ou (0)-égalité
     */
    public int verifierGagnant() {

        int numJoueurGagnant = 0;
        partieFini = true;
        for (int i = 0; i < tabJeu.length; i++) {
            for (int j = 0; j < tabJeu[i].length; j++) {
                for (int k = 0; k < tabJeu[i][j].length; k++) {

                    if (tabJeu[i][j][k] == 0) {
                        partieFini = false;
                    }

                }
            }
        }

        if (partieFini) {

            if (joueur1.getNbPoints() > joueur2.getNbPoints()) {
                numJoueurGagnant = 1;
            } else if (joueur2.getNbPoints() < joueur2.getNbPoints()) {
                numJoueurGagnant = 2;
            } else {
                numJoueurGagnant = 0;
            }

        }

        return numJoueurGagnant;

    }

    /**
     * Getter du booléen partie fini
     *
     * @return booléen
     */
    public boolean isPartieFini() {
        return partieFini;
    }

    /**
     * Setter partie fini
     *
     * @param partieFini //booléen qui dit si fini.
     */
    public void setPartieFini(boolean partieFini) {
        this.partieFini = partieFini;
    }

    public int[][][] getTabJeu() {
        return tabJeu;
    }

    /**
     * Getter du joueur 1
     *
     * @return joueur1
     */
    public Joueur getJoueur1() {
        return joueur1;
    }

    /**
     * Getter du tour actuel
     *
     * @return variable booléenne
     */
    public boolean isTourJoueur1() {
        return tourJoueur1;
    }

    /**
     * Getter du joueur 2
     *
     * @return joueur2
     */
    public Joueur getJoueur2() {
        return joueur2;
    }
}
