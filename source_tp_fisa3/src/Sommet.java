package src;

public class Sommet implements Comparable<Sommet> {
    private Integer index;
    private Double poids;
    private Integer profit;

    public Sommet(Integer index, Double poids, Integer profit) {
        this.index = index;
        this.poids = poids;
        this.profit = profit;
    }

    @Override
    public int compareTo(Sommet sommet) { // tri par profit sur les collections de Sommet
        if(this.profit > sommet.getProfit()) {
            return 1;
        } else if(this.profit < sommet.getProfit()) {
            return -1;
        } else { // this.profit = sommet.getProfit()
            return 0;
        }
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public Integer getProfit() {
        return profit;
    }

    public void setProfit(Integer profit) {
        this.profit = profit;
    }
}
