package carte.personnage;

import jeu.Partie;
import carte.Famille;
import carte.InstantEffet;

/**
 * Classe mod�lisant le personnage Architecte.
* @author Bauchet Cl�ment
* @author Pena Anthony
* @version 29 oct. 2012
*
*/
public class Architecte extends Personnage {

	public Architecte(Famille f, Partie partie) {
		super("Architecte", f, "Peut b�tir jusqu'� 3 quartiers", 7, partie,InstantEffet.effetPre);
	}
	
	public void capacite() {
		partie.chercher(this).addDroitConstruction(2); //L'architecte a le droit de construire 2 bâtiments de plus
	}
	
}