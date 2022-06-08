// Geoffrey Auzou, Maxime Frémeaux
package src.classes_tp2;

import java.util.Random;

public class Matrice {
    private Integer type;
    private Integer nbSommets;
    private Integer nbConnexion;
    private Random random;

    private Integer[][] matrice;

    /**
     * Constructeur d'un Graph avec un type, un nombre de sommet, un nombre de connexion
     * @param type Integer
     * @param nbSommets Integer
     * @param nbConnexion Integer
     * @param matrice Integer[][]
     */
    public Matrice(Integer type, Integer nbSommets, Integer nbConnexion, Integer[][] matrice){
        this.type = type;
        this.nbSommets = nbSommets;
        this.nbConnexion = nbConnexion;
        this.matrice = matrice;
        this.random = new Random();
    }

    /**
     * Constructeur d'un Graphe avec un type, et un nombre de sommet(s)
     * @param type Integer
     * @param nbSommets Integer
     */
    public Matrice(Integer type, Integer nbSommets) {
        this.type = type;
        this.nbSommets = nbSommets;
        this.nbConnexion = 0;
        this.random = new Random();
    }
    
    /**
     * Constructeur d'un Graphe avec un type
     * @param type Integer
     * @param nbSommets Integer
     */
    public Matrice(Integer type) {
        this.type = type;
        this.nbConnexion = 0;
        this.random = new Random();
    }

    /**
     * Constructeur d'un Graphe
     */
    public Matrice() {
        this.type = null;
        this.nbConnexion = 0;
        this.random = new Random();
    }

    /**
     * Affichage du graphe
     */
    public void affichage(){
        System.out.println("Type : 0 = Non Orienté, 1 = Orienté \n" +
                            "[\n\ttype = " + this.type +
                            ", \n\tnb sommet(s) = " + this.nbSommets +
                            ", \n\tMatrice : " + this.matrice +
                            "\n]"
        );
    }

    public Integer getType() {return type;}
    public Integer getNbSommets() {return nbSommets;}
    public Integer getNbConnexion() {return nbConnexion;}

    public void setType(Integer type) {this.type = type;}
    public void setNbSommets(Integer nbSommets) {this.nbSommets = nbSommets;}
    public void setNbConnexion(Integer nbConnexion) {this.nbConnexion = nbConnexion;}
    public void setListeSuccesseurs(Integer[][] matrice) {this.matrice = matrice;}

    public Integer[][] getMatrice() {return this.matrice;}
}
