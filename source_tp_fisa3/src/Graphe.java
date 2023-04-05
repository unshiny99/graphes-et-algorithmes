package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

// Bryan Moreau, Maxime Frémeaux, Geoffrey Auzou
public class Graphe {
	private Integer type; // 0 si non orienté 1 si orienté
	private Integer n; // nombre de sommets
	private Integer m; // nombre de connexions
	private Map<Integer, Map<Integer, Double>> listes; // liste d'adjacence avec poid

	public Graphe(Integer type) {
		this.type = type;
		this.n = 0;
		this.m = 0;
		this.listes = new HashMap<Integer, Map<Integer, Double>>();
	}

	public Graphe(Integer type, Integer n) {
		this.type = type;
		this.n = n;
		this.m = 0;

		this.listes = new HashMap<Integer, Map<Integer, Double>>();
		for (int i = 1; i <= n; i++) {
			Map<Integer, Double> tempL = new HashMap<Integer, Double>();

			this.listes.put(i, tempL);
		}

	}

	/**
	 * ajoute une connexion entre les deux sommets donné (arc ou arête)
	 * 
	 * @param i : identifiant du premier sommet
	 * @param j : identifiant du deuxième sommet
	 */
	public void addConnexion(Integer i, Integer j, Double poid) {
		if (this.listes.get(i).containsKey(j)) {
			System.out.println("La connexion existe déjà");
		} else {
			if (this.type == 0) {
				this.listes.get(i).put(j, poid);
				this.listes.get(j).put(i, poid);
			} else if (this.type == 1) {
				this.listes.get(i).put(j, poid);
			}
			this.m += 1;
		}
	}

	/**
	 * supprime une connexion entre les deux sommets donné (arc ou arête)
	 * 
	 * @param i : identifiant du premier sommet
	 * @param j : identifiant du deuxième sommet
	 */
	public void delConnexion(Integer i, Integer j) {
		if (this.type == 0) {
			this.listes.get(i).remove(j);
			this.listes.get(j).remove(i);
		} else if (this.type == 1) {
			this.listes.get(i).remove(j);
		}
		this.m -= 1;
	}

	/**
	 * ajoute un sommet au graphe, si le sommet existe déjà il ne l'ajoutera pas
	 * 
	 * @param i : identifiant du sommet
	 */
	public void addSommet(Integer i, Boolean manual) {
		if (this.listes.containsKey(i)) {
			if (manual) {
				System.out.println("Le sommet existe déjà");
			}
		} else {
			this.listes.put(i, new HashMap<Integer, Double>());
			this.n += 1;
		}
	}

	/**
	 * Vérifie si les sommets donnés sont adjacent ou non
	 * 
	 * @param i : identifiant du premier sommet
	 * @param j : identifiant du deuxième sommet
	 * @return true si les sommets sont adjacent false s'ils ne le sont pas
	 */
	public boolean isDirect(Integer i, Integer j) {
		return this.listes.get(i).containsKey(j);
	}

	/**
	 * affiche le graphe
	 */
	public void showGraph() {
		String type = "";
		if (this.type == 0) {
			type = "Non orienté";
		} else if (this.type == 1) {
			type = "orienté";
		}

		System.out.println("type = " + type);
		System.out.println("n = " + this.n);
		System.out.println("Liste d'adjacence : ");
		this.listes.forEach((key, value) -> {
			System.out.println(key + " = " + value);
		});

	}

