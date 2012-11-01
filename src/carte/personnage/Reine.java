package carte.personnage;

import jeu.Partie;
import carte.Carte;
import carte.Famille;

/**
* Classe modélisant le personnage Reine.
* @author Bauchet Clément
* @author Pena Anthony
* @version 29 oct. 2012
*
*/
public class Reine extends Personnage {

	public Reine(Famille f, Partie partie) {
		super("Reine", f, "Gagne 3 pièces d'or si assise à côté du Roi", 9, partie,Carte.effetPre);
	}
	
	public void capacite() {
		// Si évolution
	}
}