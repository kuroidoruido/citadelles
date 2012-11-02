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
	public static void main(String[] args) {
		// On liste les joueurs
		LinkedList<String> listeJoueur = new LinkedList<String>();
		listeJoueur.add("Joueur 1");
		listeJoueur.add("Joueur 2");
		listeJoueur.add("Joueur 3");
		
		// On cr�e une partie initialis�e
		Partie partie = null;
		try {
			partie = new Partie(listeJoueur);
		} catch (NombreDeJoueurIncorrectException e) {
			System.out.println("Le nombre de joueur indiqu� est incorrect !");
			System.exit(0);
		}
		
		// Variables n�cessaire au d�roulement d'un tour de jeu
		ArrayList<Personnage> listePersoChoisit; //Liste des personnages choisis par les joueurs
		Iterator<Batiment> iteBatiment; //Un it�rateur pour parcourir la main des joueurs
		Batiment batimentOk;
		do
		{
			System.out.println(" --- DEBUT TOUR "+partie.getNombreDeTour()+" --- ");
			// On s�lectionne les personnages qui seront jou�s
			Collections.shuffle(partie.getPilePerso()); //On m�lange la pile de personnages � piocher
			listePersoChoisit = new ArrayList<Personnage>();
			partie.selectPerso(listePersoChoisit);
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
			try {
				partie.verifierPersoSelectionne();
			} catch (NombreDePersonnageSelectionneIncorrectException e) {
				System.out.println("Le nombre de personnage s�lectionn� est incorrect !");
				System.exit(0);
			} catch (PlusieursPersonnageDuMemeOrdreException e) {
				System.out.println("Plusieurs personnage du m�me ordre ont �t� s�lectionn�s !");
				System.exit(0);
			} catch (PasDeRoiOuDEmpereurException e) {
				System.out.println("Il faut s�lectionn� le Roi ou l'Empereur !");
				System.exit(0);
			}
			
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
				// On autorise le joueur utiliser son effet
				droitEffet = 1;
				// On donne le droit au joueur de construire
				j.setDroitConstruction(1);
				// le Joueur gagne une pi�ce d'or pour chaque quartier de sa famille construit
				j.addOrBatiment();
				// Un joueur peut soit piocher 2 b�timents soit prendre 2 pi�ces
				if(!partie.pileBatimentVide() && Math.random() < 0.5) //Ici, le choix est fait al�atoirement
				{
					try {
						partie.piocherBatiment(j, 2);
					} catch (PlusDeBatimentDansLaPileException e) {
						System.out.println("Impossible de piocher : la pile de Batiment est vide.");
					}
				}
				else
				{
					j.addOr(2);
				}
				
				// Si la capacit� de son personnage peut �tre appliqu� maintenant, le joueur peut l'activer
				if(droitEffet > 0 && (j.getPerso().getInstantEffet().getValeur() == InstantEffet.effetPre || j.getPerso().getInstantEffet().getValeur() == InstantEffet.effetPreOuPost))
				{
					try {
						j.getPerso().capacite();
					} catch (Exception e) {
						System.out.println("Des conditions n'ont pas �t� respect� pour activer l'effet d'un personnage !");
					}
					droitEffet--;
				}
				
				// Un joueur peut construire un b�timent
				if(j.getOr() > 0 && Math.random() < 0.5)
				{
					while(j.getDroitConstruction() > 0)
					{
						batimentOk = null;
						iteBatiment = j.getMain().iterator();
						while(iteBatiment.hasNext() && batimentOk == null)
						{
							batimentOk = iteBatiment.next();
							if(batimentOk.getPrix() > j.getOr()) //Le joueur doit avoir assez d'or pour construire
							{
								batimentOk = null;
							}
						}
						if(batimentOk != null)
						{
							try {
								j.construireBatiment(batimentOk);
							} catch (BatimentPasDansLaMainException e) {
								System.out.println("Vous ne pouvez pas construire un b�timent qui n'est pas dans votre main !");
							} catch (BatimentDejaConstruiteException e) {
								System.out.println("Le batiment s�lectionn� a d�j� �t� construit !");
							} catch (PasAssezDOrException e) {
								System.out.println("Vous ne poss�dez pas assez d'or pour construire ce b�timent !");
							}
						}
						else
						{
							// Si aucun batiment de la main ne peut �tre construit on sort de la boucle
							j.setDroitConstruction(0);
						}
					}
				}
				
				// Si la capacit� de son personnage peut �tre appliqu� maintenant, le joueur peut l'activer
				if(droitEffet > 0 && j.getPerso().getInstantEffet().getValeur() == InstantEffet.effetPost)
				{	
					if(j.getPerso().getNom() == "Empereur")
					{
						((Empereur)j.getPerso()).selectJoueur(partie.getListeJoueur().get((new Double(Math.random()*partie.getNbJoueur()).intValue())));
					}
					try {
						j.getPerso().capacite();
					} catch (Exception e) {
						System.out.println("Des conditions n'ont pas �t� respect�es pour activer l'effet d'un personnage !");
					}
					droitEffet--;
				}
				System.out.println(j+"\nPersonnage = "+j.getPerso()+"\n");
			}
			System.out.println(" --- FIN TOUR "+partie.getNombreDeTour()+" --- \n");
			partie.tourSuivant();
		// Si aucun joueur n'a construit 8 b�timents, on refait un tour de jeu
		}
		while(!partie.huitBatiments());
		int i = 1;
		for(Joueur j : partie.getClassement()) //A la fin de la partie, on affiche le classsement des joueurs.
		{
			System.out.println(i+" "+j.getNom()+" ("+j.calculerPoints()+"pts)");
			i++;
		}
	}
}
