package carte.personnage;

import jeu.Partie;
import carte.Famille;

/**
* @author Bauchet Clément
* @author Pena Anthony
* @version 29 oct. 2012
*
*/
public class Roi extends Personnage {

	public Roi(Famille f, Partie partie) {
		super("Roi", f, "Devient le premier joueur au tour suivant", 4, partie);
	}
	
	public void capacite() {}
	
	public void capacite(int j) {
	
	}


}