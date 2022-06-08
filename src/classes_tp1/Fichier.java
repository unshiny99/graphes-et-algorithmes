// Geoffrey Auzou, Maxime Frémeaux

package src.classes_tp1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Fichier {
    /**
     * charger un fichier txt pour générer un graphe correspondant
     * @param cheminFic String
     */
    public static Graphe chargerGraphe(String cheminFic) {
        try {
            Scanner in = new Scanner(new File("data/" + cheminFic));
            int i = 0;
            Graphe graphe = new Graphe();

            while (in.hasNextLine()) {
                if (i == 0) {
                    int type = in.nextInt();
                    int nbSommets = in.nextInt();
                    if (type == 1) {
                        graphe.creerGraphe(1, nbSommets);
                    } else { // cas par défaut
                        graphe.creerGraphe(0, nbSommets);
                    }
                } else {
                    int sommetSource = in.nextInt();
                    int sommetDest = in.nextInt();

                    Sommet sommet = graphe.getSommet(sommetSource);
                    if(!(sommet.voisinExiste(sommetDest))){
                        graphe.addConnexion(sommetSource, sommetDest);
                    }
                }
                i++;
                if (in.hasNextLine())
                    in.nextLine();
            }
            if (graphe != null) {
                graphe.affichage();
            }
            in.close();
            return graphe;
        } catch (FileNotFoundException e) {
            System.out.println("\nLe fichier spécifié est introuvable.\n");
            e.getMessage();
        }
        return null;
    } 
}
