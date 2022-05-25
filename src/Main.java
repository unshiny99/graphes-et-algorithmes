import classes.Graphe;

public class Main {
    public static void creerGraphe(String type, int nbSommets) {
        Graphe graphe = new Graphe(type, nbSommets);
    }

    public static void main(String[] args) {
        Graphe g1 = Graphe.creerGraphe("Orienté", 0);
        Graphe g2 = Graphe.creerGraphe("Non orienté", 0);
        System.out.println(g1);
        System.out.println(g2);
    }
}
