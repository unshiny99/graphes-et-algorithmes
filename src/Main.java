// package src;

import classes.Graphe;
// import src.classes.Graphe;

public class Main {

    public static void main(String[] args) {
        Graphe g1 = Graphe.creerGraphe("Orienté", 10);
        Graphe g2 = Graphe.creerGraphe("Non orienté", 3);

        g1.affichage();
        g2.affichage();
    }
}
