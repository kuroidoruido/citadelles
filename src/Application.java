import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import carte.Carte;
import carte.personnage.Empereur;
import carte.personnage.Personnage;
import carte.quartier.Quartier;

import jeu.Joueur;
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
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// On liste les joueurs
		LinkedList<String> listeJoueur = new LinkedList<String>();
		listeJoueur.add("Joueur 1");
		listeJoueur.add("Joueur 2");
		listeJoueur.add("Joueur 3");
		
		// On créait une partie initialisée
		Partie partie = new Partie(listeJoueur);
		
		// Variables nécessaire au déroulement d'un tour de jeu
		ArrayList<Personnage> listePersoChoisit;
		Iterator<Quartier> iteQuartier;
		Quartier quartierOk;
		do
		{
			// On sélectionne les personnages qui seront joués
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
					partie.selectPerso(listePersoChoisit);
				}
			}
			// On vérifie que toutes les conditions sont respectées
			partie.verifierPersoSelectionne();
			
			// On attribut un personnage à chaque joueur
			Collections.shuffle(partie.getListePersoJoue());
			for(Joueur j : partie.getListeJoueur())
			{
				j.setPerso(partie.getListePersoJoue().remove(0));
			}
			int droitEffet;
			// Chaque joueur joue son tour selon l'ordre des Personnages.
			for(Joueur j : partie)
			{
				// On autorise le joueur utiliser son effet
				droitEffet = 1;
				// le Joueur gagne une pièce d'or pour chaque quartier construit
				j.addOrQuartier();
				// Un joueur peu soit piocher 2 Quartier soit prendre 2 pièces
				if(Math.random() < 0.5) // true or false aléatoirement
				{
					j.addOr(2);
				}
				else
				{
					partie.piocherQuartier(j, 2);
				}
				
				// Si la capacité de son personnage peut être appliqué maintenant, le joueur peut l'activer
				if(droitEffet > 0 && (j.getPerso().getInstantEffet() == Carte.effetPre || j.getPerso().getInstantEffet() == Carte.effetPreOuPost))
				{
					j.getPerso().capacite();
					droitEffet--;
				}
				
				// Un joueur peut construire un quartier
				if(Math.random() < 0.5)
				{
					quartierOk = null;
					iteQuartier = j.getMain().iterator();
					while(iteQuartier.hasNext() && quartierOk == null)
					{
						quartierOk = iteQuartier.next();
						if(quartierOk.getPrix() < j.getOr())
						{
							quartierOk = null;
						}
					}
					if(quartierOk != null)
					{
						j.construireQuartier(quartierOk);
					}
				}
				
				// Si la capacité de son personnage peut être appliqué maintenant, le joueur peut l'activer
				if(droitEffet > 0 && j.getPerso().getInstantEffet() == Carte.effetPost)
				{	
					if(j.getPerso().getNom() == "Empereur")
					{
						((Empereur)j.getPerso()).selectJoueur(partie.getListeJoueur().get((new Double(Math.random()*partie.getNbJoueur()).intValue())));
					}
					j.getPerso().capacite();
					droitEffet--;
				}
				System.out.println(j+"\n");
			}
			System.out.println();
		// Si aucun joueur n'a construit 8 quartiers, on refait un tour de jeu
		}
		while(partie.huitQuartiers());
		System.out.println(partie.getClassement().size());
	}
}
