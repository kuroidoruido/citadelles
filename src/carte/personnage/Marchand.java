package carte.personnage;

import jeu.Partie;
import carte.Famille;
import carte.InstantEffet;

/**
 * Classe mod�lisant le personnage Marchand.
* @author Bauchet Cl�ment
* @author Pena Anthony
* @version 29 oct. 2012
*
*/
public class Marchand extends Personnage {

	public Marchand(Famille f, Partie partie) {
		super("Marchand", f, "Gagne une pi�ce d'or de plus", 6, partie,InstantEffet.effetPre);
	}

	public void capacite() {
		partie.chercher(this).addOr(1); //Le marchand gagne 1 pi�ce d'or de plus
	}
}