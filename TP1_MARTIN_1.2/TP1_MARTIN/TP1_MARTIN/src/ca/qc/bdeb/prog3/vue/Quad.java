package ca.qc.bdeb.prog3.vue;

import ca.qc.bdeb.prog3.modele.Modele;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Classe Quad, c'est un panneau qui englobe les 4 boutons (1-2-3-4) C'est ici
 * qu'on le définit en le créant en plus de créer les événements servant à faire
 * les vérifications nécessaire lorsqu'un bouton est créée en plus de le mettre
 * à jour en changeant ses couleurs et en le mettant non-disponible
 *
 * @author 1737787
 */
public class Quad extends JPanel implements Observer {

    private Modele modele;
    private Fenetre fenetre;
    private int numQuadHorizontale;
    private int numQuadVerticale;

    private ArrayList<JButton> listeBtns = new ArrayList<>();

    /**
     * Constructeur du Quad qui crée l'interface du quad en appelant certaines
     * méthodes.
     *
     * @param modele va être modifé au cours du programme par le patron
     * observateur
     * @param numQuadHorizontale Chaque Quad a une position horizontale dans le
     * tableau
     * @param numQuadVerticale Chaque Quad a une position verticale dans le
     * tableau
     */
    public Quad(Modele modele, int numQuadHorizontale, int numQuadVerticale) {
        this.modele = modele;
        modele.addObserver(this);

        this.numQuadHorizontale = numQuadHorizontale;
        this.numQuadVerticale = numQuadVerticale;

        this.setLayout(new GridLayout(2, 2));

        creerListeBtns();

        listeBtns.get(1).setEnabled(false);
        listeBtns.get(2).setEnabled(false);
        listeBtns.get(3).setEnabled(false);

        add(listeBtns.get(1));
        add(listeBtns.get(2));
        add(listeBtns.get(0));
        add(listeBtns.get(3));

        this.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.DARK_GRAY));

    }

    /**
     * On crée une liste de Boutons, et chaque bouton appele une méthode du
     * modèle observable En clickant sur le btn, ça appele directement le modèle
     * et les vérifications, changement de tour se mettent en marche
     */
    private void creerListeBtns() {

        for (int i = 0; i < 4; i++) {
            String value = Integer.toString(i + 1);
            JButton btn = new JButton(value);

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    modele.clickBoutons(numQuadHorizontale, numQuadVerticale, Integer.parseInt(btn.getText()) - 1);

                }

            });
            listeBtns.add(btn);
        }
    }

    /**
     * Méthode qui sert à mettre à jour l'interface grâce au patron observateur
     * Il est ainsi appelé par la classe modèle grâce aux MajObserver.
     *
     * @param o Patron observateur
     * @param arg Patron observateur
     */
    @Override
    public void update(Observable o, Object arg) {

        for (int k = 0; k < 4; k++) {

            if (modele.getTabJeu()[numQuadHorizontale][numQuadVerticale][k] != 0) {
                listeBtns.get(k).setEnabled(false);

                if (k <= 2) {
                    listeBtns.get(k + 1).setEnabled(true);
                }

                if (modele.getTabJeu()[numQuadHorizontale][numQuadVerticale][k] == 1) {
                    listeBtns.get(k).setBackground(modele.getJoueur1().getCouleur());

                } else if (modele.getTabJeu()[numQuadHorizontale][numQuadVerticale][k] == 2) {
                    listeBtns.get(k).setBackground(modele.getJoueur2().getCouleur());

                }

            }
            if (modele.getTabJeu()[numQuadHorizontale][numQuadVerticale][k] == 0) {

                if (k == 0) {
                    listeBtns.get(k).setEnabled(true);
                } else if (modele.getTabJeu()[numQuadHorizontale][numQuadVerticale][k - 1] == 0) {
                    listeBtns.get(k).setEnabled(false);
                }

                listeBtns.get(k).setBackground(null);
            }

        }

    }
}
