// Geoffrey Auzou, Maxime Frémeaux
package src.classes_tp1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import src.Sommet;

public class Graphe {
    private Integer type;
    private Integer nbSommets;
    private Integer nbConnexion;
    private Random random;
    private Integer time;

    private List<Sommet> listeSuccesseurs;
    private List<Sommet> new_listeSuccesseurs;

    /**
     * Constructeur d'un Graph avec un type, un nombre de sommet, un nombre de connexion
     * @param type Integer
     * @param nbSommets Integer
     * @param nbConnexion Integer
     */
    public Graphe(Integer type, Integer nbSommets, Integer nbConnexion){
        this.type = type;
        this.nbSommets = nbSommets;
        this.nbConnexion = nbConnexion;
        this.listeSuccesseurs = new ArrayList<>();
        this.random = new Random();
    }

    /**
     * Constructeur d'un Graphe avec un type, et un nombre de sommet(s)
     * @param type Integer
     * @param nbSommets Integer
     */
    public Graphe(Integer type, Integer nbSommets) {
        this.type = type;
        this.nbSommets = nbSommets;
        this.nbConnexion = 0;
        this.listeSuccesseurs = new ArrayList<>();
        this.random = new Random();
    }
    
    /**
     * Constructeur d'un Graphe avec un type
     * @param type Integer
     * @param nbSommets Integer
     */
    public Graphe(Integer type) {
        this.type = type;
        this.nbConnexion = 0;
        this.listeSuccesseurs = new ArrayList<>();
        this.random = new Random();
    }

    /**
     * Constructeur d'un Graphe
     */
    public Graphe() {
        this.type = null;
        this.nbConnexion = 0;
        this.listeSuccesseurs = new ArrayList<>();
        this.random = new Random();
    }


    /**
     * Création d'un graphe avec un type et un nombre de sommet
     * @param type String
     * @param nbSommets Integer
     */
    public void creerGraphe(Integer type, int nbSommets) {
        this.type = type;
        this.nbSommets = nbSommets;

        for (int i = 0; i < nbSommets; i++) {
            this.listeSuccesseurs.add(new Sommet(i+1, new ArrayList<Sommet>()));
        }
    }

    /**
     * Generation aléatoire d'un graphe avec n nombre de sommet, p proba de génération d'une connexion
     * @param n Integer
     * @param p Double
     */
    public void generationAleatoire(Integer n, Double p){
        this.nbSommets = n;
        for(int i = 0; i<n; ++i){
            if(!(checkIdentifiantExiste(i+1))){
                this.listeSuccesseurs.add(new Sommet(i+1, new ArrayList<Sommet>()));
            }
        }

        for(int i = 0; i < this.listeSuccesseurs.size(); ++i){
            Double double1 = random.nextDouble();

            if(double1 < p && i < this.listeSuccesseurs.size() - 1){
                addConnexion(this.listeSuccesseurs.get(i).getIndex(),
                                this.listeSuccesseurs.get(i+1).getIndex());
                this.nbConnexion += 1;
            }
        }
    }

    /**
     * Génération aléatoire de création de connexion
     * @param nbConnexion Integer
     */
    public void generationAleatoireConnexion(Integer nbConnexion){       
        this.nbConnexion += nbConnexion;
        for(int i = 0; i < nbConnexion; ++i){
            this.addConnexion(random.nextInt(this.listeSuccesseurs.size())+1, random.nextInt(this.listeSuccesseurs.size())+1);
        }
    }

    /**
     * Ajout d'une connexion par identifiants de sommets
     * @param identifiant_a Integer
     * @param identifiant_b Integer
     */
    public void addConnexion(Integer identifiant_a, Integer identifiant_b){
        if(this.type.equals(1)) {
            for(Sommet sommet : this.listeSuccesseurs){
                if(sommet.getIndex().equals(identifiant_a)) {
                    if(!(sommet.voisinExiste(identifiant_b))){
                        sommet.addVoisin(new Sommet(identifiant_b));
                        this.nbConnexion += 1;
                    }
                }
            }
        } else {
            for(Sommet sommet : this.listeSuccesseurs) {
                if(sommet.getIndex().equals(identifiant_a)) {
                    sommet.addVoisin(new Sommet(identifiant_b));
                    // insertion autre sens
                    this.getSommet(identifiant_b).addVoisin(sommet);
                    this.nbConnexion += 1;
                }
            }
        }
    }

