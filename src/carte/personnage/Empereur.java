package carte.personnage;

import jeu.Joueur;
import jeu.Partie;
import carte.Carte;
import carte.Famille;

/**
* @author Bauchet ClÃ©ment
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
			partie.setCouronne(joueurSelectionne);
			joueurSelectionne = null;
		}
		else
		{
			throw new SelectionDuJoueurNonFaiteException();
		}
	}
	
	public void selectJoueur(Joueur j) {
		joueurSelectionne = j;
	}
}