// package src;

import classes.Graphe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
// import src.classes.Graphe;

public class Main {

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

    public static void main(String[] args) {
        /*
        // PREMIERE PARTIE : test des créations de graphe
        Graphe g1 = Graphe.creerGraphe("Orienté", 10);
        Graphe g2 = Graphe.creerGraphe("Non orienté", 3);

        g1.affichage();
        g2.affichage();
         */

        chargerGraphe("mon_graphe.txt");
    }
}
