package ca.qc.bdeb.prog3.vue;

import ca.qc.bdeb.prog3.modele.Modele;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Classe Options (celle permettant à l'utilisateur de changer sa couleur)
 * On créée donc une nouvelle fenêtre, qui apparaîtra par-dessus le jeu principal.
 * @author 1737787
 */
public class Options extends JFrame implements Observer {

    private JPanel pnlChoix = new JPanel(new GridLayout(2, 2));
    private JPanel pnlPrincipal = new JPanel(new BorderLayout());
    private JLabel lblTitre = new JLabel("Changer la couleur de votre Joueur");
    private JLabel lblJoueur1 = new JLabel("Joueur 1:");//a changer pr le bon nom
    private JLabel lblJoueur2 = new JLabel("Joueur 2:");//idem
    private JButton btnSave = new JButton("Sauvegarder");
    private String[] choix = {"BLEU", "ROUGE", "JAUNE", "VERT"};
    private JComboBox cboJoueur1 = new JComboBox(choix);
    private JComboBox cboJoueur2 = new JComboBox(choix);
    private Modele modele;

    /**
     * Constructeur de la fenêtre Options 
     * Il sert donc à créer la fenêtre, en appelant les méthodes nécessaires
     * @param modele Hérite du patron observateur modèle et de ses attributs
     */
    public Options(Modele modele) {
        this.modele = modele;
        modele.addObserver(this);

        setTitle("Options Jeux");
        setSize(390, 190);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        creerInterface();

        creerEvenements();

        setVisible(true);

    }
/**
 * Méthode créant l'interface principale appelé par le constructeur.
 */
    private void creerInterface() {

        cboJoueur1.setPreferredSize(new Dimension(50, 20));
        cboJoueur2.setPreferredSize(new Dimension(50, 20));

        pnlChoix.add(lblJoueur1);
        pnlChoix.add(cboJoueur1);
        pnlChoix.add(lblJoueur2);
        pnlChoix.add(cboJoueur2);

        pnlPrincipal.add(btnSave, BorderLayout.SOUTH);
        pnlPrincipal.add(lblTitre, BorderLayout.NORTH);
        pnlPrincipal.add(pnlChoix, BorderLayout.CENTER);

        this.add(pnlPrincipal);

    }

    /**
     * Méthode créant les événements du Bouton sauvegarder
     * Sert à prendre les informations des nouvelles couleurs, pour ainsi les envoyer 
     * au modèle en appelant les méthodes nécessaires.
     */
    private void creerEvenements() { //on vient prendre les données prises pour les convertir
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String valueJoueur1 = cboJoueur1.getSelectedItem().toString();
                String valueJoueur2 = cboJoueur2.getSelectedItem().toString();
                boolean erreur = true;

                erreur = modele.changementCouleurs(valueJoueur1, valueJoueur2);

                if (erreur) {
                    JOptionPane.showMessageDialog(Options.this, "Un joueur a déjà cette couleur. Réssayer SVP");
                } else {
                    dispose();
                }

            }
        });
    }
/**
 * Update, non-nécessaire, c'est l'update du quad, qui sera utilisé car c'est celui-ci
 * qui est modifé en changeant la couleur des boutons.
 * @param o
 * @param o1 
 */
    @Override
    public void update(Observable o, Object o1) {

    }

}
