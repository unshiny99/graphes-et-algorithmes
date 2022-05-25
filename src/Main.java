// Geoffrey Auzou, Maxime Frémeaux

package src;

// import classes.Graphe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import src.classes.Graphe;

public class Main {

    /**
     * charger un fichier txt pour générer un graphe correspondant
     * @param cheminFic chemin (PATH) du fichier
     */
    public static void chargerGraphe(String cheminFic) {
        try {
            Scanner in = new Scanner(new File("data/" + cheminFic));
            int i = 0;
            Graphe graphe = null;

            while (in.hasNextLine()) {
                if (i == 0) {
                    int type = in.nextInt();
                    int nbSommets = in.nextInt();
                    int nbConnexions = in.nextInt();
                    if (type == 1) {
                        graphe = Graphe.creerGraphe("Orienté", nbSommets);
                        //graphe = new Graphe("Orienté", nbSommets, nbConnexions);
                    } else { // cas par défaut
                        graphe = Graphe.creerGraphe("Non orienté", nbSommets);
                        //graphe = new Graphe("Non orienté", nbSommets, nbConnexions);
                    }
                } else {
                    int sommetSource = in.nextInt();
                    int sommetDest = in.nextInt();

                    graphe.addConnexion(sommetSource, sommetDest);
                }
                i++;
                if (in.hasNextLine())
                    in.nextLine();
            }
            if (graphe != null) {
                graphe.affichage();
            }
            in.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * fonction principale
     * @param args
     */
    public static void main(String[] args) {

        // PREMIERE PARTIE : test des créations de graphe
        Graphe g1 = Graphe.creerGraphe("Orienté", 10);
        //Graphe g2 = Graphe.creerGraphe("Non orienté", 3);

        //g1.affichage();
        //g2.affichage();

        chargerGraphe("mon_graphe.txt");
        // g1.addSommet(11);
        // g1.addSommet(12);

        // System.out.println("---- After addSommet ----");
        // g1.Affichage();

        // g1.addConnexion(1, 2);
        // g1.addConnexion(2, 3);

        // System.out.println("---- After addConnexion ----");
        // g1.Affichage();

        // g1.suppConnexion(2, 3);

        // System.out.println("---- After suppConnexion ----");

        // g1.generationAleatoire(10, 0.5);
        // g1.affichage();
    }
}
