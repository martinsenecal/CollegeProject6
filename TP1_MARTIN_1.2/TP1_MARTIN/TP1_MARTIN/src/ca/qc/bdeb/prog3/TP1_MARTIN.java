/*
 TP1 de Martin Senécal du cours no 3 d'informatique en SIM

    Mon Auto-Évaluation:
    Mon programme fonctionne bien. Je l'ai testé au complet en essayant toutes les
    erreurs possible, et je les ai toute traités. Mes jeux de tests viennent en plus prouver
    mon point, car j'ai tout passé aux travers. J'ai bien utilisé le modèle observateur, question
    d'avoir un code propre et bien divisé. C'est sur, je ne suis pas un pro, alors mon code aurait pu être
    d'avantages optimisé, mais de ce que je vois, j'ai fais le mieux que je pouvais. Mon interface est belle,
    et j'ai bien profité des interfaces. J'ai eu un peu du mal au début à toute mettre en place, mais après 
    que j'aille compris, c'était ok avec quelques fois des petites bugs stupides.
    
    Bonne correction, vous êtes bon à faire ça, c'est beaucoup 50x lire la même chose :-)

    Martin Senécal
    groupe 02

 */
package ca.qc.bdeb.prog3;

import ca.qc.bdeb.prog3.modele.Modele;
import ca.qc.bdeb.prog3.vue.Fenetre;


/**
 * TP1: Martin Senécal
 * @author 1737787
 */
public class TP1_MARTIN {

  /**
   * Main du projet qui crée la classe Fenêtre et Modèle
   * @param args 
   */
    public static void main(String[] args) {
      
           
            Modele modele = new Modele();

            Fenetre fenetre= new Fenetre(modele);
        }
    }
    

