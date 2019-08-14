package ca.qc.bdeb.prog3.vue;

import ca.qc.bdeb.prog3.modele.Modele;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Classe Fenêtre: C'est le jeu principal qui est créée ici (toute son
 * interface)
 *
 * @author 1737787
 */
public class Fenetre extends JFrame implements Observer {

    private JPanel pnlJeu = new JPanel(new GridLayout(4, 4));
    private JPanel pnlJeuPrincipal = new JPanel(new BorderLayout());
    private JPanel pnlInformation = new JPanel(new GridLayout(1, 2));
    private JPanel pnlPrincipal = new JPanel();

    private JLabel lblTimer = new JLabel("00:00");
    private JLabel lblTours = new JLabel("C'est le tour aux:  Rouge");

    private Timer chronoJeu = startChrono();
    private int temps = 0;
    private int min = 0;
    private int sec = 0;

    private Pointage points1;
    private Pointage points2;

    private JMenuBar mnuBar = new JMenuBar();
    private JMenu mnuFichier = new JMenu("Fichier");
    private JMenu mnuAide = new JMenu("Aide");
    private JMenuItem mnuNouvellePartie = new JMenuItem("Nouvelle Partie");
    private JMenuItem mnuOptions = new JMenuItem("Options...");
    private JMenuItem mnuQuitter = new JMenuItem("Quitter");
    private JMenuItem mnuAPropos = new JMenuItem("À propos...");

    private Modele modele;

    /**
     * Constructeur de la classe Fenêtre, créé dans le main en insérant le
     * patron observateur Appele toutes les méthodes nécessaires et les
     * paramêtres pour faire fonctionner la fenêtre et l'afficher
     *
     * @param modele patron observateur intégré à la fenêtre (classe à part)
     */
    public Fenetre(Modele modele) {

        this.modele = modele;
        modele.addObserver(this);

        setTitle("Hijara!");
        setSize(600, 400);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        fermerApplication();

        creerInterface();

        creerEvenementsMenus();

        setVisible(true);
    }

