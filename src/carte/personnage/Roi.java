package carte.personnage;

import jeu.Partie;
import carte.Carte;
import carte.Famille;

/**
 * Classe modélisant le personnage Roi.
* @author Bauchet Clément
* @author Pena Anthony
* @version 29 oct. 2012
*
*/
public class Roi extends Personnage {

	public Roi(Famille f, Partie partie) {
		super("Roi", f, "Devient le premier joueur au tour suivant", 4, partie, Carte.effetPost);
	}
	
	public void capacite() {
		super.partie.setCouronne(super.partie.chercher(this));
		partie.couronneEnPremier();
	}

}