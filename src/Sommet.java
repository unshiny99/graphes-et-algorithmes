package src;

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

    public List<Sommet> getListeVoisin(){
        return this.voisins;
    }

    @Override
    public String toString(){
        return "[Voisins : " this.voisins + "] \n";
    }
}