    /**
     * Suppresion d'une connexion
     * @param a Integer
     * @param b Integer
     */
    public void suppConnexion(Integer a, Integer b) {
        Boolean trouve = false;
        for(Sommet sommet : this.listeSuccesseurs) {
            if(sommet.getIndex().equals(a)) {
                for(Sommet sommetDest : sommet.getVoisins()) {
                    if(sommetDest.getIndex().equals(b)) {
                        sommet.getVoisins().remove(sommetDest);
                        this.nbConnexion -= 1;
                        trouve = true;
                        System.out.println("La connexion a bien été supprimée.");
                        break;
                    }
                }
            }
        }
        if(!trouve) {
            System.out.println("La connexion n'a pas été trouvée.");
        }
    }

    /**
     * Ajout un sommet
     * @param identifiant Integer
     */
    public void addSommet(Integer identifiant){
        if(!(checkIdentifiantExiste(identifiant))){
            this.listeSuccesseurs.add(new Sommet(identifiant, new ArrayList<>()));   
        }else{
            System.out.println("Sommet : " + identifiant + " existe déjà !");
        }
    }

    /**
     * Ajout nb sommet(s) à la liste des voisins/succeseurs
     * @param nbSommet Integer
     */
    public void addNbSommet(Integer nbSommet){
        for(int i = 0; i < nbSommet; ++i){
            if(!(checkIdentifiantExiste(i+1))){
                this.listeSuccesseurs.add(new Sommet(i+1, new ArrayList<>()));   
            }
        }
        
    }

