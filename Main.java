import src.Graphe;

public class Main {
    // Graphe graphe = new Graphe(type, nbSommets, nbConnexion);

    public void creerGraphe(String type, int nbSommets) {
        Graphe graphe = new Graphe(type, nbSommets);
    }

    public static void main(String[] args) {
        this.creerGraphe("Orienté", 0);
        this.creerGraphe("Non orienté", 0);
    }
}
