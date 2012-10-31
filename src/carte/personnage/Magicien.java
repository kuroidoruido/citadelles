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
public class Magicien extends Personnage {

	public Magicien(Famille f, Partie partie) {
		super("Magicien", f, "Peut échanger toute sa main contre la main d'un autre joueur", 3, partie,Carte.effetPreOuPost);
	}
	
	public void capacite() {
		// Si évolution
	}

}