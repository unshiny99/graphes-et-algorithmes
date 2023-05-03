package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BackPack {
    private Integer nbSommet;
    private Double poidsMax;
    private List<Sommet> sommets;
    private Map<Integer, List<Integer>> incompatibilite;

    public BackPack(Integer nbSommet, Double poidsMax) {
        this.nbSommet = nbSommet;
        this.poidsMax = poidsMax;
    }

    /**
	 * Solve le problème du sac à dos disjonctif
     * base de l'algo dit "glouton"
	 */
	public void sacADosDisjonctif(Graphe graphe) {
        List<Sommet> choixSommets = new ArrayList<>();
		// tri décroissant au préalable (sur le profit)
		Collections.sort(this.sommets, Collections.reverseOrder());

        Double wConso = 0.0;
		// itération sur les sommets
		for(Sommet sommet : this.getSommets()) {
            if(sommet.getPoids() + wConso <= this.getPoidsMax()) {
                // parcours des listes d'adjacence
                for(Integer key : incompatibilite.keySet()){
                    if(key != sommet.getIndex()) { // si pas sommet courant et sommet dans le sac
                        // && choixSommets.contains(sommet)
                        
                        // parcourir toutes les values pour vérifier si on a le sommet courant
                        if(!this.incompatibilite.get(key).contains(sommet.getIndex())) {
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
        for(Sommet sommet : choixSommets) {
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

    
}
