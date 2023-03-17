package src;

import java.util.Scanner;

// Bryan Moreau, Maxime Frémeaux, Geoffrey Auzou
public class Main {
    public static void main(String[] args) {
        Scanner scan_menu = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        
        Integer select = null;

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