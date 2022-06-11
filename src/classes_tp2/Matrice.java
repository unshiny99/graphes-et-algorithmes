// Geoffrey Auzou, Maxime Frémeaux
package src.classes_tp2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import src.Sommet;

public class Matrice {
    private Integer type;
    private Integer nbSommets;
    private Integer nbConnexion;
    private List<Sommet> sommets;

    private Integer[][] matrice;

    private List<Integer> c = new ArrayList<>();
    private List<Integer> d = new ArrayList<>();
    private List<Sommet> p = new ArrayList<>();
    private List<Sommet> f = new ArrayList<>();

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
        this.sommets = new ArrayList<>();
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
        this.matrice = new Integer[nbSommets][nbSommets];
        this.sommets = new ArrayList<>();
    }
    
    /**
     * Constructeur d'un Graphe avec un type
     * @param type Integer
     * @param nbSommets Integer
     */
    public Matrice(Integer type) {
        this.type = type;
        this.nbConnexion = 0;
        this.sommets = new ArrayList<>();
    }

    /**
     * Constructeur d'un Graphe
     */
    public Matrice() {
        this.type = null;
        this.nbConnexion = 0;
        this.sommets = new ArrayList<>();
    }

    /**
     * Ajout d'une connexion par identifiants de sommets
     * @param identifiant_a Integer
     * @param identifiant_b Integer
     */
    public void addConnexion(Integer identifiant_a, Integer identifiant_b){
        Integer indexSommetSource = null;
        Integer indexSommetDest = null;

        for(Sommet sommet : this.sommets){
            if(sommet.getIndex().equals(identifiant_a)) {
                indexSommetSource = this.sommets.indexOf(sommet);
            }
            if(sommet.getIndex().equals(identifiant_b)) {
                indexSommetDest = this.sommets.indexOf(sommet);
            }
        }
        if(indexSommetSource != null && indexSommetDest != null) {
            this.nbConnexion += 1;
            if(this.type.equals(1)) { // orienté
                // ajout de la connexion dans la matrice
                this.matrice[indexSommetSource][indexSommetDest] = 1;
            } else { // non orienté
                // ajout des 2 connexions dans la matrice
                this.matrice[indexSommetSource][indexSommetDest] = 1;
                this.matrice[indexSommetDest][indexSommetSource] = 1;
            }
        }
    }

    /**
     * Suppresion d'une connexion par identifiants de sommets
     * @param identifiant_a Integer
     * @param identifiant_b Integer
     */
    public void suppConnexion(Integer identifiant_a, Integer identifiant_b){
        Integer indexSommetSource = null;
        Integer indexSommetDest = null;

        for(Sommet sommet : this.sommets){
            if(sommet.getIndex().equals(identifiant_a)) {
                indexSommetSource = this.sommets.indexOf(sommet);
            }
            if(sommet.getIndex().equals(identifiant_b)) {
                indexSommetDest = this.sommets.indexOf(sommet);
            }
        }
        if(indexSommetSource != null && indexSommetDest != null) {
            this.nbConnexion += 1;
            if(this.type.equals(1)) { // orienté
                // suppresion de la connexion dans la matrice
                this.matrice[indexSommetSource][indexSommetDest] = 0;
            } else { // non orienté
                // suppresion des 2 connexions dans la matrice
                this.matrice[indexSommetSource][indexSommetDest] = 0;
                this.matrice[indexSommetDest][indexSommetSource] = 0;
            }
        }
    }

    /**
     * Création d'un graphe avec un type et un nombre de sommet
     * @param type String
     * @param nbSommets Integer
     */
    public void creerGraphe(Integer type, int nbSommets) {
        this.type = type;
        this.nbSommets = nbSommets;

        this.matrice = new Integer[nbSommets][nbSommets];

        for(int x = 0; x < nbSommets; x++){
            this.sommets.add(new Sommet(x+1));
            for (int i = 0; i < nbSommets; i++) {
                this.matrice[x][i] = 0;
            }
        }   
    }

    /**
     * Création d'un graphe avec un type et un nombre de sommet
     * @param type String
     * @param nbSommets Integer
     */
    public void creerGrapheMots(Integer type, int nbSommets, List<Sommet> sommets) {
        this.type = type;
        this.nbSommets = nbSommets;

        this.sommets = sommets;
        this.matrice = new Integer[nbSommets][nbSommets];

        for(int x = 0; x < nbSommets; x++){
            for (int i = 0; i < nbSommets; i++) {
                this.matrice[x][i] = 0;
            }
        }   
    }

    /**
     * Augmente la taille de la matrice suite à l'ajout d'un sommet
     */
    private void sizeIncrease(){
        Integer pos_x = 0;
        Integer pos_y = 0;
        Integer[][] new_matrice = new Integer[this.nbSommets+1][this.nbSommets+1];

        for(int x = 0; x < this.nbSommets; x++){
            for (int i = 0; i < this.nbSommets; i++) {
                new_matrice[x][i] = this.matrice[x][i] = 0;
                pos_x =+ 1;
            }
            new_matrice[x][pos_x] = 0;
            pos_y += 1;
        }

        this.nbSommets++;

        for(int x = 0; x < this.nbSommets; ++x){
            new_matrice[pos_y][x] = 0;
        }
        this.matrice = new_matrice;
    }   

    /**
     * Ajout un sommet
     * @param identifiant Integer
     */
    public void addSommet(Integer identifiant){
        if(!(checkIdentifiantExiste(identifiant))){
            this.sizeIncrease();
            for(int x = 0; x < nbSommets; x++){
                for (int i = 0; i < nbSommets; i++) {
                    this.matrice[x][i] = 0;
                }
            }
            this.sommets.add(new Sommet(identifiant, new ArrayList<>()));   
        }else{
            System.out.println("Sommet : " + identifiant + " existe déjà !");
        }
    }

    /**
     * Vérifie si un identifiant existe dans la liste des sucesseurs
     * @param identifiant
     * @return Boolean
     */
    private Boolean checkIdentifiantExiste(Integer identifiant){
        for(Sommet sommet : this.sommets){
            if(sommet.getIndex().equals(identifiant)){
                return true;
            }
        }
        return false;
    }

    /**
     * vérifie si un sommet est adjacent à un autre
     * pour un graphe orienté, le sens est pris en compte
     * @param s1 Sommet
     * @param s2 Sommet
     * @return vrai si adjacent direct, faux sinon
     */
    public boolean estAdjacentDirect(int s1, int s2) {
        Sommet start = this.getSommetListe(s1);
        Sommet stop = this.getSommetListe(s2);

        if(this.matrice[this.sommets.indexOf(start)][this.sommets.indexOf(stop)].equals(1)){
            return true;
        }else{
            return false;
        }
    }


    /**
     * Affichage du graphe
     */
    public void affichage(){
        System.out.println("Type : 0 = Non Orienté, 1 = Orienté \n" +
                            "[\n\ttype = " + this.type +
                            ", \n\tnb sommet(s) = " + this.nbSommets +
                            ", \n\tsommet(s) = " + this.sommets +
                            ", \n\tMatrice : " + this.affichageMatrice() +
                            "\n]"
        );
    }

    /**
     * Retourne un string contenant l'affichage de la matrice
     * @return
     */
    public String affichageMatrice(){
        String res = "\n\t";
        for (int i = 0; i < this.matrice.length; i++) {
            for (int j = 0; j < this.matrice[0].length; j++) {
                res += this.matrice[i][j] + " ";
            }
            res += "\n\t";
        }
        return res;
    }

    /**
     * Retourne un sommet dont l'id est donné
     * @param int identifiant du sommet
     * @return Sommet 
     */
    public Sommet getSommetListe(int identifiant){
        for(Sommet sommet : this.sommets){
            if(sommet.getIndex().equals(identifiant)){
                return sommet;
            }
        }
        return null;
    }

    /**
     * Retourne la liste des identifiants depuis la liste des sucesseurs
     * @return String
     */
    public String getIdentifiantAll(){
        String res = "[";
        for(Sommet sommet : this.sommets){
            res += sommet.getIndex() + " | ";
        }
        res += "]";
        return res.replace(" | ]", "]");
    }

    /**
     * sauvegarder un graphe dans un fichier texte
     * @param nomFic String
     */
    public void sauvegarderGraphe(String nomFic) {
        try {
            String chemin = "data/" + nomFic + ".txt";
            File fichier = new File(chemin);
            if(fichier.createNewFile()) {
                FileWriter myWriter = new FileWriter(chemin);
                System.out.println("type : " + this.type);
                System.out.println("nombre sommets : " + this.nbSommets);
                if(this.type.equals(1)) {
                    myWriter.write("1 " + this.getNbSommets().toString() + " "  + this.getNbConnexion().toString() + "\n");
                } else {
                    myWriter.write("0 " + this.getNbSommets().toString() + " " + this.getNbConnexion().toString() + "\n");
                }
                // récupérer les valeurs à 1 dans la matrice et sens coordonnées
                for(int x = 0; x < this.nbSommets; x++){
                    // this.sommets.add(new Sommet(x+1));
                    for (int i = 0; i < this.nbSommets; i++) {
                        if(this.matrice[x][i] == 1) {
                            String indexSommetSource = this.sommets.get(x).getIndex().toString();
                            String indexSommetDest = this.sommets.get(i).getIndex().toString();
                            myWriter.write(indexSommetSource+ " " + indexSommetDest + "\n");
                        }
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
     * Vérifie si il existe un char différent entre 2 mots, si plus de 1 différence, return false
     * @param a String
     * @param b String
     * @return Boolean
     */
    public Boolean verificationDiffChar(String a, String b){
        Integer cpt = 0;
        for(Integer i = 0; i < a.length(); ++i){
            if(!(a.charAt(i) == b.charAt(i))){
                cpt++;
            }
        }
        if(cpt.equals(1)){
            return true;
        }else{
            return false;
        }
    }

    public void generationLiaisons() {
        for(int i=0;i<this.sommets.size(); i++) {
            for(int j=i+1;j<this.sommets.size();j++) {
                if(verificationDiffChar(this.sommets.get(i).getMot(), this.sommets.get(j).getMot())) {
                    this.addConnexion(this.sommets.get(i).getIndex(), this.sommets.get(j).getIndex());
                }
            }
        }
    }

    public void parcoursEnLargeurInit(Sommet sommet){
        for(Sommet s : this.sommets){
            this.c.add(0);
            this.d.add(999999);
            this.p.add(null);
        }
        this.c.set(0, 0);
        this.d.set(0, 0);

        this.f.add(sommet);

        // System.out.println(this.c);
        // System.out.println(this.d);
    }

    /**
     * parcours en largeur sur le graphe
     * @param c liste des colorations
     * @param d liste des distances
     * @param p liste des prédécesseurs
     * @param f file de parcours
     */
    public void parcoursEnLargeur(Sommet sommet) {
        this.parcoursEnLargeurInit(sommet);
        while(this.f.size() != 0) {
            Sommet x = this.f.get(0);
            this.f.remove(0); // défiler f
            int index = this.sommets.indexOf(x);
            for(int y=0;y<this.getVoisin(x).size();y++) { // pour tout sommet adjacent
                int indexTest = this.getVoisin(x).get(y).getIndex()-1;
                if(this.c.get(indexTest) == 0) {
                    this.c.set(indexTest,1);
                    this.d.set(indexTest,this.d.get(index)+1);
                    this.p.set(indexTest,x);
                    this.f.add(this.getVoisin(x).get(y)); // enfiler y
                }
            }
            this.c.set(index,2);
        }
    }

    /**
     * Parcrous en largeurs (version test 11/06/2022 21h29)
     * @param sommet Sommet
     */
    public void parcoursEnLargeurs(Sommet sommet){
        List<Sommet> file = new ArrayList<>();
        file.add(sommet);
        sommet.setMarquer(true);
        
        while(!(file.isEmpty())){
            Sommet s = file.get(0);
            file.remove(0);
            System.out.println(s);
            for(Sommet som : this.getVoisin(s)){
                if(!(som.getMarquer())){
                    file.add(som);
                    som.setMarquer(true);
                }
            }
        }
    }

    /**
     * Retourne la liste des voisins à partir d'un sommet depuis une matrice
     * @param sommet Sommet
     * @return List<Sommet>
     */
    public List<Sommet> getVoisin(Sommet sommet){
        List<Sommet> liste = new ArrayList<>();
        
        for (int j = 0; j < this.matrice[sommet.getIndex()-1].length; j++){
            if(this.matrice[sommet.getIndex()-1][j].equals(1)){
                liste.add(this.sommets.get(j));
            }
        }

        return liste;
    }

    public Integer getType() {return type;}
    public Integer getNbSommets() {return nbSommets;}
    public Integer getNbConnexion() {return nbConnexion;}
    public Integer[][] getMatrice() {return this.matrice;}
    public List<Sommet> getListeSommets() {return this.sommets;}

    public void setType(Integer type) {this.type = type;}
    public void setNbSommets(Integer nbSommets) {this.nbSommets = nbSommets;}
    public void setNbConnexion(Integer nbConnexion) {this.nbConnexion = nbConnexion;}
    public void setMatrice(Integer[][] matrice) {this.matrice = matrice;}
    public void setListeSommets(List<Sommet> sommets) {this.sommets = sommets;}
}
