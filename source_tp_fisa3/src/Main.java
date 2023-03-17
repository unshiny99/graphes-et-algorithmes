// Geoffrey Auzou, Maxime Frémeaux

package src;

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
        Integer cout = null;
        
        Double proba = null;

        String name_file = null;

        // Graphe graphe = null;
        // Graphe_cout graphe_cout = null;
        // Matrice matrice = null;

        // lire les données depuis le dossier data
        Path dir = Paths.get("./data/");

        do{
            if(select != null){
                System.out.println("[Terminé]");
            }

            System.out.println("--------------------------------------------------------\n" +
                               "|                     MENU TP GRAPHES                   |\n" +
                               "---------------------------------------------------------\n" +
                               "|1  : MODE : Afficher graphe                            |\n" +
                               "|2  : MODE : Création graphe (avec nombre sommets)      |\n" +
                               "|3  : MODE : Ajout sommet                               |\n" +
                               "|4  : MODE : Ajout arc                                  |\n" +
                               "|5  : MODE : Adjacents ?                                |\n" +
                               "|6  : MODE : Charger un graphe                          |\n" +
                               "|7  : MODE : Sauvegarde du graphe                       |\n" +
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