    /**
     * Méthode créant l'interface visuellement. Incluant le jeu, les menus et
     * les informations. Part également le timer.
     */
    private void creerInterface() {

        mnuFichier.add(mnuNouvellePartie);
        mnuFichier.add(mnuOptions);
        mnuFichier.addSeparator();
        mnuFichier.add(mnuQuitter);
        mnuAide.add(mnuAPropos);
        mnuBar.add(mnuFichier);
        mnuBar.add(mnuAide);

        this.setJMenuBar(mnuBar);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                Quad quad = new Quad(modele, i, j);

                pnlJeu.add(quad);

            }
        }
        pnlJeuPrincipal.add(pnlJeu, BorderLayout.CENTER);

        points1 = new Pointage(modele, modele.getJoueur1().getCouleur(), modele.getJoueur1());
        points2 = new Pointage(modele, modele.getJoueur2().getCouleur(), modele.getJoueur2());

        pnlJeuPrincipal.add(points1, BorderLayout.NORTH);
        pnlJeuPrincipal.add(points2, BorderLayout.SOUTH);

        pnlInformation.add(lblTours);
        pnlInformation.add(lblTimer);

        pnlPrincipal.setLayout(new BoxLayout(pnlPrincipal, BoxLayout.Y_AXIS));
        pnlPrincipal.add(pnlInformation);
        pnlPrincipal.add(pnlJeuPrincipal);

        chronoJeu.start();

        this.add(pnlPrincipal);

    }

    /**
     * Méthode créant les événements du Menus Appele d'autres méthodes et on dis
     * quoi faire au programme en cliquant sur certaines options du menus
     * variés.
     */
    private void creerEvenementsMenus() {

        mnuNouvellePartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modele.initialiserJeu();
                chronoJeu.start();
                temps = 0;

            }
        });

        mnuQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showOptionDialog(Fenetre.this,
                        "Voulez vous fermer l’application?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        mnuAPropos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Fenetre.this, "Martin Senécal, dimanche 14 octobre 2018");
            }
        });

        mnuOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Options options = new Options(modele);

            }
        });

    }

    /**
     * Méthode appelé dans le constructeur: Sert à demander à l'utilisateur s'il
     * veut vraiment fermer la fenêtre en cliquant sur le 'X'
     */
    private void fermerApplication() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(Fenetre.this,
                        "Voulez vous fermer l’application?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

   
    @Override
    public void update(Observable o, Object arg) {

        for (int j = 0; j < 13; j++) {
            points1.getListePointage().get(j).setForeground(Color.black);
            points2.getListePointage().get(j).setForeground(Color.black);
        }

        int pointsJoueur1 = (modele.getJoueur1().getNbPoints()) / 5;
        int pointsJoueur2 = (modele.getJoueur2().getNbPoints()) / 5;

        try {
            points1.getListePointage().get(pointsJoueur1 - 1).setForeground(modele.getJoueur1().getCouleur());
        } catch (ArrayIndexOutOfBoundsException e) {

        } catch (IndexOutOfBoundsException e) {
            points1.getListePointage().get(12).setForeground(modele.getJoueur1().getCouleur());
        }
        try {
            points2.getListePointage().get(pointsJoueur2 - 1).setForeground(modele.getJoueur2().getCouleur());
        } catch (ArrayIndexOutOfBoundsException e) {

        } catch (IndexOutOfBoundsException e) {
            points2.getListePointage().get(12).setForeground(modele.getJoueur2().getCouleur());
        }

        if (modele.isTourJoueur1()) {
            lblTours.setText("C'est le tour aux: " + getNomCouleur1());

        } else {
            lblTours.setText("C'est le tour aux: " + getNomCouleur2());
        }

        if (modele.isPartieFini()) {
            int valeurGagnant = modele.verifierGagnant();
            String annonceGagnant;
            chronoJeu.stop();

            if (valeurGagnant == 1) {

                annonceGagnant = "Le gagnant est le joueur du Haut avec un total de: " + modele.getJoueur1().getNbPoints() + " points!";
            } else if (valeurGagnant == 2) {

                annonceGagnant = "Le gagnant est le joueur du Bas avec un total de: " + modele.getJoueur2().getNbPoints() + " points!";
            } else {

                annonceGagnant = "Les deux joueurs sont égaux avec un total de: " + modele.getJoueur1().getNbPoints() + " points!";
            }
            JOptionPane.showMessageDialog(Fenetre.this, annonceGagnant);

            modele.setPartieFini(false);

        }
    }

    /**
     * Méthode qui sert à partir le chrono Timer au début en lançant la fenêtre
     * dans le constructeur Va afficher les minutes et les secondes depuis le
     * moment zéro
     *
     * @return un Timer chrono (nécessaire à sa création)
     */
    private Timer startChrono() {
        chronoJeu = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                temps++;
                min = (int) Math.floor(temps / 60);
                sec = temps % 60;

                if (min > 9 && sec > 9) {
                    lblTimer.setText(min + ":" + sec);
                } else if (min > 9 && sec < 9) {
                    lblTimer.setText(min + ":0" + sec);
                } else if (min < 9 && sec > 9) {
                    lblTimer.setText("0" + min + ":" + sec);
                } else {
                    lblTimer.setText("0" + min + ":0" + sec);
                }
            }

        });
        return chronoJeu;
    }

     /**
     * Méthode qui converti la couleur du Joueur 1 présente en String pour être
     * affiché
     *
     * @return String de la couleur du joueur 1 présentement
     */
    private String getNomCouleur1() {
        String nomCouleur1 = null;
        if (modele.getJoueur1().getCouleur() == Color.BLUE) {
            nomCouleur1 = "Bleu";
        } else if (modele.getJoueur1().getCouleur() == Color.RED) {
            nomCouleur1 = "Rouge";

        } else if (modele.getJoueur1().getCouleur() == Color.YELLOW) {
            nomCouleur1 = "Jaune";

        } else if (modele.getJoueur1().getCouleur() == Color.GREEN) {
            nomCouleur1 = "Vert";
        }
        return nomCouleur1;

    }

    /**
     * Méthode qui converti la couleur du Joueur 2 présente en String pour être
     * affiché
     *
     * @return String de la couleur du joueur 2 présentement
     */
    private String getNomCouleur2() {
        String nomCouleur2 = null;
        if (modele.getJoueur2().getCouleur() == Color.BLUE) {
            nomCouleur2 = "Bleu";
        } else if (modele.getJoueur2().getCouleur() == Color.RED) {
            nomCouleur2 = "Rouge";

        } else if (modele.getJoueur2().getCouleur() == Color.YELLOW) {
            nomCouleur2 = "Jaune";

        } else if (modele.getJoueur2().getCouleur() == Color.GREEN) {
            nomCouleur2 = "Vert";
        }
        return nomCouleur2;
    }

    /**
     * Méthode update qui sert à mettre à jour l'affichage de l'interface à
     * chaque fois qu'il y a un changement dans le patron observateur (soit le
     * modèle)
     *
     * @param o Observable
     * @param arg Patron observateur
     */
}
