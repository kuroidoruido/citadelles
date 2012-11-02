package carte.personnage;

import jeu.Partie;
import carte.Famille;
import carte.InstantEffet;

/**
 * Classe modélisant le personnage Assassin.
* @author Bauchet Clément
* @author Pena Anthony
* @version 29 oct. 2012
*
*/
public class Assassin extends Personnage {

	public Assassin(Famille f, Partie partie) {
		super("Assassin",f,"Peut tuer un autre personnage", 1, partie,InstantEffet.effetPost);
	}
	
	public void capacite() {
		// Si évolution
	}
}