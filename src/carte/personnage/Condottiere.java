package carte.personnage;

import jeu.Partie;
import carte.Famille;

/**
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 29 oct. 2012
 *
 */
public class Condottiere extends Personnage {

	public Condottiere(Famille f, Partie partie) {
		super("Condottiere", f, "Peut d�truire un quartier en d�pensant le co�t moins 1 pi�ce d'or", 8, partie);
	}

	public void capacite() {}

	public void capacite(int j) {
		
	}

}
