package carte.personnage;

import jeu.Partie;
import carte.Carte;
import carte.Famille;

/**
* @author Bauchet Clément
* @author Pena Anthony
* @version 29 oct. 2012
*
*/
public class Bailli extends Personnage {

	public Bailli(Famille f, Partie partie) {
		super("Bailli",f,"Tout joueur qui construit doit payer 1 pièce d'or au Bailli", 2, partie,Carte.effetPassif);
	}
	
	public void capacite() {
		// capacité passive
		// La capacité du Bailli est codée dans la méthode construreQuartier() de Joueur
	}
}