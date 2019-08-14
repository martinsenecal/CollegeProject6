package ca.qc.bdeb.prog3.vue;

import ca.qc.bdeb.prog3.modele.Joueur;
import ca.qc.bdeb.prog3.modele.Modele;
import java.awt.Color;
import java.util.ArrayList;
import ca.qc.bdeb.prog3.vue.Fenetre;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe (panneau) pointage qui inclu le score de chaque joueur avec un interface
 * @author 1737787
 */
public class Pointage extends JPanel implements Observer {

    private JLabel lblJoueur = new JLabel("");
    private JLabel lblPoints = new JLabel("      Points.");
    private Modele modele;
    private Joueur joueur;
    private Fenetre fenetre;
    private ArrayList<JLabel> listePointage = new ArrayList();

    /**
     * Getter de la liste contenant les pointages afin d'être utilisé dans le modèle
     * @return une liste de pointage
     */
    public ArrayList<JLabel> getListePointage() {
        return listePointage;
    }

    /**
     * Constructeur du panneau pointage (extends JPanel), chaque joueur à donc son panneau 
     * qui est créé dans la fenêtre
     * @param modele va être modifé au cours du programme par le patron observateur
     * @param couleur chaque joueur a une couleur qui sera affiché ainsi que la couleur du pointage
     * @param joueur Chaque panneau a un joueur en paramètre
     */
    public Pointage(Modele modele, Color couleur, Joueur joueur) {
        this.modele = modele;
        this.joueur = joueur;
        modele.addObserver(this);

       if(joueur.getCouleur()==Color.BLUE){
           lblJoueur.setText("Bleu");
       } else { lblJoueur.setText("Rouge");}
       
       

        this.add(lblJoueur);

        for (int i = 0; i < 13; i++) {

            JLabel lblPointageJoueur = new JLabel((i + 1) * 5 + " ", JLabel.CENTER);
            listePointage.add(lblPointageJoueur);
            this.add(lblPointageJoueur);

        }
        this.add(lblPoints);

    }

   /**
    * Méthode qui sert à mettre à jour l'interface grâce au patron observateur
    * Il est ainsi appelé par la classe modèle grâce aux MajObserver.
    * @param o Patron observateur
    * @param arg Patron observateur
    */
    @Override
    public void update(Observable o, Object arg) {
        String nomCouleur = null;
        if (joueur.getCouleur() == Color.BLUE) {
            nomCouleur = "Bleu";
        } else if (joueur.getCouleur() == Color.RED) {
            nomCouleur = "Rouge";
        } else if (joueur.getCouleur() == Color.YELLOW) {
            nomCouleur = "Jaune";
        } else if (joueur.getCouleur() == Color.GREEN) {
            nomCouleur = "Vert";
        }

        lblJoueur.setText(nomCouleur);
    }

}
