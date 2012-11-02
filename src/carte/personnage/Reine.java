package carte.personnage;

import jeu.Partie;
import carte.Famille;
import carte.InstantEffet;

/**
* Classe mod�lisant le personnage Reine.
* @author Bauchet Cl�ment
* @author Pena Anthony
* @version 29 oct. 2012
*
*/
public class Reine extends Personnage {

	public Reine(Famille f, Partie partie) {
		super("Reine", f, "Gagne 3 pi�ces d'or si assise � c�t� du Roi", 9, partie,InstantEffet.effetPre);
	}

	public void capacite() {
		// Si �volution
	}
}