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
     * Vérifie si une lettre existe dans la liste des sommets
     * @param identifiant
     * @return Boolean
     */
    public static Boolean checkLettreExiste(String lettre, List<Sommet> sommets) {
        for(Sommet sommet : sommets) {
            if(sommet.getMot().equals(lettre)){
                return true;
            }
        }
        return false;
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
            List<String> mots = new ArrayList<>();

            while (in.hasNextLine()) {
                if (i == 0) { 
                    // ignore here
                    in.nextLine();
                } else {
                    String ensSommets = in.nextLine();
                    mots.add(ensSommets);
                    if (in.hasNextLine()) {
                        for(Integer k = 0; k < ensSommets.length(); k++) {
                            if(!checkLettreExiste(String.valueOf(ensSommets.charAt(k)), sommets)) {
                                sommets.add(new Sommet(k+1,String.valueOf(ensSommets.charAt(k))));
                                nbSommets++;
                            }
                        }
                    }
                }
                i++;
            }
            // cas orienté
            graphe.creerGrapheMots(1, nbSommets, sommets);
            // génération des liaisons
            Integer index = null;
            for(int j=0;j<mots.size()-1;j++) {
                index = null;
                //System.out.println(mots.get(j));
                // vérifie si une seule lettre est différente, pour une même taille de liste
                if(mots.get(j).length() == mots.get(j+1).length()) {
                    index = graphe.verificationDiffCharIndex(mots.get(j), mots.get(j+1)); 
                } else if(mots.get(j).length() < mots.get(j+1).length()) {
                    index = graphe.verifDiffCharIndex(mots.get(j), mots.get(j+1));
                } else { // mots.get(j).length() > mots.get(j+1).length()
                    index = graphe.verifDiffCharIndex(mots.get(j+1), mots.get(j));
                }
                if(index != null && index >= 0) {
                    //System.out.println("index lettre : " + index);
                    // créer une connexion entre les 2 lettres
                    String lettreSource = String.valueOf(mots.get(j).charAt(index));
                    String lettreDest = String.valueOf(mots.get(j+1).charAt(index));
                    Integer indexSource = null;
                    Integer indexDest = null;
                    //System.out.println("lettreSource : " + lettreSource);
                    //System.out.println("lettreDest : " + lettreDest);
                    for(int k=0; k<graphe.getListeSommets().size();k++) {
                        //System.out.println("mot courant : " + graphe.getListeSommets().get(k).getMot());
                        if(graphe.getListeSommets().get(k).getMot().equals(lettreSource)) {
                            indexSource = graphe.getListeSommets().get(k).getIndex();
                            //System.out.println("indexSource : " + indexSource);
                        }
                        if(graphe.getListeSommets().get(k).getMot().equals(lettreDest)) {
                            indexDest = graphe.getListeSommets().get(k).getIndex();
                            //System.out.println("indexDest : " + indexDest);
                        }
                    }
                    if(indexSource != null && indexDest != null) {
                        //System.out.println("indexSource : " + indexSource + " ; indexDest : " + indexDest);
                        graphe.addConnexion(indexSource,indexDest);
                    }
                }
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

