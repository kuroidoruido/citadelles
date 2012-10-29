package carte.personnage;

import jeu.Partie;
import carte.Famille;

/**
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 29 oct. 2012
 *
 */
public class Bailli extends Personnage {

	public Bailli(String nom, Famille famille, String texteCapacite, int ordre, Partie partie) {
		super(nom, famille, texteCapacite, ordre, partie);
	}

	public void capacite() {}

	public void capacite(int j) {} //La capacit� du Bailli est cod�e dans la m�thode construreQuartier() de Joueur

}
