package src;

import src.classes.Graphe;

public class Main {

    public static void main(String[] args) {
        Graphe g1 = Graphe.creerGraphe("Orienté", 0);
        Graphe g2 = Graphe.creerGraphe("Non orienté", 0);
        System.out.println(g1);
        System.out.println(g2);
    }
}
