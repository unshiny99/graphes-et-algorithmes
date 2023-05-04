package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BackPack {
    private Integer nbSommet;
    private Integer nbIncompatibilite;
    private Double poidsMax;
    private List<Sommet> sommets;
    private Map<Integer, List<Integer>> incompatibilite;

    public BackPack(Double poidsMax) {
        this.nbSommet = 0;
        this.nbIncompatibilite = 0;
        this.poidsMax = poidsMax;

        this.sommets = new ArrayList<>();

        this.incompatibilite = new HashMap<Integer, List<Integer>>();
    }

    /**
     * ajoute une connexion entre les deux sommets donné (arête)
     * 
     * @param i : identifiant du premier sommet
     * @param j : identifiant du deuxième sommet
     */
    public void addConnexion(Integer i, Integer j) {
        if (this.incompatibilite.get(i).contains(j)) {
            System.out.println("La connexion existe déjà");
        } else {
            this.incompatibilite.get(i).add(j);
            this.incompatibilite.get(j).add(i);
            this.nbIncompatibilite += 1;
        }
    }

    /**
     * supprime une connexion entre les deux sommets donné (arête)
     * 
     * @param i : identifiant du premier sommet
     * @param j : identifiant du deuxième sommet
     */
    public void delConnexion(Integer i, Integer j) {
        this.incompatibilite.get(i).remove(j);
        this.incompatibilite.get(j).remove(i);

        this.nbIncompatibilite -= 1;
    }

    /**
     * ajoute un sommet au graphe, si le sommet existe déjà il ne l'ajoutera pas
     * 
     * @param i : identifiant du sommet
     */
    public void addSommet(Sommet i) {
        for (Sommet sommet : this.sommets) {
            if (sommet.getIndex() == i.getIndex()) {
                return;
            }
        }
        this.sommets.add(i);
        this.nbSommet += 1;
    }

    /**
     * affiche le sac à dos
     */
    public void showBackPack() {
        System.out.println("nbSommet = " + this.nbSommet);
        System.out.println("nbIncompatibilité = " + this.nbIncompatibilite);
        System.out.println("Poids Max = " + this.poidsMax);
        System.out.println("Sommets = " + this.sommets);
        System.out.println("Liste d'incompatibilitée : ");
        this.incompatibilite.forEach((key, value) -> {
            System.out.println(key + " = " + value);
        });

    }

    /**
     * Charge un sac à dos depuis un fichier texte
     * 
     * @param path
     * @return
     * @throws Exception
     */
    public static BackPack loadBackPack(String path) throws Exception {
        File file = new File(path);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = br.readLine();

        String[] data = line.split("[\s]+");

        BackPack backPack = new BackPack(Double.parseDouble(data[2]));

        // Ajout des sommets
        line = br.readLine();
        String[] profits = line.split("[\s]+");

        line = br.readLine();
        String[] poids = line.split("[\s]+");

        for (int i = 0; i < Integer.valueOf(data[0]); i++) {
            backPack.addSommet(new Sommet(i + 1, Double.parseDouble(poids[i]), Integer.parseInt(profits[i])));
            backPack.incompatibilite.put(i, new ArrayList<>());
        }

        // ajoute des incompatibilité
        line = br.readLine();
        while (line != null && line != "") {
            String[] sommets = line.split("[\s]+");

            Integer i = Integer.valueOf(sommets[0]);
            Integer j = Integer.valueOf(sommets[1]);

            backPack.addConnexion(i, j);

            line = br.readLine();
        }

        br.close();

        if (backPack.getNbSommet() != Integer.valueOf(data[0])) {
            throw new Exception(
                    "Le nombre de sommet n'est pas le même que le nombre de sommet au début du fichier");
        } else if (backPack.getNbIncompatibilite() != Integer.valueOf(data[1])) {
            throw new Exception(
                    "Le nombre d'incompatibilité ne correspond pas au nombre d'incompatibilité au début du fichier");
        }

        return backPack;

    }

    /**
     * Sauvegarde un graphe dans un fichier texte
     * 
     * @param path
     * @throws IOException
     */
    public void saveBackpack(String path) throws IOException {
        File file = new File(path);

        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        bw.write(this.nbSommet + " " + this.nbIncompatibilite + " " + this.poidsMax);
        bw.newLine();

        // get les strings des profits et poids
        String poidsString = "";
        String profitString = "";
        for (Sommet s : this.sommets) {
            poidsString = poidsString + s.getPoids() + " ";
            profitString = profitString + s.getProfit() + " ";
        }

        // Ajout des profits
        bw.write(profitString);
        bw.newLine();

        // Ajout des poids
        bw.write(poidsString);
        bw.newLine();

        // Ajout des incompabilités
        this.incompatibilite.forEach((i, adj) -> {
            // pour chaque adjacent
            adj.forEach((j) -> {
                try {
                    if (j > i) { // écrit la connexion si elle est non orienté et que la connexion n'a pas déjà
                                 // été pris en compte
                        bw.write(i + " " + j);
                        bw.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });

        bw.close();
    }

    /**
     * Génère un sac à dos par la méthode de Erdös-Rényi
     * 
     * @param n       : nombre de sommets à générer
     * @param poidMax : capacité du sac
     * @throws IOException
     */
    public static BackPack genBackPack(Integer n, Double poidMax) throws IOException {
        Random rDouble = new Random();
        Random rInt = new Random();

        BackPack backPack = new BackPack(poidMax);

        // Ajout des sommets
        for (int i = 1; i <= n; i++) {
            backPack.addSommet(new Sommet(i, rDouble.nextDouble(poidMax), rInt.nextInt(1000)));
            backPack.incompatibilite.put(i, new ArrayList<>());
        }

        // passe par tous les couples possibles et regarde si on ajoute cette connexion
        for (Sommet i : backPack.sommets) {
            for (Sommet j : backPack.sommets) {
                if (i.getIndex() != j.getIndex() && !backPack.isIncompatible(i.getIndex(), j.getIndex())) { // si
                                                                                                            // sommets
                                                                                                            // différents
                                                                                                            // et non
                    // connectés
                    Double d = rDouble.nextDouble();
                    if (d <= 0.6) {
                        backPack.addConnexion(i.getIndex(), j.getIndex());
                    }
                }
            }
        }

        backPack.saveBackpack("source_tp_fisa3/data/gen_backpack.txt");
        return backPack;
    }

    /**
     * Vérifie si les sommets donnés sont adjacent ou non
     * 
     * @param i : identifiant du premier sommet
     * @param j : identifiant du deuxième sommet
     * @return true si les sommets sont adjacent false s'ils ne le sont pas
     */
    public boolean isIncompatible(Integer i, Integer j) {
        return this.incompatibilite.get(i).contains(j);
    }

    /**
     * Solve le problème du sac à dos disjonctif
     * base de l'algo dit "glouton"
     * gère aussi la les incompatibilités entre éléments (sommets)
     */
    public void sacADosDisjonctif() {
        List<Sommet> choixSommets = new ArrayList<>();
        // tri décroissant au préalable (sur le profit)
        Collections.sort(this.sommets, Collections.reverseOrder());

        Double wConso = 0.0; // poids consommé
        // itération sur les sommets
        for (Sommet sommet : this.getSommets()) {
            if ((sommet.getPoids() + wConso) <= this.getPoidsMax()) {
                // parcours des listes d'adjacence
                for (Integer key : incompatibilite.keySet()) {
                    if (key != sommet.getIndex()) { // si pas sommet courant
                        // && choixSommets.contains(sommet) // vérif si sommet déjà dans le sac

                        // vérifie la compatibilité des sommets
                        if (!this.incompatibilite.get(key).contains(sommet.getIndex())
                                && (sommet.getPoids() + wConso) <= this.getPoidsMax()) {
                            // ajout du sommet (liste et poids total)
                            choixSommets.add(sommet);
                            wConso = wConso + sommet.getPoids();
                        }
                    }
                }
            }
        }
        System.out.println("Poids total dans le sac : " + wConso);
        // calcul somme des profits
        Integer totalprofit = 0;
        for (Sommet sommet : choixSommets) {
            totalprofit += sommet.getProfit();
        }
        System.out.println("Profit total : " + totalprofit);
    }

    public Integer getNbSommet() {
        return nbSommet;
    }

    public void setNbSommet(Integer nbSommet) {
        this.nbSommet = nbSommet;
    }

    public Double getPoidsMax() {
        return poidsMax;
    }

    public void setPoidsMax(Double poidsMax) {
        this.poidsMax = poidsMax;
    }

    public List<Sommet> getSommets() {
        return sommets;
    }

    public void setSommets(List<Sommet> sommets) {
        this.sommets = sommets;
    }

    public Map<Integer, List<Integer>> getIncompatibilite() {
        return incompatibilite;
    }

    public void setIncompatibilite(Map<Integer, List<Integer>> incompatibilite) {
        this.incompatibilite = incompatibilite;
    }

    public Integer getNbIncompatibilite() {
        return nbIncompatibilite;
    }

    public void setNbIncompatibilite(Integer nbIncompatibilite) {
        this.nbIncompatibilite = nbIncompatibilite;
    }
}
