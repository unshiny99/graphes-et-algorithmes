// Geoffrey Auzou, Maxime Frémeaux

package src.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Fichier {
    /**
     * charger un fichier txt pour générer un graphe correspondant
     * @param cheminFic String
     */
    public static void chargerGraphe(String cheminFic) {
        try {
            Scanner in = new Scanner(new File("data/" + cheminFic));
            int i = 0;
            Graphe graphe = new Graphe();

            while (in.hasNextLine()) {
                if (i == 0) {
                    int type = in.nextInt();
                    int nbSommets = in.nextInt();
                    //int nbConnexions = in.nextInt();
                    if (type == 1) {
                        graphe.creerGraphe(1, nbSommets);
                    } else { // cas par défaut
                        graphe.creerGraphe(0, nbSommets);
                    }
                    graphe.affichage();
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
}
