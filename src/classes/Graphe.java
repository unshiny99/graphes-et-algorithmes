package src.classes;

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
        return graphe;
    }

    public void addConnexion(Integer a, Integer b){
        for(Sommet sommet : this.listeSuccesseurs){
            if(sommet.getIndex().equals(a)){
                sommet.addVoisin(this.getSommet(b));
            }
        }
    }

    public void suppConnexion(Integer a, Integer b){
        for(Sommet sommet : this.listeSuccesseurs){
            if(sommet.getIndex().equals(a)){
                sommet.suppVoisons(this.getSommet(b));
            }
        }
    }

    public void addSommet(Sommet sommet){
        if(!(this.listeSuccesseurs.contains(sommet))){
            this.listeSuccesseurs.add(sommet);
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
}
