// Geoffrey Auzou, Maxime Frémeaux

package src.classes_tp2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.Sommet;

public class FichierMatrice {
    /**
     * charger un fichier txt pour générer la matrice d'adjacence correspondante
     * @param cheminFic String
     */
    public static Matrice chargerGraphe(String cheminFic) {
        try {
            Scanner in = new Scanner(new File("data/" + cheminFic));
            int i = 0;
            Matrice graphe = new Matrice();

            while (in.hasNextLine()) {
                if (i == 0) {
                    int type = in.nextInt();
                    int nbSommets = in.nextInt();
                    if (type == 1) {
                        graphe.creerGraphe(1, nbSommets);
                    } else { // cas par défaut
                        graphe.creerGraphe(0, nbSommets);
                    }
                } else { // pour les autres lignes, faire les liaisons
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
            return graphe;
        } catch (FileNotFoundException e) {
            System.out.println("\nLe fichier spécifié est introuvable.\n");
            e.getMessage();
        }
        return null;
    } 

    /**
     * charger un fichier txt contenant tous ses sommets
     * pour générer la matrice d'adjacence correspondante
     * @param cheminFic String
     */
    public static Matrice chargerGrapheMots(String cheminFic) {
        try {
            Scanner in = new Scanner(new File("data/" + cheminFic));
            int i = 0;
            Matrice graphe = new Matrice();
            int nbSommets = 0;
            List<Sommet> sommets = new ArrayList<>();

            while (in.hasNextLine()) {
                if (i == 0) {
                    //int type = in.nextInt();
                    nbSommets = in.nextInt();
                    in.nextLine();
                } else {
                    for(int j=0;j<nbSommets;j++) {
                        if (in.hasNextLine()) {
                            String sommet = in.nextLine();
                            sommets.add(new Sommet(j+1,sommet));
                        }
                    }
                }
                i++;
            }
            // cas non orienté
            graphe.creerGrapheMots(0, nbSommets, sommets);
            graphe.generationLiaisons();
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

    /**
     * charger un fichier txt contenant différents mots
     * pour générer ses sommets et arêtes correspondantes
     * @param cheminFic String
     */
    public static Matrice chargerGrapheLettres(String cheminFic) {
        try {
            Scanner in = new Scanner(new File("data/" + cheminFic));
            int i = 0;
            Matrice graphe = new Matrice();
            int nbSommets = 0;
            List<Sommet> sommets = new ArrayList<>();

            while (in.hasNextLine()) {
                if (i == 0) {
                    //int type = in.nextInt();
                    nbSommets = in.nextInt();
                    in.nextLine();
                } else {
                    for(int j=0;j<nbSommets;j++) {
                        if (in.hasNextLine()) {
                            String sommet = in.nextLine();
                            sommets.add(new Sommet(j+1,sommet));
                        }
                    }
                }
                i++;
            }
            // cas orienté
            graphe.creerGrapheMots(0, nbSommets, sommets);
            graphe.generationLiaisonsTopo();
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

