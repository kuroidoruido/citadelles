import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import carte.InstantEffet;
import carte.batiment.Batiment;
import carte.personnage.Empereur;
import carte.personnage.Personnage;

import jeu.BatimentDejaConstruiteException;
import jeu.BatimentPasDansLaMainException;
import jeu.Joueur;
import jeu.NombreDeJoueurIncorrectException;
import jeu.NombreDePersonnageSelectionneIncorrectException;
import jeu.Partie;
import jeu.PasAssezDOrException;
import jeu.PasDeRoiOuDEmpereurException;
import jeu.PlusDeBatimentDansLaPileException;
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
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		// On liste les joueurs
		LinkedList<String> listeJoueur = new LinkedList<String>();
		listeJoueur.add("Joueur 1");
		listeJoueur.add("Joueur 2");
		listeJoueur.add("Joueur 3");
		
		// On créait une partie initialisée
		Partie partie = null;
		try {
			partie = new Partie(listeJoueur);
		} catch (NombreDeJoueurIncorrectException e) {
			System.out.println("Le nombre de joueur indiqué est incorrect !");
			System.exit(0);
		}
		
		// Variables nécessaire au déroulement d'un tour de jeu
		ArrayList<Personnage> listePersoChoisit;
		Iterator<Batiment> iteBatiment;
		Batiment batimentOk;
		do
		{
			System.out.println(" --- DEBUT TOUR "+partie.getNombreDeTour()+" --- ");
			// On sélectionne les personnages qui seront joués
			Collections.shuffle(partie.getPilePerso());
			listePersoChoisit = new ArrayList<Personnage>();
			partie.selectPerso(listePersoChoisit);
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
			try {
				partie.verifierPersoSelectionne();
			} catch (NombreDePersonnageSelectionneIncorrectException e) {
				System.out.println("Le nombre de personnage sélectionné est incorrect !");
				System.exit(0);
			} catch (PlusieursPersonnageDuMemeOrdreException e) {
				System.out.println("Plusieurs personnage du même ordre ont été sélectionnés !");
				System.exit(0);
			} catch (PasDeRoiOuDEmpereurException e) {
				System.out.println("Il faut sélectionné le Roi ou l'Empereur !");
				System.exit(0);
			}
			
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
				// On donne le droit au joueur de construire
				j.setDroitConstruction(1);
				// le Joueur gagne une pièce d'or pour chaque quartier construit
				j.addOrQuartier();
				// Un joueur peu soit piocher 2 Quartier soit prendre 2 pièces
				if(!partie.pileBatimentVide() && Math.random() < 0.5) // true or false aléatoirement
				{
					try {
						partie.piocherBatiment(j, 2);
					} catch (PlusDeBatimentDansLaPileException e) {
						System.out.println("Impossible de piocher la pile de Batiment est vide.");
					}
				}
				else
				{
					j.addOr(2);
				}
				
				// Si la capacité de son personnage peut être appliqué maintenant, le joueur peut l'activer
				if(droitEffet > 0 && (j.getPerso().getInstantEffet().getValeur() == InstantEffet.effetPre || j.getPerso().getInstantEffet().getValeur() == InstantEffet.effetPreOuPost))
				{
					try {
						j.getPerso().capacite();
					} catch (Exception e) {
						System.out.println("Des conditions n'ont pas été respecté pour activer l'effet d'un personnage !");
					}
					droitEffet--;
				}
				
				// Un joueur peut construire un quartier
				if(j.getOr() > 0 && Math.random() < 0.5)
				{
					while(j.getDroitConstruction() > 0)
					{
						batimentOk = null;
						iteBatiment = j.getMain().iterator();
						while(iteBatiment.hasNext() && batimentOk == null)
						{
							batimentOk = iteBatiment.next();
							if(batimentOk.getPrix() > j.getOr())
							{
								batimentOk = null;
							}
						}
						if(batimentOk != null)
						{
							try {
								j.construireBatiment(batimentOk);
							} catch (BatimentPasDansLaMainException e) {
								System.out.println("Vous ne pouvez pas construire un batiment qui n'est pas dans votre main !");
							} catch (BatimentDejaConstruiteException e) {
								System.out.println("Le batiment sélectionné a déjà été construit !");
							} catch (PasAssezDOrException e) {
								System.out.println("Vous ne possédez pas assez d'or pour construire ce batiment !");
							}
						}
						else
						{
							// Si aucun batiment de la main ne peut être construit on sort de la boucle
							j.setDroitConstruction(0);
						}
					}
				}
				
				// Si la capacité de son personnage peut être appliqué maintenant, le joueur peut l'activer
				if(droitEffet > 0 && j.getPerso().getInstantEffet().getValeur() == InstantEffet.effetPost)
				{	
					if(j.getPerso().getNom() == "Empereur")
					{
						((Empereur)j.getPerso()).selectJoueur(partie.getListeJoueur().get((new Double(Math.random()*partie.getNbJoueur()).intValue())));
					}
					try {
						j.getPerso().capacite();
					} catch (Exception e) {
						System.out.println("Des conditions n'ont pas été respecté pour activer l'effet d'un personnage !");
					}
					droitEffet--;
				}
				System.out.println(j+"\nPersonnage = "+j.getPerso()+"\n");
			}
			System.out.println(" --- FIN TOUR "+partie.getNombreDeTour()+" --- \n");
			partie.tourSuivant();
		// Si aucun joueur n'a construit 8 quartiers, on refait un tour de jeu
		}
		while(!partie.huitBatiments());
		int i = 1;
		for(Joueur j : partie.getClassement())
		{
			System.out.println(i+" "+j.getNom()+" ("+j.calculerPoints()+"pts)");
			i++;
		}
	}
}
