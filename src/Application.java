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
* @author Bauchet Cl�ment
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
		
		// On cr�e une nouvelle partie initialis�e
		Partie partie = new Partie(listeJoueur);
		
		// Variables n�cessaires au d�roulement d'un tour de jeu
		ArrayList<Personnage> listePersoChoisit; //Liste des personnages choisis par les joueurs
		Iterator<Quartier> iteQuartier; //Un it�rateur pour parcourir la main des joueurs
		Quartier quartierOk;
		do
		{
			// On s�lectionne les personnages qui seront jou�s
			Collections.shuffle(partie.getPilePerso()); //On m�lange la pile de personnages � piocher
			listePersoChoisit = new ArrayList<Personnage>();
			for(Personnage p : partie.getPilePerso()) //On parcourt chaque personnage de la pile
			{
				if(!partie.ordreDejaSelectionne(p)) //On s'assure de ne pas piocher 2 personnages de m�me ordre
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
			// On v�rifie que toutes les conditions sont respect�es
			partie.verifierPersoSelectionne();
			
			// On attribue un personnage � chaque joueur
			Collections.shuffle(partie.getListePersoJoue());
			for(Joueur j : partie.getListeJoueur())
			{
				j.setPerso(partie.getListePersoJoue().remove(0));
			}
			int droitEffet;
			// Chaque joueur joue son tour selon l'ordre des Personnages.
			for(Joueur j : partie)
			{
				// On autorise le joueur � utiliser son effet
				droitEffet = 1;
				// le Joueur gagne une pi�ce d'or pour chaque quartier de sa famille construit
				j.addOrQuartier();
				// Un joueur peut soit piocher 2 Quartiers soit prendre 2 pi�ces
				if(Math.random() < 0.5) //Ici, le choix est fait al�atoirement
				{
					j.addOr(2);
				}
				else
				{
					partie.piocherQuartier(j, 2);
				}
				
				// Si la capacit� de son personnage peut �tre appliqu�e maintenant, le joueur peut l'activer
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
				
				// Si la capacit� de son personnage peut �tre appliqu�e maintenant, le joueur peut l'activer
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
