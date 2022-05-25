public class Main {
    public static void creerGraphe(String type, int nbSommets) {
        Graphe graphe = new Graphe(type, nbSommets);
    }

    public static void main(String[] args) {
        creerGraphe("Orienté", 0);
        creerGraphe("Non orienté", 0);
    }
}
