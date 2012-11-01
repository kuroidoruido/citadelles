package carte.personnage;

import jeu.Joueur;
import jeu.Partie;
import carte.Carte;
import carte.Famille;

/**
 * Classe mod�lisant le personnage Empereur.
* @author Bauchet Cl�ment
* @author Pena Anthony
* @version 29 oct. 2012
*
*/
public class Empereur extends Personnage {
	
	private Joueur joueurSelectionne;
	
	public Empereur(Famille f, Partie partie) {
		super("Empereur", f, "D�signe le premier joueur du tour suivant", 4, partie,Carte.effetPost);
		joueurSelectionne = null;
	}
	
	public void capacite() throws SelectionDuJoueurNonFaiteException {
		if(joueurSelectionne != null)
		{
			partie.setCouronne(joueurSelectionne); //L'empereur donne la couronne au joueur qu'il a s�lectionn�
			joueurSelectionne = null;
		}
		else
		{
			throw new SelectionDuJoueurNonFaiteException(); //Si l'empereur n'a choisi personne, il y a exception.
		}
	}
	
	/**
	* M�thode de s�lection du joueur � couronner
	* @param j le joueur choisi par l'empereur.
	*/
	public void selectJoueur(Joueur j) {
		joueurSelectionne = j;
	}
}