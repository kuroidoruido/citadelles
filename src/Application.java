import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import carte.personnage.Personnage;

import jeu.Joueur;
import jeu.NombreDeJoueurIncorrectException;
import jeu.NombreDePersonnageSelectionneIncorrectException;
import jeu.Partie;
import jeu.PasDeRoiOuDEmpereurException;
import jeu.PlusieursPersonnageDuMemeOrdreException;

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
	 * @throws PasDeRoiOuDEmpereurException 
	 * @throws PlusieursPersonnageDuMemeOrdreException 
	 * @throws NombreDePersonnageSelectionneIncorrectException 
	 */
	public static void main(String[] args) throws NombreDeJoueurIncorrectException, NombreDePersonnageSelectionneIncorrectException, PlusieursPersonnageDuMemeOrdreException, PasDeRoiOuDEmpereurException {
		// On liste les joueurs
		LinkedList<String> listeJoueur = new LinkedList<String>();
		listeJoueur.add("Joueur 1");
		listeJoueur.add("Joueur 2");
		listeJoueur.add("Joueur 3");
		
		// On créait une partie
		Partie partie = new Partie(listeJoueur);
		
		// On sélectionne les personnages qui seront joués
		ArrayList<Personnage> listePersoChoisit;
		Collections.shuffle(partie.getPilePerso());
		listePersoChoisit = new ArrayList<Personnage>();
		for(Personnage p : partie.getPilePerso())
		{
			if(!partie.ordreDejaSelectionne(p))
			{
				if(p.getOrdre() < 9)
				{
					listePersoChoisit.add(p);
				}
				else if(partie.getNbJoueur() == 8)
				{
					listePersoChoisit.add(p);
				}
			}
		}
		partie.selectPerso(listePersoChoisit);
		System.out.println(partie.getListePersoJoue());
		partie.verifierPersoSelectionne();
		
		// On attribut un personnage à chaque joueur
		Collections.shuffle(partie.getListePersoJoue());
		for(Joueur j : partie.getListeJoueur())
		{
			j.setPerso(partie.getListePersoJoue().remove(0));
		}
	}

}
