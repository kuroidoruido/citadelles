package carte.personnage;

import jeu.Partie;
import carte.Carte;
import carte.Famille;

/**
* Classe modélisant le personnage Condottiere.
* @author Bauchet Clément
* @author Pena Anthony
* @version 29 oct. 2012
*
*/
public class Condottiere extends Personnage {

	public Condottiere(Famille f, Partie partie) {
		super("Condottiere", f, "Peut détruire un quartier en dépensant le coût moins 1 pièce d'or", 8, partie,Carte.effetPost);
	}
	
	public void capacite() {}
	//Si évolution
}