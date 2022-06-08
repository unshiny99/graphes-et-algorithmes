// Geoffrey Auzou, Maxime Frémeaux

package src.classes_tp1;

import java.util.ArrayList;
import java.util.List;

public class Sommet {
    private Integer index;
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

    /**
     * Mise en forme de l'affichage de chaque voisin 
     * @return String
     */
    private String formaString(){
        String res = "";

        for(Sommet sommet : this.voisins){
            res += " => " + sommet.getIndex();
        }
        return res;
    }

    @Override
    public String toString(){
        return "[ Index : " + this.index + ", Voisins" + formaString() + " ]";
    }

    public List<Sommet> getVoisins(){return this.voisins;}

    public Integer getIndex(){return this.index;}

    public void setIndex(Integer index) {this.index = index;}
    public void setVoisins(List<Sommet> voisins) {this.voisins = voisins;}
}
