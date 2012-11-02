package carte.personnage;

import jeu.Partie;
import carte.Famille;
import carte.InstantEffet;

/**
 * Classe mod�lisant le personnage Roi.
* @author Bauchet Cl�ment
* @author Pena Anthony
* @version 29 oct. 2012
*
*/
public class Roi extends Personnage {

	public Roi(Famille f, Partie partie) {
		super("Roi", f, "Devient le premier joueur au tour suivant", 4, partie, InstantEffet.effetPost);
	}
	
	public void capacite() {
		super.partie.setCouronne(super.partie.chercher(this));
		partie.couronneEnPremier();
	}

}