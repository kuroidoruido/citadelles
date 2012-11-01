package carte.personnage;

import jeu.Joueur;
import jeu.Partie;
import carte.Carte;
import carte.Famille;

/**
 * Classe modélisant le personnage Empereur.
* @author Bauchet Clément
* @author Pena Anthony
* @version 29 oct. 2012
*
*/
public class Empereur extends Personnage {
	
	private Joueur joueurSelectionne;
	
	public Empereur(Famille f, Partie partie) {
		super("Empereur", f, "Désigne le premier joueur du tour suivant", 4, partie,Carte.effetPost);
		joueurSelectionne = null;
	}
	
	public void capacite() throws SelectionDuJoueurNonFaiteException {
		if(joueurSelectionne != null)
		{
			partie.setCouronne(joueurSelectionne); //L'empereur donne la couronne au joueur qu'il a sélectionné
			joueurSelectionne = null;
		}
		else
		{
			throw new SelectionDuJoueurNonFaiteException(); //Si l'empereur n'a choisi personne, il y a exception.
		}
	}
	
	/**
	* Méthode de sélection du joueur à couronner
	* @param j le joueur choisi par l'empereur.
	*/
	public void selectJoueur(Joueur j) {
		joueurSelectionne = j;
	}
}