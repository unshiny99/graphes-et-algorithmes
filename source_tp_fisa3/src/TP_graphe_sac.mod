/*********************************************
 * OPL 12.7.1.0 Model
 * Author: bryan
 * Creation Date: 2 mai 2023 at 15:02:44
 *********************************************/
 
//lire dans un fichier le nb d'objet et le poids max
int nbObjet = ...;
float poidsMax = ...;

//déclarer un intervalle d'entiers de 1 à nbObjet
range objets = 1..nbObjet;

//déclarer des tableaux indexés sur les objets,
//ils seront remplis en lisant le fichier de données
float poids[objets] = ...;
int valeur[objets] = ...;

int incomp[objets][objets]=...;

//déclarer les variables de décisions
dvar boolean x[objets];

//modele
maximize
 sum(i in objets) valeur[i] * x[i];

subject to {
	sum( i in objets )
	 	poids[i] * x[i] <= poidsMax;
	 	
	forall(i in objets, j in objets){
		if(incomp[i][j] != 0){
			x[j]+x[i]<=1;
		}	
	}	 	
}