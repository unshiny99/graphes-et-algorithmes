package src.classes;
// package classes;

import java.util.ArrayList;
import java.util.List;

import java.util.Random;

public class Graphe {
    private String type;
    private Integer nbSommets;
    private Integer nbConnexion;
    private Random random;

    private List<Sommet> listeSuccesseurs;

    public Graphe(String type, Integer nbSommets, Integer nbConnexion){
        this.type = type;
        this.nbSommets = nbSommets;
        this.nbConnexion = nbConnexion;
        this.listeSuccesseurs = new ArrayList<>();
        this.random = new Random();
    }

    public Graphe(String type, Integer nbSommets) {
        this.type = type;
        this.nbSommets = nbSommets;
        this.nbConnexion = 0;
        this.listeSuccesseurs = new ArrayList<>();
        this.random = new Random();
    }

    public static Graphe creerGraphe(String type, int nbSommets) {
        Graphe graphe = new Graphe(type, nbSommets);
        for (int i=0;i<nbSommets;i++) {
            Sommet sommet = new Sommet(i, new ArrayList<Sommet>());
            graphe.listeSuccesseurs.add(sommet);
        }
        return graphe;
    }

    public void generationAleatoire(Integer n, Double p){
        for(int i = 0; i<n; ++i){
            this.listeSuccesseurs.add(new Sommet(i, new ArrayList<Sommet>()));
        }

        for(int i = 0; i < this.listeSuccesseurs.size(); ++i){
            Double double1 = random.nextDouble(1);
            
            if(double1 < p && i < this.listeSuccesseurs.size() - 1){
                addConnexion(this.listeSuccesseurs.get(i).getIndex(), 
                this.listeSuccesseurs.get(i+1).getIndex());
            }
        }
    }

    public void addConnexion(Integer identifiant_a, Integer identifiant_b){
        for(Sommet sommet : this.listeSuccesseurs){
            if(sommet.getIndex().equals(identifiant_a)){
                sommet.addVoisin(this.getSommet(identifiant_b));
            }
        }
    }

    public void suppConnexion(Integer a, Integer b){
        for(Sommet sommet : this.listeSuccesseurs){
            if(sommet.getIndex().equals(a)){
                sommet.suppVoisins(this.getSommet(b));
            }
        }
    }

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

    public Sommet getSommet(Integer identifiant){
        for(Sommet sommet : this.listeSuccesseurs){
            if(sommet.getIndex().equals(identifiant)){
                return sommet;
            }
        }
        System.out.println("Sommet : " + identifiant + "n'éxiste pas");
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

    public void Affichage(){
        System.out.println("[type = " + this.type + 
                            ", nb sommet = " + this.nbSommets +
                            ", liste adjacence : " + this.listeSuccesseurs);
    }
}
