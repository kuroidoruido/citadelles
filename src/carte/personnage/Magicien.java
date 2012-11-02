package carte.personnage;

import jeu.Partie;
import carte.Famille;
import carte.InstantEffet;

/**
* Classe mod�lisant le personnage Magicien.
* @author Bauchet Cl�ment
* @author Pena Anthony
* @version 29 oct. 2012
*
*/
public class Magicien extends Personnage {

	public Magicien(Famille f, Partie partie) {
		super("Magicien", f, "Peut �changer toute sa main contre la main d'un autre joueur", 3, partie,InstantEffet.effetPreOuPost);
	}

	public void capacite() {
		// Si �volution
	}

}