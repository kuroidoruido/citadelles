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
		
		// On crée une nouvelle partie initialisée
		Partie partie = new Partie(listeJoueur);
		
		// Variables nécessaires au déroulement d'un tour de jeu
		ArrayList<Personnage> listePersoChoisit; //Liste des personnages choisis par les joueurs
		Iterator<Quartier> iteQuartier; //Un itérateur pour parcourir la main des joueurs
		Quartier quartierOk;
		do
		{
			// On sélectionne les personnages qui seront joués
			Collections.shuffle(partie.getPilePerso()); //On mélange la pile de personnages à piocher
			listePersoChoisit = new ArrayList<Personnage>();
			for(Personnage p : partie.getPilePerso()) //On parcourt chaque personnage de la pile
			{
				if(!partie.ordreDejaSelectionne(p)) //On s'assure de ne pas piocher 2 personnages de même ordre
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
			
			// On attribue un personnage à chaque joueur
			Collections.shuffle(partie.getListePersoJoue());
			for(Joueur j : partie.getListeJoueur())
			{
				j.setPerso(partie.getListePersoJoue().remove(0));
			}
			int droitEffet;
			// Chaque joueur joue son tour selon l'ordre des Personnages.
			for(Joueur j : partie)
			{
				// On autorise le joueur à utiliser son effet
				droitEffet = 1;
				// le Joueur gagne une pièce d'or pour chaque quartier de sa famille construit
				j.addOrQuartier();
				// Un joueur peut soit piocher 2 Quartiers soit prendre 2 pièces
				if(Math.random() < 0.5) //Ici, le choix est fait aléatoirement
				{
					j.addOr(2);
				}
				else
				{
					partie.piocherQuartier(j, 2);
				}
				
				// Si la capacité de son personnage peut être appliquée maintenant, le joueur peut l'activer
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
						if(quartierOk.getPrix() < j.getOr()) //Le joueur doit avoir assez d'or pour construire
						{
							quartierOk = null;
						}
					}
					if(quartierOk != null)
					{
						j.construireQuartier(quartierOk);
					}
				}
				
				// Si la capacité de son personnage peut être appliquée maintenant, le joueur peut l'activer
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
		System.out.println(partie.getClassement().size()); //A la fin de la partie, on affiche le classsement des joueurs.
	}
}
