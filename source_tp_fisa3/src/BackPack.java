package src;

import java.util.Collections;
import java.util.List;

public class BackPack {
    private Integer nbSommet;
    private Double poidsMax;
    private List<Sommet> sommets;
    private List<List<Integer>> incompatibilite; // si 0 : pas d'arête, si 1 : arête

    public BackPack(Integer nbSommet, Double poidsMax) {
        this.nbSommet = nbSommet;
        this.poidsMax = poidsMax;
    }

    /**
	 * Solve le problème du sac à dos disjonctif
	 */
	public void sacADosDisjonctif() {
		// tri décroissant au préalable (sur le profit)
		Collections.sort(this.sommets, Collections.reverseOrder());

		// itération sur les sommets
		
	}
}
