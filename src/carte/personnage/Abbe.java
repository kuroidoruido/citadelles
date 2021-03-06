package carte.personnage;

import jeu.Joueur;
import jeu.Partie;
import jeu.PasAssezDOrException;
import carte.Carte;
import carte.Famille;

/**
 * Classe mod�lisant le personnage Abb�.
 * @author Bauchet Cl�ment
 * @author Pena Anthony
 * @version 29 oct. 2012
 *
 */
public class Abbe extends Personnage {

	public Abbe(Famille f, Partie partie) {
		super("Abb�", f, "Le joueur le plus riche lui donne 1 pi�ce d'or", 5, partie,Carte.effetPre);
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
		partie.chercher(this).addOr(1); //L'abb� gagne 1 or
	}
	
}
