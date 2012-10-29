package carte.personnage;

import jeu.Partie;
import carte.Famille;

public class Bailli extends Personnage {

	public Bailli(String nom, Famille famille, String texteCapacite, int ordre, Partie partie) {
		super(nom, famille, texteCapacite, ordre, partie);
	}

	public void capacite() {}

	public void capacite(int j) {} //La capacité du Bailli est codée dans la méthode construreQuartier() de Joueur

}
