package carte.personnage;

import jeu.Partie;
import carte.Carte;
import carte.Famille;

/**
 * Classe modélisant le personnage Assassin.
* @author Bauchet Clément
* @author Pena Anthony
* @version 29 oct. 2012
*
*/
public class Assassin extends Personnage {

	public Assassin(Famille f, Partie partie) {
		super("Assassin",f,"Peut tuer un autre personnage", 1, partie,Carte.effetPost);
	}
	
	public void capacite() {
		// Si évolution
	}
}