package carte.personnage;

import jeu.Joueur;
import jeu.Partie;
import jeu.PasAssezDOrException;
import carte.Famille;

/**
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 29 oct. 2012
 *
 */
public class Abbe extends Personnage {

	public Abbe(String nom, Famille famille, String texteCapacite, int ordre, Partie partie) {
		super(nom, famille, texteCapacite, ordre, partie);
	}
	
	public void capacite() {
		Joueur tmp = partie.getListeJoueur().getFirst();
		for(Joueur j : partie.getListeJoueur()) { //Recherche du joueur le plus riche
			if (j.getOr() > tmp.getOr()){
				tmp = j;
			}
		}
		try {
			tmp.subOr(1);	//Le joueur le plus riche perd 1 or
		} catch (PasAssezDOrException e) {}
		partie.getJoueurCourant().addOr(1); //L'abb� gagne 1 or
	}
	
	public void capacite(int j) {}
	
}