	/**
	 * Charge un graphe depuis un fichier texte
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static Graphe loadGraph(String path) throws Exception {
		File file = new File(path);

		BufferedReader br = new BufferedReader(new FileReader(file));

		String line = br.readLine();

		String[] data = line.split("[\s]+");

		Graphe g = new Graphe(1);

		line = br.readLine();

		while (line != null || line == "") {
			String[] sommets = line.split("[\s]+");

			Integer i = Integer.valueOf(sommets[0]);
			Integer j = Integer.valueOf(sommets[1]);
			Double poid = Double.valueOf(sommets[2]);

			// ajoute les deux sommets (s'ils existe ça ne les ajoutera pas)
			g.addSommet(i, false);
			g.addSommet(j, false);

			// ajoute la connexion
			g.addConnexion(i, j, poid);

			line = br.readLine();
		}

		br.close();

		if (g.getN() != Integer.valueOf(data[0])) {
			g.setN(Integer.valueOf(data[0]));
			throw new Exception(
					"Le nombre de sommet dans les connexions n'est pas le même que le nombre de sommet au début du fichier");
		} else if (g.getM() != Integer.valueOf(data[1])) {
			throw new Exception("Le nombre de connexion ne correspond pas au nombre de connexion au début du fichier");
		}

		return g;

	}

	/**
	 * Sauvegarde un graphe dans un fichier texte
	 * 
	 * @param path
	 * @throws IOException
	 */
	public void saveGraph(String path) throws IOException {
		File file = new File(path);

		BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		bw.write(this.n + " " + this.m);
		bw.newLine();

		this.listes.forEach((i, adj) -> {
			// pour chaque adjacent
			adj.forEach((j, poid) -> {
				try {
					if (this.type == 0 && j > i) { // écrit la connexion si elle est non orienté et que la connexion n'a
													// pas déjà été pris en compte
						bw.write(i + " " + j + " " + poid);
						bw.newLine();
					} else if (this.type == 1) { // sinon si il est orienté, on écrit la connexion
						bw.write(i + " " + j + " " + poid);
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
	 * Génère un graphe par la méthode de Erdös-Rényi
	 * 
	 * @param type : type du graphe à générer
	 * @param n    : nombre de sommets à générer
	 * @param p    : probabilité de présence des connexions
	 * @throws IOException
	 */
	public static Graphe genGraph(Integer type, Integer n, Double p) throws IOException {
		Random r = new Random();

		Graphe g = new Graphe(type, n);

		// passe par tout les couples possibles et regarde si on ajoute cette connexion
		g.listes.forEach((key, value) -> { // sommet 1
			g.listes.forEach((key2, value2) -> { // sommet
				if (key != key2 && !g.isDirect(key, key2)) { // si sommets différents et non connectés
					Double d = r.nextDouble();
					if (d <= p) {
						// générer un poids entre 1.0 et 10.0
						Double poids = Math.round((r.nextDouble() * 9.0 + 1.0) * 10.0) / 10.0;
						System.out.println(poids);
						g.addConnexion(key, key2, poids);
					}
				}
			});
		});

		g.saveGraph("source_tp_fisa3/data/gen_graphe.txt");
		return g;
	}

	/**
	 * Implémentation de l'algorithme de Ford-Fulkerson
	 * 
	 * @param source     : source
	 */
	public List<Object> methodeBellmanFord(Integer source) {
		List<Double> longeurChemin = new ArrayList<>();
		Map<Integer, Integer> predecesseurs = new HashMap<>();
		// initialisation
		for (Integer u = 0; u < n; u++) {
			longeurChemin.add(Double.MAX_VALUE);
			predecesseurs.put(u+1, null);
		}
		longeurChemin.set(source-1,0.0);
		for (Integer k = 1; k < n; k++) {
			for (Integer u : this.listes.keySet()) {
				for (Integer v : this.listes.get(u).keySet()) {
					Double poids = this.listes.get(u).get(v);
					if (longeurChemin.get(u-1) + poids < longeurChemin.get(v-1)) {
						longeurChemin.set(v-1, longeurChemin.get(u-1) + poids);
						predecesseurs.replace(v, u);
					}
				}
			}
		}
		List<Object> returnedList = new ArrayList<>();
		returnedList.add(longeurChemin);
		returnedList.add(predecesseurs);
		return returnedList;
	}

	/**
	 * Implémentation de l'algorithme de Ford-Fulkerson
	 * 
	 * @param graph : graphe
	 * @param a     : liste des arêtes
	 * @param c     : liste des capacités entre chaque vertex
	 * @param s     : source
	 * @param t     : puit de s
	 */
	public Integer algoFordFullkerson(Graphe graph, Map<Integer, Integer> a, Integer c, Integer s, Integer t) {
		Integer flow = null;

		// explication + pseudo code ici :
		// https://fr.wikipedia.org/wiki/Algorithme_de_Ford-Fulkerson

		return flow;
	}

	/**
	 * Exporte le graphe dans un .dat pour l'utiliser dans CPLEX
	 * 
	 * @throws IOException
	 */
	public void exportCPLEX(Integer source, Integer target) throws IOException {
		File file = new File("source_tp_fisa3/data/donnee.dat");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		bw.write("n = " + this.n + ";");
		bw.newLine();
		bw.write("s = " + source + ";");
		bw.newLine();
		bw.write("t = " + target + ";");
		bw.newLine();

		// Mise en forme des poids
		List<List<Double>> poids = new ArrayList<List<Double>>();

		for (int i = 0; i < this.n; i++) {
			poids.add(new ArrayList<Double>());
			for (int j = 0; j < this.n; j++) {
				if (this.listes.containsKey(i + 1) && this.listes.get(i + 1).containsKey(j + 1)) {
					poids.get(i).add(this.listes.get(i + 1).get(j + 1));
				} else {
					poids.get(i).add(10000.0);
				}
			}
		}

		bw.write("poids = " + poids + ";");

		bw.close();
	}

	public Integer getType() {
		return type;
	}

	public Integer getN() {
		return n;
	}

	public Integer getM() {
		return m;
	}

	public Map<Integer, Map<Integer, Double>> getListes() {
		return listes;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setN(Integer n) {
		this.n = n;
	}

	public void setM(Integer m) {
		this.m = m;
	}

	public void setListes(Map<Integer, Map<Integer, Double>> listes) {
		this.listes = listes;
	}
}
