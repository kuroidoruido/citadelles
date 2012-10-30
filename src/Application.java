import java.util.LinkedList;

import jeu.NombreDeJoueurIncorrectException;
import jeu.Partie;

/**
* @author Bauchet Clément
* @author Pena Anthony
* @version 29 oct. 2012
*
*/
public class Application {

	/**
	 * @param args
	 * @throws NombreDeJoueurIncorrectException 
	 */
	public static void main(String[] args) throws NombreDeJoueurIncorrectException {
		// On liste les joueurs
		LinkedList<String> listeJoueur = new LinkedList<String>();
		listeJoueur.add("Joueur 1");
		listeJoueur.add("Joueur 2");
		listeJoueur.add("Joueur 3");
		
		// On créait une partie
		Partie partie = new Partie(listeJoueur);
		
		// On sélectionne les personnages qui seront joué
		// On attribut un personnage à chaque joueur
		
	}

}
