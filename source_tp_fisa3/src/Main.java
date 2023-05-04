package src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Bryan Moreau, Maxime Frémeaux, Geoffrey Auzou
public class Main {
    public static void main(String[] args) {
        Scanner scan_menu = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        Integer select = null;
        Graphe graphe = null;
        BackPack backpack = null;

        Double proba;
        do {
            if (select != null) {
                System.out.println("[Terminé]");
            }
            System.out.println(
                    "-----------------------------------------------------------\n" +
                            "|                      MENU TP GRAPHES                     |\n" +
                            "-----------------------------------------------------------\n" +
                            "|1  : MODE : Afficher graphe                               |\n" +
                            "|2  : MODE : Création graphe (avec nombre sommets)         |\n" +
                            "|3  : MODE : Ajout sommet                                  |\n" +
                            "|4  : MODE : Ajout arc                                     |\n" +
                            "|5  : MODE : Sont adjacents ?                              |\n" +
                            "|6  : MODE : Charger un graphe                             |\n" +
                            "|7  : MODE : Sauvegarde du graphe                          |\n" +
                            "|8  : MODE : Plus court chemin (Bellman-Ford)              |\n" +
                            "|9  : MODE : Générer un graphe (Erdös-Rényi)               |\n" +
                            "|10 : MODE : Générer un graphe amélioré (puits et source)  |\n" +
                            "|11 : MODE : Exporter le graphe pour CPLEX                 |\n" +
                            "|12 : MODE : Calcul du flow maximum (Ford-Fulkerson)       |\n" +
                            "|13 : MODE : Charger un sac à dos                          |\n" +
                            "|14 : MODE : Sauvegarde du sac à dos                       |\n" +
                            "|15 : MODE : Problème du sac à dos disjonctif              |\n" +
                            "|16 : MODE : Générer un sac à dos                          |\n" +
                            "|17 : MODE : Exporter le sac à dos pour CPLEX              |\n" +
                            "|0  : QUITTER                                              |\n" +
                            "-----------------------------------------------------------");
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
                    if (graphe != null) {
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
                        graphe = new Graphe(1, nbSommmets);
                    } while (nbSommmets < 0);
                    break;
                case 3:
                    // ajouter sommet
                    if (graphe != null) {
                        graphe.showGraph();
                        Integer indexSommet = 0;
                        System.out.println("Index du nouveau sommet :");
                        indexSommet = scan.nextInt();
                        graphe.addSommet(indexSommet, true);
                    }
                    break;
                case 4:
                    // ajouter arc
                    if (graphe != null) {
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
                    if (graphe != null) {
                        graphe.showGraph();
                        System.out.println("Index du premier sommet (origine) :");
                        Integer indexSommet1 = scan.nextInt();
                        System.out.println("Index du second sommet (destination) :");
                        Integer indexSommet2 = scan.nextInt();
                        Boolean estAdjacent = graphe.isDirect(indexSommet1, indexSommet2);
                        if (estAdjacent) {
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
                    // Bellman-Ford
                    if (graphe != null) {
                        Integer destination = -1;
                        Integer source = -1;
                        do {
                            System.out.println("Sommet source :");
                            source = scan.nextInt();
                        } while (source < 0);

                        do {
                            System.out.println("Sommet de destination :");
                            destination = scan.nextInt();
                        } while (destination < 0);

                        List<Object> result = graphe.methodeBellmanFord(source);

                        // Prise en compte du résultat
                        List<Double> longeurChemin = (List<Double>) result.get(0);
                        Map<Integer, Integer> predecesseurs = (Map<Integer, Integer>) result.get(1);

                        // System.out.println("Longueur plus court chemin : " + longeurChemin);
                        // System.out.println("Liste de prédecesseurs : " + predecesseurs);

                        if (longeurChemin.get(destination - 1) == Double.MAX_VALUE) {
                            System.out.println(
                                    "Il n'y a pas de solution valide pour s=" + source + " et t=" + destination);
                        } else {
                            // Sauvegarde de la solution
                            try {
                                File file = new File("source_tp_fisa3/data/sol_reseau.txt");
                                BufferedWriter bw = new BufferedWriter(new FileWriter(file));

                                Integer u = destination;
                                String parcours = "";
                                while (u != null) {
                                    parcours = u + " " + parcours;
                                    u = predecesseurs.get(u);
                                }

                                bw.write(parcours);
                                bw.newLine();

                                bw.write(longeurChemin.get(destination - 1) + "");
                                bw.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    } else {
                        System.out.println("Merci d'instancier un graphe");
                    }
                    break;
                case 9:
                    // Générer un graphe (Erdös-Rényi)
                    do {
                        System.out.println("Nombre de sommets :");
                        nbSommmets = scan.nextInt();
                    } while (nbSommmets < 0);
                    System.out.println("Probabilité de connexion :");
                    proba = scan.nextDouble();
                    try {
                        graphe = Graphe.genGraph(1, nbSommmets, proba);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 10:
                    // Générer un graphe amélioré (avec puits et source)
                    do {
                        System.out.println("Nombre de sommets :");
                        nbSommmets = scan.nextInt();
                    } while (nbSommmets < 0);
                    System.out.println("Probabilité de connexion :");
                    proba = scan.nextDouble();
                    try {
                        graphe = Graphe.genGraph2(1, nbSommmets, proba);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 11:
                    // exporter le graphe dans un format valide pour CPLEX (.dat)
                    if (graphe != null) {
                        Integer destination = -1;
                        Integer source = -1;
                        do {
                            System.out.println("Sommet source :");
                            source = scan.nextInt();
                        } while (source < 0);

                        do {
                            System.out.println("Sommet de destination :");
                            destination = scan.nextInt();
                        } while (destination < 0);
                        try {
                            graphe.exportCPLEX(source, destination);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Merci d'instancier un graphe");
                    }
                    break;
                case 12:
                    // Ford-Fulkerson
                    Integer destination = -1;
                    Integer source = -1;
                    do {
                        System.out.println("Sommet source :");
                        source = scan.nextInt();
                    } while (source < 0);
                    do {
                        System.out.println("Sommet de destination :");
                        destination = scan.nextInt();
                    } while (destination < 0);
                    System.out.println("Flow maximum : " + graphe.algoFordFulkerson(source, destination));
                    break;
                case 13:
                    try {
                        backpack = BackPack.loadBackPack("source_tp_fisa3/data/mon_backpack.txt");
                        backpack.showBackPack();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 14:
                    // sauvegarder un sac à dos
                    try {
                        backpack.saveBackpack("source_tp_fisa3/data/saved_backpack.txt");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 15:
                    // Problème sac à dos disjonctif
                    if (backpack != null) {
                        backpack.sacADosDisjonctif();
                    } else {
                        System.out.println("Merci d'instancier un sac à dos");
                    }
                    break;
                case 16:
                    do {
                        System.out.println("Nombre de sommets :");
                        nbSommmets = scan.nextInt();
                    } while (nbSommmets < 0);
                    System.out.println("Capacité maximal du sac :");
                    Double poidMax = scan.nextDouble();
                    try {
                        backpack = BackPack.genBackPack(nbSommmets, poidMax);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 17:
                    // exporter le graphe dans un format valide pour CPLEX (.dat)
                    if (backpack != null) {
                        try {
                            backpack.exportCPLEX();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Merci d'instancier un graphe");
                    }
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