// Geoffrey Auzou, Maxime Frémeaux

package src;

import java.util.ArrayList;
import java.util.List;

public class Sommet {
    private Integer index;
    private String mot;
    private List<Sommet> voisins;

    /**
     * Constructeur de sommet avec index(identifiant)
     * @param index Integer
     */
    public Sommet(Integer index){
        this.index = index;
        this.voisins = new ArrayList<>();
    }

    /**
     * Constructeur de sommet avec index(identifiant) et un mots (tp2 exo2)
     * @param index Integer
     */
    public Sommet(Integer index, String mot){
        this.index = index;
        this.mot = mot;
        this.voisins = new ArrayList<>();
    }

    /**
     * Constructeur de sommet avec un index(identifiant) et une liste des ses voisins
     * @param index Integer
     * @param voisins List<Sommet>
     */
    public Sommet(Integer index, List<Sommet> voisins){
        this.index = index;
        this.voisins = voisins;
    }

    /**
     * Ajoute un voisin à la liste des sommets
     * @param sommet Sommet
     */
    public void addVoisin(Sommet sommet){
        this.voisins.add(sommet);
    }

    /**
     * Vérifie si un voisin existe via son identifiant
     * @param identifiant Integer
     * @return Boolean
     */
    public Boolean voisinExiste(Integer identifiant){
        for(Sommet sommet : this.voisins){
            if(sommet.getIndex().equals(identifiant)){
                return true;
            }
        }
        return false;
    }

    /**
     * supprimer le voisin d'un sommet
     * @param Sommet
     */
    public void suppVoisins(Sommet sommet){
        this.voisins.remove(sommet);
    }

    @Override
    public String toString(){
        return "[ Index : " + this.index + ", Nom : " + this.mot + " ]";
    }

    public List<Sommet> getVoisins(){return this.voisins;}

    public String getMot(){return this.mot;}
    public Integer getIndex(){return this.index;}

    public void setIndex(Integer index) {this.index = index;}
    public void setVoisins(List<Sommet> voisins) {this.voisins = voisins;}
}
