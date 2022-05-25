// Geoffrey Auzou, Maxime Frémeaux

package src.classes;
//package classes;

import java.util.ArrayList;
import java.util.List;

public class Sommet {
    private Integer index;
    private List<Sommet> voisins;

    public Sommet(Integer index){
        this.index = index;
        this.voisins = new ArrayList<>();
    }

    public Sommet(Integer index, List<Sommet> voisins){
        this.index = index;
        this.voisins = voisins;
    }

    public void addVoisin(Sommet sommet){
        this.voisins.add(sommet);
    }

    public List<Sommet> getVoisins(){
        return this.voisins;
    }

    public Integer getIndex(){
        return this.index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public void setVoisins(List<Sommet> voisins) {
        this.voisins = voisins;
    }

    /**
     * supprimer les voisins d'un sommet
     * @param sommet
     */
    public void suppVoisins(Sommet sommet){
        this.voisins.remove(sommet);
    }

    @Override
    public String toString(){
        return "[ Index : " + this.index + ", Voisins => " + this.voisins + " ]";
    }
}
