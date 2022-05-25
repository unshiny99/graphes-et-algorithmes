package src;

import src.classes.Graphe;

public class Main {

    public static void main(String[] args) {
        Graphe g1 = Graphe.creerGraphe("Orienté", 0);
        Graphe g2 = Graphe.creerGraphe("Non orienté", 0);
        // System.out.println(g1);
        // System.out.println(g2);

        // g1.addSommet(11);
        // g1.addSommet(12);

        // System.out.println("---- After addSommet ----");
        // g1.Affichage();

        // g1.addConnexion(1, 2);
        // g1.addConnexion(2, 3);
        
        // System.out.println("---- After addConnexion ----");
        // g1.Affichage();

        // g1.suppConnexion(2, 3);

        // System.out.println("---- After suppConnexion ----");

        g1.generationAleatoire(10, 0.5);
        g1.Affichage();
    }
}
