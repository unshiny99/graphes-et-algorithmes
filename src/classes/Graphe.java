// Geoffrey Auzou, Maxime Frémeaux
package src.classes;
//package classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;

public class Graphe {
    private String type;
    private Integer nbSommets;
    private Integer nbConnexion;
    private Random random;

    private List<Sommet> listeSuccesseurs;

    /**
     * Constructeur d'un Graph avec un type, un nombre de sommet, un nombre de connexion
     * @param type String
     * @param nbSommets Integer
     * @param nbConnexion Integer
     */
    public Graphe(String type, Integer nbSommets, Integer nbConnexion){
        this.type = type;
        this.nbSommets = nbSommets;
        this.nbConnexion = nbConnexion;
        this.listeSuccesseurs = new ArrayList<>();
        this.random = new Random();
    }

    /**
     * Constructeur d'un Graphe avec un type, et un nombre de sommet(s)
     * @param type String
     * @param nbSommets Integer
     */
    public Graphe(String type, Integer nbSommets) {
        this.type = type;
        this.nbSommets = nbSommets;
        this.nbConnexion = 0;
        this.listeSuccesseurs = new ArrayList<>();
        this.random = new Random();
    }

    /**
     * Création d'un graphe avec un type et un nombre de sommet
     * @param type String
     * @param nbSommets Integer
     * @return
     */
    public static Graphe creerGraphe(String type, int nbSommets) {
        Graphe graphe = new Graphe(type, nbSommets);
        for (int i=0;i<nbSommets;i++) {
            Sommet sommet = new Sommet(i+1, new ArrayList<Sommet>());
            graphe.listeSuccesseurs.add(sommet);
        }
        return graphe;
    }

    /**
     * Generation aléatoire d'un graphe avec n nombre de sommet, p proba de génération d'une connexion
     * @param n Integer
     * @param p Double
     */
    public void generationAleatoire(Integer n, Double p){
        Graphe graphe = new Graphe("Orienté", n);
        for(int i = 0; i<n; ++i){
            graphe.listeSuccesseurs.add(new Sommet(i, new ArrayList<Sommet>()));
        }

        for(int i = 0; i < graphe.listeSuccesseurs.size(); ++i){
            Double double1 = random.nextDouble();

            if(double1 < p && i < graphe.listeSuccesseurs.size() - 1){
                addConnexion(this.listeSuccesseurs.get(i).getIndex(),
                graphe.listeSuccesseurs.get(i+1).getIndex());
            }
        }
    }

    /**
     * Ajout d'une connexion par identifiants de sommets
     * @param identifiant_a Integer
     * @param identifiant_b Integer
     */
    public void addConnexion(Integer identifiant_a, Integer identifiant_b){
        if(this.type.equals("Orienté")) {
            for(Sommet sommet : this.listeSuccesseurs){
                if(sommet.getIndex().equals(identifiant_a)) {
                    sommet.addVoisin(new Sommet(identifiant_b));
                    this.nbConnexion += 1;
                }
            }
        } else {
            for(Sommet sommet : this.listeSuccesseurs) {
                if(sommet.getIndex().equals(identifiant_a)) {
                    sommet.addVoisin(new Sommet(identifiant_b));
                    // insertion autre sens
                    this.getSommet(identifiant_b).addVoisin(sommet);
                    this.nbConnexion += 1;
                }
            }
        }
    }

    /**
     * Suppresion d'une connexion
     * @param a Integer
     * @param b Integer
     */
    public void suppConnexion(Integer a, Integer b){
        for(Sommet sommet : this.listeSuccesseurs){
            if(sommet.getIndex().equals(a)){
                sommet.suppVoisins(this.getSommet(b));
                this.nbConnexion -= 1;
            }
        }
    }

    /**
     * Ajout un sommet
     * @param identifiant Integer
     */
    public void addSommet(Integer identifiant){
        Boolean existe = false;
        for(Sommet sommet : this.listeSuccesseurs){
            if(sommet.getIndex().equals(identifiant)){
                existe = true;
            }
        }

        if(!existe){
            this.listeSuccesseurs.add(new Sommet(identifiant, new ArrayList<>()));
        }
    }

    /**
     * vérifie si un sommet est adjacent à un autre
     * @param s1 Sommet
     * @param s2 Sommet
     * @return vrai si adjacent direct, faux sinon
     */
    public boolean estAdjacentDirect(int s1, int s2) {
        Sommet sommetRef = null;
        Sommet sommetDest= null;

        for (Sommet sommet : listeSuccesseurs) {
            if (sommet.getIndex().equals(s1)) {
                sommetRef = sommet;
            }
            if (sommet.getIndex().equals(s1)) {
                sommetDest = sommet;
            }
        }
        if (sommetRef != null && sommetDest != null) {
            return sommetRef.getVoisins().contains(sommetDest);
        }
        return false;
    }

    /**
     * Retourne un sommet via son identifiant
     * @param identifiant Integer
     * @return Sommet
     */
    public Sommet getSommet(Integer identifiant){
        for(Sommet sommet : this.listeSuccesseurs){
            if(sommet.getIndex().equals(identifiant)){
                return sommet;
            }
        }
        System.out.println("Sommet : " + identifiant + " n'existe pas");
        return null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNbSommets() {
        return nbSommets;
    }

    public void setNbSommets(Integer nbSommets) {
        this.nbSommets = nbSommets;
    }

    public Integer getNbConnexion() {
        return nbConnexion;
    }

    public void setNbConnexion(Integer nbConnexion) {
        this.nbConnexion = nbConnexion;
    }

    public List<Sommet> getListeSuccesseurs() {
        return listeSuccesseurs;
    }

    public void setListeSuccesseurs(List<Sommet> listeSuccesseurs) {
        this.listeSuccesseurs = listeSuccesseurs;
    }

    /**
     * Affichage de la liste des seccesseurs
     * @return String
     */
    public String afficherListeSuccesseurs() {
        StringBuilder s = new StringBuilder();
        for (Sommet sommet : listeSuccesseurs) {
            s.append("\n\t\t").append(sommet);
        }
        return s.toString();
    }

    /**
     * Affichage du graphe
     */
    public void affichage(){
        System.out.println("[\n\ttype = " + this.type +
                            ", \n\tnb sommet(s) = " + this.nbSommets +
                            ", \n\tliste d'adjacence : " + this.afficherListeSuccesseurs() +
                            "\n]"
        );
    }

    /**
     * sauvegarder un graphe dans un fichier texte
     * @param nomFic String
     */
    public void sauvegarderGraphe(String nomFic) {
        try {
            File fichier = new File("data/" + nomFic);
            if(fichier.createNewFile()) {
                FileWriter myWriter = new FileWriter("data/" + nomFic);
                if(this.type.equals("Orienté")) {
                    myWriter.write("1 " + this.getNbSommets().toString() + " "  + this.getNbConnexion().toString() + "\n");
                } else {
                    myWriter.write("0 " + this.getNbSommets().toString() + " " + this.getNbConnexion().toString() + "\n");
                }
                for(Sommet s : this.listeSuccesseurs) {
                    for(Sommet v : s.getVoisins()) {
                        myWriter.write(s.getIndex().toString() + " " + v.getIndex().toString() + "\n");
                    }
                }
                myWriter.close();
            } else {
                System.out.println("Le fichier existe déjà !");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
