package carte.personnage;

import jeu.Partie;
import carte.Famille;

/**
 * @author Bauchet Cl�ment
 * @author Pena Anthony
 * @version 29 oct. 2012
 *
 */
public class Bailli extends Personnage {

	public Bailli(Famille f, Partie partie) {
		super("Bailli",f,"Tout joueur qui construit doit payer 1 pi�ce d'or au Bailli", 2, partie);
	}

	public void capacite() {}

	public void capacite(int j) {} //La capacit� du Bailli est cod�e dans la m�thode construreQuartier() de Joueur

	
}