    /**
     * vérifie si un sommet est adjacent à un autre
     * pour un graphe orienté, le sens est pris en compte
     * @param s1 Sommet
     * @param s2 Sommet
     * @return vrai si adjacent direct, faux sinon
     */
    public boolean estAdjacentDirect(int s1, int s2) {
        Sommet sommetRef = null;

        for (Sommet sommet : this.listeSuccesseurs) {
            if (sommet.getIndex().equals(s1)) {
                sommetRef = sommet;
                for(Sommet sommetDest : sommetRef.getVoisins()) {
                    if(sommetDest.getIndex().equals(s2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Retourne un sommet via son identifiant
     * @param identifiant Integer
     * @return Sommet
     */
    public Sommet getSommet(Integer identifiant) {
        for(Sommet sommet : this.listeSuccesseurs) {
            if(sommet.getIndex().equals(identifiant)) {
                return sommet;
            }
        }
        System.out.println("Sommet : " + identifiant + " n'existe pas !");
        return null;
    }

    /**
     * Retourne la liste des identifiants depuis la liste des sucesseurs
     * @return String
     */
    public String getIdentifiantAll(){
        String res = "[";
        for(Sommet sommet : this.listeSuccesseurs){
            res += sommet.getIndex() + " | ";
        }
        res += "]";
        return res.replace(" | ]", "]");
    }


    /**
     * Vérifie si un identifiant existe dans la liste des sucesseurs
     * @param identifiant
     * @return Boolean
     */
    private Boolean checkIdentifiantExiste(Integer identifiant){
        for(Sommet sommet : this.listeSuccesseurs){
            if(sommet.getIndex().equals(identifiant)){
                return true;
            }
        }
        return false;
    }

    /**
     * Affichage de la liste des seccesseurs
     * @return String
     */
    public String afficherListeSuccesseurs() {
        StringBuilder s = new StringBuilder();
        for (Sommet sommet : listeSuccesseurs) {
            s.append("\n\t\t").append(sommet.AffichageFormatListe());
        }
        return s.toString();
    }

    /**
     * Affichage du graphe
     */
    public void affichage(){
        System.out.println("Type : 0 = Non Orienté, 1 = Orienté \n" +
                            "[\n\ttype = " + this.type +
                            ", \n\tnb sommet(s) = " + this.nbSommets +
                            ", \n\tliste d'adjacence : " + this.afficherListeSuccesseurs() +
                            "\n]"
        );
    }

    /**
     * sauvegarder un graphe dans un fichier texte
     * @param nomFic String
     */
    public void sauvegarderGraphe(String nomFic) {
        try {
            File fichier = new File("data/" + nomFic + ".txt");
            if(fichier.createNewFile()) {
                FileWriter myWriter = new FileWriter("data/" + nomFic + ".txt");
                if(this.type.equals(1)) {
                    myWriter.write("1 " + this.getNbSommets().toString() + " "  + this.getNbConnexion().toString() + "\n");
                } else {
                    myWriter.write("0 " + this.getNbSommets().toString() + " " + this.getNbConnexion().toString() + "\n");
                }
                for(Sommet s : this.listeSuccesseurs) {
                    for(Sommet v : s.getVoisins()) {
                        myWriter.write(s.getIndex().toString() + " " + v.getIndex().toString() + "\n");
                    }
                }
                myWriter.close();
            } else {
                System.out.println("Le fichier existe déjà !");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ajout d'une connexion par identifiants de sommets pour une nouvelle liste
     * @param identifiant_a Integer
     * @param identifiant_b Integer
     */
    public void addConnexionNew(Integer identifiant_a, Integer identifiant_b){
        if(this.type.equals(1)) {
            for(Sommet sommet : this.new_listeSuccesseurs){
                if(sommet.getIndex().equals(identifiant_a)) {
                    if(!(sommet.voisinExiste(identifiant_b))){
                        sommet.addVoisin(new Sommet(identifiant_b));
                    }
                }
            }
        } else {
            for(Sommet sommet : this.new_listeSuccesseurs) {
                if(sommet.getIndex().equals(identifiant_a)) {
                    sommet.addVoisin(new Sommet(identifiant_b));
                    this.getSommet(identifiant_b).addVoisin(sommet);
                }
            }
        }
    }

    /*
     * Créer une nouvelle liste avec la mise à jour des connexions
     */
    private void newListe(){
        this.new_listeSuccesseurs = new ArrayList<>();
        for(Sommet sommet : this.listeSuccesseurs){
            this.new_listeSuccesseurs.add(new Sommet(sommet.getIndex()));
        }
    }

    /*
     * Inversion des connexions
     */
    private void reverseConnexion(Sommet source, List<Sommet> voisins){        
        Collections.reverse(voisins);
        for(int i = 0; i < voisins.size(); ++i){
            if(i != voisins.size() - 1){
                this.addConnexionNew(voisins.get(i).getIndex(), source.getIndex());
            }else{
                this.addConnexionNew(voisins.get(i).getIndex(), source.getIndex());
            }
        }
    }

    /*
     * Inversion du graphe
     */
    public void inverse(){
        List<Sommet> voisins = new ArrayList<>();
        this.newListe();

        for(Sommet sommet : this.listeSuccesseurs){
            if(!(sommet.getVoisins().isEmpty())){
                voisins.addAll(sommet.getVoisins());
                this.reverseConnexion(sommet, voisins);
                voisins.clear();
            }
        }

        this.listeSuccesseurs = this.new_listeSuccesseurs;
    }

    public Integer getType() {return type;}
    public Integer getNbSommets() {return nbSommets;}
    public Integer getNbConnexion() {return nbConnexion;}
    public List<Sommet> getListeSuccesseurs() {return listeSuccesseurs;}

    public void setType(Integer type) {this.type = type;}
    public void setNbSommets(Integer nbSommets) {this.nbSommets = nbSommets;}
    public void setNbConnexion(Integer nbConnexion) {this.nbConnexion = nbConnexion;}
    public void setListeSuccesseurs(List<Sommet> listeSuccesseurs) {this.listeSuccesseurs = listeSuccesseurs;}
}
