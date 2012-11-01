package carte.personnage;

import jeu.Partie;
import carte.Carte;
import carte.Famille;

/**
* Classe mod�lisant le personnage Condottiere.
* @author Bauchet Cl�ment
* @author Pena Anthony
* @version 29 oct. 2012
*
*/
public class Condottiere extends Personnage {

	public Condottiere(Famille f, Partie partie) {
		super("Condottiere", f, "Peut d�truire un quartier en d�pensant le co�t moins 1 pi�ce d'or", 8, partie,Carte.effetPost);
	}
	
	public void capacite() {}
	//Si �volution
}