// Geoffrey Auzou, Maxime Frémeaux

package src;

import src.classes.Graphe;
import src.classes.Fichier;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan_menu = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        
        Integer select = null;
        Integer type = null;
        Integer nbSommet = null;
        Integer nbConnexion = null;

        Integer identifiant_a = null;
        Integer identifiant_b = null;
        
        Double proba = null;

        String name_file = null;

        Graphe graphe = null;
        
        do{
            if(select != null){
                System.out.println("[Terminé]");
            }

            System.out.println("--------------------------------------------------------\n" +
                               "|                   MENU TP GRAPHE                      |\n" +
                               "---------------------------------------------------------\n" +
                               "|0  : STOP                                              |\n" +
                               "|1  : MODE : Afficher graphe                            |\n" +
                               "|2  : MODE : Création graphe (avec type/nombre sommets) |\n" +
                               "|3  : MODE : Ajout connexion                            |\n" +
                               "|4  : MODE : Ajout sommet                               |\n" +
                               "|5  : MODE : Charger graphe                             |\n" +
                               "|6  : MODE : Sauvegarde du graphe                       |\n" +
                               "|7  : MODE : Suppression connexion                      |\n" +
                               "|8  : MODE : Suppression instance graphe                |\n" + 
                               "|9  : MODE : Génération aléatoire graphe                |\n" +
                               "|10 : MODE : Vérification adjacent direct               |\n" +
                               "--------------------------------------------------------");
            try {
                select = scan_menu.nextInt();
            } catch(Exception e) {
                System.out.println("Erreur de paramètre d'entrée.");
                scan_menu.close();
                scan.close();
                break;
            }
            switch(select){
                case 0:
                    scan_menu.close();
                    scan.close();
                    break;
                case 1:
                    if(graphe != null) {
                        graphe.affichage();
                    } else {
                        System.out.println("Aucun graphe instancié !");
                    }
                    break;
                case 2:
                    System.out.println("Choisir type : 0(Non Orienté), 1(Orienté)");
                    type = scan.nextInt();
                    System.out.println("Choisir nombre sommet(s) : ");
                    nbSommet = scan.nextInt();

                    graphe = new Graphe(type, nbSommet);
                    graphe.addNbSommet(nbSommet);
                    break;
                case 3:
                    if(graphe != null) {
                        System.out.println("Liste identifiants : " + graphe.getIdentifiantAll());
                        System.out.println("Choisir identifiant source :");
                        identifiant_a = scan.nextInt();
                        System.out.println("Choisir identifiant cible :");
                        identifiant_b = scan.nextInt();

                        graphe.addConnexion(identifiant_a, identifiant_b);
                    } else {
                        System.out.println("Aucun graphe instancié !");
                    }
                    break;
                case 4:
                    if(graphe != null) {
                        System.out.println("Liste identifiants : " + graphe.getIdentifiantAll());
                        System.out.println("Choisir identifiant du sommet : ");
                        identifiant_a = scan.nextInt();
                        
                        graphe.addSommet(identifiant_a);
                    } else {
                        System.out.println("Aucun graphe instancié !");
                    }
                    break;
                case 5:
                    System.out.println("Choisir nom fichier : ");
                    Path dir = Paths.get("./data/");

                    try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.txt")) {
                        for (Path file : stream) {
                            System.out.println(file.getFileName());
                        }
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                    name_file = scan.next();
                    graphe = Fichier.chargerGraphe(name_file);
                    break;
                case 6:
                    if(graphe != null) {
                        System.out.println("Choisir nom fichier : ");
                        name_file = scan.next();

                        graphe.sauvegarderGraphe(name_file);
                    } else {
                        System.out.println("Aucun graphe instancié !");
                    }
                    break;
                case 7:
                    if(graphe != null) {
                        System.out.println("Liste identifiants : " + graphe.getIdentifiantAll());
                        System.out.println("Choisir identifiant source :");
                        identifiant_a = scan.nextInt();
                        System.out.println("Choisir identifiant cible :");
                        identifiant_b = scan.nextInt();

                        graphe.suppConnexion(identifiant_a, identifiant_b);     
                    } else {
                        System.out.println("Aucun graphe instancié !");
                    }
                    break;
                case 8:
                    if(graphe == null) {
                        System.out.println("Instance du graphe déjà nulle");
                    } else {
                        graphe = null;
                    }
                    break;
                case 9:
                    if(graphe == null) {
                        System.out.println("Choisir type : 0(Non Orienté), 1(Orienté)");
                        type = scan.nextInt();
                        System.out.println("Choisir nombre sommets : ");
                        nbSommet = scan.nextInt();
                        System.out.println("Choisir une probabilité 0,0 <= p <= 1,0 : (Exemple : 0,4 et non 0.4)");
                        proba = scan.nextDouble();

                        graphe = new Graphe(type);
                        graphe.generationAleatoire(nbSommet, proba);
                    } else {
                        System.out.println("Le graphe est déjà instancié !");
                    }
                    break;
                case 10:
                    if(graphe != null) {
                        System.out.println("Liste identifiants : " + graphe.getIdentifiantAll());
                        System.out.println("Choisir identifiant source :");
                        identifiant_a = scan.nextInt();
                        System.out.println("Choisir identifiant cible :");
                        identifiant_b = scan.nextInt();
                        
                        Boolean estAdjacent = graphe.estAdjacentDirect(identifiant_a, identifiant_b);
                        if(estAdjacent) {
                            System.out.println("Les sommets sont adjacents direct.");
                        } else {
                            System.out.println("Les sommets ne sont pas adjacents direct.");
                        }
                    } else {
                        System.out.println("Aucun graphe instancié !");
                    }
                    break;
                default:
                    select = 0;
                    scan_menu.close();
                    scan.close();
                    break;
            }
        } while(select != 0);

        scan_menu.close();
        scan.close();
    }
}
