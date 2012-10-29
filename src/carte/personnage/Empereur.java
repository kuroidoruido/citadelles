package carte.personnage;

import jeu.Partie;
import carte.Famille;

/**
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 29 oct. 2012
 *
 */
public class Empereur extends Personnage {

	public Empereur(Famille f, Partie partie) {
		super("Empereur", f, "D�signe le premier joueur du tour suivant", 4, partie);
	}

	public void capacite() {}

	public void capacite(int j) {
		
		
	}

	
}
