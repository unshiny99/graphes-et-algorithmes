package src;

public class Main {

    public static void main(String[] args) {
        src.classes.Graphe g1 = src.classes.Graphe.creerGraphe("Orienté", 0);
        src.classes.Graphe g2 = src.classes.Graphe.creerGraphe("Non orienté", 0);
        System.out.println(g1);
        System.out.println(g2);
    }
}
