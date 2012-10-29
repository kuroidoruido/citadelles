import java.util.LinkedList;

import jeu.Joueur;
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
	 */
	public static void main(String[] args) {
		LinkedList<String> listeJoueur = new LinkedList<String>();
		listeJoueur.add("Joueur 1");
		listeJoueur.add("Joueur 2");
		listeJoueur.add("Joueur 3");
		
		Partie partie = null;
		try {
			partie = new Partie(listeJoueur);
		} catch (NombreDeJoueurIncorrectException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}
