package carte.personnage;

import jeu.Partie;
import carte.Carte;
import carte.Famille;

/**
 * Classe modélisant le personnage Marchand.
* @author Bauchet Clément
* @author Pena Anthony
* @version 29 oct. 2012
*
*/
public class Marchand extends Personnage {

	public Marchand(Famille f, Partie partie) {
		super("Marchand", f, "Gagne une pièce d'or de plus", 6, partie,Carte.effetPre);
	}
	
	public void capacite() {
		partie.chercher(this).addOr(1); //Le marchand gagne 1 or de plus
	}
}