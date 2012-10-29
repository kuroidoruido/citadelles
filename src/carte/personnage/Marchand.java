package carte.personnage;

import jeu.Partie;
import carte.Famille;

/**
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 29 oct. 2012
 *
 */
public class Marchand extends Personnage {

	public Marchand(Famille f, Partie partie) {
		super("Marchand", f, "Gagne une pi�ce d'or de plus", 6, partie);
	}

	public void capacite() {
		partie.getJoueurCourant().addOr(1); //Le marchand gagne 1 or de plus 
	}

	public void capacite(int j) {}

	
}
