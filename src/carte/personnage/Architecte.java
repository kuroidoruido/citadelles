package carte.personnage;

import jeu.Partie;
import carte.Famille;
import carte.InstantEffet;

/**
 * Classe modélisant le personnage Architecte.
* @author Bauchet Clément
* @author Pena Anthony
* @version 29 oct. 2012
*
*/
public class Architecte extends Personnage {

	public Architecte(Famille f, Partie partie) {
		super("Architecte", f, "Peut bâtir jusqu'à 3 quartiers", 7, partie,InstantEffet.effetPre);
	}
	
	public void capacite() {
		partie.chercher(this).addDroitConstruction(2); //L'architecte a le droit de construire 2 bÃ¢timents de plus
	}
	
}