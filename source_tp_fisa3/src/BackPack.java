package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BackPack {
    private Integer nbSommet;
    private Double poidsMax;
    private List<Sommet> sommets;
    private Map<Integer, List<Integer>> incompatibilite; // si 0 : pas d'arête, si 1 : arête

    public BackPack(Integer nbSommet, Double poidsMax) {
        this.nbSommet = nbSommet;
        this.poidsMax = poidsMax;
    }

    /**
	 * Solve le problème du sac à dos disjonctif
	 */
	public void sacADosDisjonctif(Graphe graphe) {
        List<Sommet> choixSommets = new ArrayList<>();
		// tri décroissant au préalable (sur le profit)
		Collections.sort(this.sommets, Collections.reverseOrder());

        Double wConso = 0.0;
		// itération sur les sommets
		for(Sommet sommet : this.getSommets()) {
            if(sommet.getPoids() + wConso <= this.getPoidsMax()) {
                choixSommets.add(sommet);
                wConso = wConso + sommet.getPoids();
            }
        }
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
