package classes;

import java.util.ArrayList;
import java.util.List;

public class Sommet {
    private Integer index;
    private List<Sommet> voisins;

    public Sommet(Integer index, List<Sommet> voisins){
        this.index = index;
        this.voisins = new ArrayList<>();
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

    public void suppVoisons(Sommet sommet){
        this.voisins.remove(sommet);
    }

    @Override
    public String toString(){
        return "[Voisins : "+ this.voisins + "] \n";
    }
}
