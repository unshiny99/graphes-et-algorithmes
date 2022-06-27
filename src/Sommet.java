// Geoffrey Auzou, Maxime Frémeaux

package src;

import java.util.ArrayList;
import java.util.List;

public class Sommet {
    private Integer index;
    private String mot;
    private Boolean marquer = false;
    private List<Sommet> voisins;
    private Integer time_now = 0;
    private Integer time = 0;
    private Integer cout = 0;

    /**
     * Constructeur de sommet avec index(identifiant)
     * @param index Integer
     */
    public Sommet(Integer index){
        this.index = index;
        this.voisins = new ArrayList<>();
    }

    /**
     * Constructeur de sommet avec index(identifiant) et un coût
     * @param index Integer
     */
    public Sommet(Integer index, Integer cout){
        this.index = index;
        this.cout = cout;
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
        this.cout = cout;
    }

    /**
     * Constructeur de sommet avec un index(identifiant) et une liste des ses voisins
     * @param index Integer
     * @param voisins List<Sommet>
     */
    public Sommet(Integer index, List<Sommet> voisins, Integer cout){
        this.index = index;
        this.voisins = voisins;
        this.cout = cout;
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
            res += " <=> (" + sommet.getIndex() + ", cout = " + sommet.getCout() + ")";
        }
        return res;
    }


    public String AffichageFormatListe(){
        return "[ Index : " + this.index + ", Voisins" + formaString() + " ]";
    }

    public String AffichageFormatMartice(){
        return "[ Index : " + this.index + ", Nom : " + this.mot + " ]\n";
    }

    public List<Sommet> getVoisins(){return this.voisins;}

    public Integer getCout() {return this.cout;}
    public String getMot(){return this.mot;}
    public Integer getIndex(){return this.index;}
    public Boolean getMarquer(){return this.marquer;}
    public Integer getTimeNow(){return this.time_now;}
    public Integer getTime(){return this.time;}

    public void setCout(Integer cout) {this.cout = cout;}
    public void setMarquer(Boolean marquer){this.marquer = marquer;}
    public void setIndex(Integer index) {this.index = index;}
    public void setVoisins(List<Sommet> voisins) {this.voisins = voisins;}
    public void setTimeNow(Integer time) {this.time_now = time;}
    public void setTime(Integer time) {this.time = time;}
}
