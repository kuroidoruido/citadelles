package carte.personnage;

import jeu.Partie;
import carte.Famille;

public class Marchand extends Personnage {

	public Marchand(String nom, Famille famille, String texteCapacite, int ordre, Partie partie) {
		super(nom, famille, texteCapacite, ordre, partie);
	}

	public void capacite() {
		partie.getJoueurCourant().addOr(1); //Le marchand gagne 1 or de plus 
	}

	public void capacite(int j) {}

}
