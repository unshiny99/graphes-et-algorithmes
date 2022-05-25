package src;

import java.util.ArrayList;
import java.util.List;

public class Graphe {
    private String type;
    private Integer nbSommets;
    private Integer nbConnexion;

    private List<List<Sommet>> listeSuccesseurs;

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

    public void addConnexion(Sommet a, Sommet b){
        for(List<Sommet> liste : this.listeSuccesseurs){
            for(Sommet sommet : liste){
                if(sommet.equals(a)){
                    sommet.addVoisin(b);
                }
            }
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

    public List<List<Sommet>> getListeSuccesseurs() {
        return listeSuccesseurs;
    }

    public void setListeSuccesseurs(List<List<Sommet>> listeSuccesseurs) {
        this.listeSuccesseurs = listeSuccesseurs;
    }
}
