package carte.personnage;

import jeu.Partie;
import carte.Famille;
import carte.InstantEffet;

/**
* @author Bauchet ClÃ©ment
* @author Pena Anthony
* @version 29 oct. 2012
*
*/
public class Condottiere extends Personnage {

	public Condottiere(Famille f, Partie partie) {
		super("Condottiere", f, "Peut détruire un quartier en dépensant le coût moins 1 pièce d'or", 8, partie,InstantEffet.effetPost);
	}
	
	public void capacite() {}

}