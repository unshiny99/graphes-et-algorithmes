// package src.classes
package classes;

import java.util.ArrayList;
import java.util.List;

public class Graphe {
    private String type;
    private Integer nbSommets;
    private Integer nbConnexion;

    private List<Sommet> listeSuccesseurs;

    public Graphe(String type, Integer nbSommets, Integer nbConnexion){
        this.type = type;
        this.nbSommets = nbSommets;
        this.nbConnexion = nbConnexion;
        this.listeSuccesseurs = new ArrayList<>();
    }

    public Graphe(String type, Integer nbSommets) {
        this.type = type;
        this.nbSommets = nbSommets;
        this.nbConnexion = 0;
        this.listeSuccesseurs = new ArrayList<>();
    }

    public static Graphe creerGraphe(String type, int nbSommets) {
        Graphe graphe = new Graphe(type, nbSommets);
        for (int i=0;i<nbSommets;i++) {
            Sommet sommet = new Sommet(i, new ArrayList<Sommet>());
            graphe.listeSuccesseurs.add(sommet);
        }
        return graphe;
    }

    public void addConnexion(Sommet a, Sommet b){
        for(Sommet sommet : this.listeSuccesseurs){
            if(sommet.equals(a)){
                sommet.addVoisin(b);
            }
        }
    }

    public void suppConnexion(Sommet a, Sommet b){
        for(Sommet sommet : this.listeSuccesseurs){
            if(sommet.equals(a)){
                sommet.suppVoisins(b);
            }
        }
    }

    public void addSommet(Sommet sommet){
        if(!(this.listeSuccesseurs.contains(sommet))){
            this.listeSuccesseurs.add(sommet);
        }
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
}
