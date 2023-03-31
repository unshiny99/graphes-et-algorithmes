package src;

import java.io.IOException;
import java.util.Scanner;

// Bryan Moreau, Maxime Frémeaux, Geoffrey Auzou
public class Main {
    public static void main(String[] args) {
        Scanner scan_menu = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        Integer select = null;
        Graphe graphe = null;

        do {
            if (select != null) {
                System.out.println("[Terminé]");
            }

            System.out.println("--------------------------------------------------------\n" +
                    "|                     MENU TP GRAPHES                   |\n" +
                    "---------------------------------------------------------\n" +
                    "|1  : MODE : Afficher graphe                            |\n" +
                    "|2  : MODE : Création graphe (avec nombre sommets)      |\n" +
                    "|3  : MODE : Ajout sommet                               |\n" +
                    "|4  : MODE : Ajout arc                                  |\n" +
                    "|5  : MODE : Sont adjacents ?                           |\n" +
                    "|6  : MODE : Charger un graphe                          |\n" +
                    "|7  : MODE : Sauvegarde du graphe                       |\n" +
                    "|8  : MODE : Générer un graphe (Erdös-Rényi)            |\n" +
                    "|0  : QUITTER                                           |\n" +
                    "--------------------------------------------------------");
            try {
                select = scan_menu.nextInt();
            } catch (Exception e) {
                System.out.println("Erreur de paramètre d'entrée.");
                scan_menu.close();
                scan.close();
                break;
            }
            switch (select) {
                case 0:
                    // quitter programme
                    scan_menu.close();
                    scan.close();
                    break;
                case 1:
                    // afficher graphe
                    if(graphe != null) {
                        graphe.showGraph();
                    } else {
                        System.out.println("Pas de graphe instancié");
                    }
                    break;
                case 2:
                    // créer graphe
                    Integer nbSommmets = -1;
                    do {
                        System.out.println("Entrez le nombre de sommets souhaités (0 pour graphe vide)");
                        nbSommmets = scan.nextInt();
                        graphe = new Graphe(1,nbSommmets);
                    } while(nbSommmets < 0);
                    break;
                case 3:
                    // ajouter sommet
                    if(graphe != null) {
                        graphe.showGraph();
                        Integer indexSommet = 0;
                        System.out.println("Index du nouveau sommet :");
                        indexSommet = scan.nextInt();
                        graphe.addSommet(indexSommet, true);
                    }
                    break;
                case 4:
                    // ajouter arc
                    if(graphe != null) {
                        graphe.showGraph();
                        System.out.println("Index du premier sommet (origine) :");
                        Integer indexSommet1 = scan.nextInt();
                        System.out.println("Index du second sommet (destination) :");
                        Integer indexSommet2 = scan.nextInt();
                        System.out.println("Poids de l'arc");
                        Double poids = scan.nextDouble();
                        graphe.addConnexion(indexSommet1, indexSommet2, poids);
                    }
                    break;
                case 5:
                    // savoir si 2 sommets sont adjacents
                    if(graphe != null) {
                        graphe.showGraph();
                        System.out.println("Index du premier sommet (origine) :");
                        Integer indexSommet1 = scan.nextInt();
                        System.out.println("Index du second sommet (destination) :");
                        Integer indexSommet2 = scan.nextInt();
                        Boolean estAdjacent = graphe.isDirect(indexSommet1, indexSommet2);
                        if(estAdjacent) {
                            System.out.println("Les sommets sont adjacents");
                        } else {
                            System.out.println("Les sommets ne sont pas adjacents");
                        }
                    }
                    break;
                case 6:
                    // charger un graphe
                    try {
                        graphe = Graphe.loadGraph("source_tp_fisa3/data/mon_graphe.txt");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    // sauvegarder un graphe
                    try {
                        graphe.saveGraph("source_tp_fisa3/data/saved_graph.txt");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 8:
                    do {
                        System.out.println("Nombre de sommets :");
                        nbSommmets = scan.nextInt();
                    } while(nbSommmets < 0);
                    System.out.println("Probabilité de connexion :");
                    Double proba = scan.nextDouble();
                    try {
                        graphe = Graphe.genGraph(1,nbSommmets,proba);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    select = 0;
                    scan_menu.close();
                    scan.close();
                    break;
            }
        } while (select != 0);
        scan_menu.close();
        scan.close();
    }
}