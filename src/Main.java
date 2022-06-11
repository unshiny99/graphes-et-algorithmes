// Geoffrey Auzou, Maxime Frémeaux

package src;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import src.classes_tp1.Fichier;
import src.classes_tp1.Graphe;
import src.classes_tp2.FichierMatrice;
import src.classes_tp2.Matrice;


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
        Matrice matrice = null;
        Path dir = Paths.get("./data/");

        do{
            if(select != null){
                System.out.println("[Terminé]");
            }

            System.out.println("--------------------------------------------------------\n" +
                               "|                    MENU TP GRAPHE                     |\n" +
                               "---------------------------------------------------------\n" +
                               "|0  : STOP                                              |\n" +
                               "---------------------------------------------------------\n" +
                               "|                   LISTE D'ADJACENCE                   |\n" +
                               "---------------------------------------------------------\n" +
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
                               "---------------------------------------------------------\n" +
                               "|                  MATRICE D'ADJACENCE                  |\n" +
                               "---------------------------------------------------------\n" +
                               "|11 : MODE : Afficher graphe                            |\n" +
                               "|12 : MODE : Création graphe (avec type/nombre sommets) |\n" +
                               "|13 : MODE : Ajout connexion                            |\n" +
                               "|14 : MODE : Ajout sommet                               |\n" +
                               "|15 : MODE : Charger graphe                             |\n" +
                               "|16 : MODE : Sauvegarde du graphe                       |\n" +
                               "|17 : MODE : Suppression connexion                      |\n" +
                               "|18 : MODE : Suppression instance graphe                |\n" + 
                               "|19 : MODE : Vérification adjacent direct               |\n" +
                               "|20 : MODE : Parcours de mots (exercice 2)(non fonctionnelle)|\n" +
                               "--------------------------------------------------------"
                               );
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
                    try{
                        System.out.println("Choisir type : 0(Non Orienté), 1(Orienté)");
                        type = scan.nextInt();
                        System.out.println("Choisir nombre sommet(s) : ");
                        nbSommet = scan.nextInt();

                        graphe = new Graphe(type, nbSommet);
                        graphe.addNbSommet(nbSommet);
                    } catch(Exception e) {
                        e.printStackTrace();
                        scan.next();
                    } 
                    break;
                case 3:
                    if(graphe != null) {
                        try{
                            System.out.println("Liste identifiants : " + graphe.getIdentifiantAll());
                            System.out.println("Choisir identifiant source :");
                            identifiant_a = scan.nextInt();
                            System.out.println("Choisir identifiant cible :");
                            identifiant_b = scan.nextInt();

                            graphe.addConnexion(identifiant_a, identifiant_b);
                        } catch(Exception e) {
                            e.printStackTrace();
                            scan.next();
                        }
                    } else {
                        System.out.println("Aucun graphe instancié !");
                    }
                    break;
                case 4:
                    if(graphe != null) {
                        try{
                            System.out.println("Liste identifiants : " + graphe.getIdentifiantAll());
                            System.out.println("Choisir identifiant du sommet : ");
                            identifiant_a = scan.nextInt();
                            
                            graphe.addSommet(identifiant_a);
                        } catch(Exception e) {
                            e.printStackTrace();
                            scan.next();
                        }
                    } else {
                        System.out.println("Aucun graphe instancié !");
                    }
                    break;
                case 5:
                    try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.txt")) {
                        for (Path file : stream) {
                            System.out.println(file.getFileName());
                        }
                        System.out.println("Choisir nom fichier : ");
                        name_file = scan.next();
                        graphe = Fichier.chargerGraphe(name_file);
                    } catch(Exception e) {
                        e.printStackTrace();
                        scan.next();
                    }
                    break;
                case 6:
                    if(graphe != null) {
                        try{
                            System.out.println("Choisir nom fichier : ");
                            name_file = scan.next();

                            graphe.sauvegarderGraphe(name_file);
                        }catch(Exception e){
                            e.printStackTrace();
                            scan.next();
                        }
                    } else {
                        System.out.println("Aucun graphe instancié !");
                    }
                    break;
                case 7:
                    if(graphe != null) {
                        try{
                            System.out.println("Liste identifiants : " + graphe.getIdentifiantAll());
                            System.out.println("Choisir identifiant source :");
                            identifiant_a = scan.nextInt();
                            System.out.println("Choisir identifiant cible :");
                            identifiant_b = scan.nextInt();
                        
                            graphe.suppConnexion(identifiant_a, identifiant_b);  
                        }catch(Exception e){
                            e.printStackTrace();
                            scan.next();
                        }   
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
                    try{
                        if(graphe == null) {
                        System.out.println("Choisir type : 0(Non Orienté), 1(Orienté)");
                        type = scan.nextInt();
                        System.out.println("Choisir nombre sommets : ");
                        nbSommet = scan.nextInt();
                        System.out.println("Choisir une probabilité 0,0 <= p <= 1,0 : p = (nombre de connexions) (Exemple : 0,4 et non 0.4)");
                        proba = scan.nextDouble();

                        graphe = new Graphe(type);
                        graphe.generationAleatoire(nbSommet, proba);
                        } else {
                            System.out.println("Le graphe est déjà instancié !");
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                        scan.next();
                    }
                    break;
                case 10:
                        if(graphe != null) {
                            try{
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
                            }catch(Exception e){
                                e.printStackTrace();
                                scan.next();
                            }
                        } else {
                            System.out.println("Aucun graphe instancié !");
                        }
                    break;
                case 11:
                    if(matrice != null) {
                        matrice.affichage();
                    } else {
                        System.out.println("Aucun graphe (tp2) instancié !");
                    }
                    break;
                case 12:
                    try{
                        System.out.println("Choisir type : 0(Non Orienté), 1(Orienté)");
                        type = scan.nextInt();
                        System.out.println("Choisir nombre sommet(s) : ");
                        nbSommet = scan.nextInt();

                        matrice = new Matrice();
                        matrice.creerGraphe(type,nbSommet);
                    } catch(Exception e) {
                        e.printStackTrace();
                        scan.next();
                    } 
                    break;
                case 13:
                    if(matrice != null) {
                        try{
                            System.out.println("Liste identifiants : " + matrice.getIdentifiantAll());
                            System.out.println("Choisir identifiant source :");
                            identifiant_a = scan.nextInt();
                            System.out.println("Choisir identifiant cible :");
                            identifiant_b = scan.nextInt();

                            matrice.addConnexion(identifiant_a, identifiant_b);
                        } catch(Exception e) {
                            e.printStackTrace();
                            scan.next();
                        }
                    } else {
                        System.out.println("Aucun graphe (tp2) instancié !");
                    }
                    break;
                case 14:
                    if(matrice != null) {
                        try{
                            System.out.println("Liste identifiants : " + matrice.getIdentifiantAll());
                            System.out.println("Choisir identifiant du sommet : ");
                            identifiant_a = scan.nextInt();
                            
                            matrice.addSommet(identifiant_a);
                        } catch(Exception e) {
                            e.printStackTrace();
                            scan.next();
                        }
                    } else {
                        System.out.println("Aucun graphe (tp2) instancié !");
                    }
                    break;
                case 15:
                    try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.txt")) {
                        for (Path file : stream) {
                            System.out.println(file.getFileName());
                        }
                        System.out.println("Choisir nom fichier : ");
                        name_file = scan.next();
                        matrice = FichierMatrice.chargerGraphe(name_file);
                    } catch(Exception e) {
                        e.printStackTrace();
                        scan.next();
                    }
                    break;
                case 16:
                    if(matrice != null) {
                        try{
                            System.out.println("Choisir nom fichier : ");
                            name_file = scan.next();
                            matrice.sauvegarderGraphe(name_file);
                        }catch(Exception e){
                            e.printStackTrace();
                            scan.next();
                        }
                    } else {
                        System.out.println("Aucun graphe instancié !");
                    }
                    break;
                case 17:
                    if(matrice != null) {
                        try{
                            System.out.println("Liste identifiants : " + matrice.getIdentifiantAll());
                            System.out.println("Choisir identifiant source :");
                            identifiant_a = scan.nextInt();
                            System.out.println("Choisir identifiant cible :");
                            identifiant_b = scan.nextInt();
                        
                            matrice.suppConnexion(identifiant_a, identifiant_b);  
                        }catch(Exception e){
                            e.printStackTrace();
                            scan.next();
                        }  
                    }
                    break;
                case 18:
                    if(matrice == null) {
                        System.out.println("Instance du graphe (tp2) déjà nulle");
                    } else {
                        matrice = null;
                    }
                    break;
                case 19:
                    if(matrice != null) {
                        try{
                            System.out.println("Liste identifiants : " + matrice.getIdentifiantAll());
                            System.out.println("Choisir identifiant source :");
                            identifiant_a = scan.nextInt();
                            System.out.println("Choisir identifiant cible :");
                            identifiant_b = scan.nextInt();
                            
                            Boolean estAdjacent = matrice.estAdjacentDirect(identifiant_a, identifiant_b);
                            if(estAdjacent) {
                                System.out.println("Les sommets sont adjacents direct.");
                            } else {
                                System.out.println("Les sommets ne sont pas adjacents direct.");
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                            scan.next();
                        }
                    } else {
                        System.out.println("Aucun graphe (tp2) instancié !");
                    }
                    break;
                case 20:
                    matrice = FichierMatrice.chargerGrapheMots("Mots.txt");
                    try {
                        System.out.println("Liste identifiants : " + matrice.getIdentifiantAll());
                        System.out.println("Choisir identifiant sommet départ :");
                        identifiant_a = scan.nextInt();
                        if(matrice != null) {
                            matrice.parcoursEnLargeur(matrice.getSommetListe(identifiant_a));
                        }
                        System.out.println("Choisir identifiant sommet arrivée :");
                        identifiant_b = scan.nextInt();
                        matrice.trouverPlusCourtChemin(matrice.getSommetListe(identifiant_a), matrice.getSommetListe(identifiant_b));
                    } catch (Exception e) {
                        e.printStackTrace();
                        scan.next();
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
