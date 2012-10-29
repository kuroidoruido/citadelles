package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import carte.Quartier;
import carte.personnage.Personnage;

/**
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 24 oct. 2012
 *
 */
public class Partie {
	
	private int nombreDeTour;
	private LinkedList<Joueur> listeJoueur;
	private Joueur joueurCourant;
	private LinkedList<Quartier> pileQuartier;
	private ArrayList<Personnage> pilePerso;
	
	/** Constructeur de Partie, initialise la partie en instanciant les listes et en distribuant les cartes.
	 * 
	 */
	public Partie() {
		super();
		this.nombreDeTour = 0;
		this.listeJoueur = new LinkedList<Joueur>();
		this.joueurCourant = null;
		this.pileQuartier = new LinkedList<Quartier>();
		this.pilePerso = new ArrayList<Personnage>();
		initialiser();
	}

	/**
	 * @return le nombre de tour écoulé
	 */
	public int getNombreDeTour() {
		return nombreDeTour;
	}
	
	/**
	 * @return le joueur courant
	 */
	public Joueur getJoueurCourant() {
		return joueurCourant;
	}
	
	/**
	 * @return la listeJoueur
	 */
	public LinkedList<Joueur> getListeJoueur() {
		return listeJoueur;
	}
	
	/**
	 * 
	 * @return true si un bailli est présent en jeu
	 */
	public Joueur isThereBailli() {
		Joueur tmp = null;
		for(Joueur j : listeJoueur)
		{
			if(j.getPerso().getNom().equals("Bailli"))
			{
				tmp = j;
			}
		}
		return tmp;
	}
	
	/**
	 * @param nbQuartier le nombre de quartier à piocher
	 * @return les nbQuartier premiers quartiers de la pile de quartier
	 */
	public void piocherQuartier(int nbQuartier) {
		for(int i=0; i<nbQuartier; i++)
		{
			joueurCourant.addCarteMain(pileQuartier.removeFirst());
		}
	}
	
	/**
	 * @param p le personnage à attribuer au joueur courant
	 */
	public void choisirPerso(Personnage p) {
		joueurCourant.setPerso(p);
	}
	
	/**
	 * @return true si au moins un des joueurs à construit 8 quartiers
	 */
	public boolean huitQuartiers() {
		boolean retour = false;
		for(Joueur j : listeJoueur)
		{
			retour = (retour || j.huitQuartier());
		}
		return retour;
	}
	
	private void initialiser() {
/* ------------------------------------------------ A FAIRE ------------------------------- */
	}
	
	/** Méthode qui donne le nombre indiqué de pièces d'or au joueur courant
	 * 
	 * @param nbPieces le nombre de pièces à donner
	 */
	public void donnerOr(int nbPieces) {
		joueurCourant.addOr(nbPieces);
	}
	
	/** Méthode permettant de passer au joueur suivant, et d'indiquer quand le tour se termine.
	 * 
	 * @return false dès qu'on passe au tour suivant.
	 */
	public boolean joueurSuivant() {
		boolean suivant = true;
		Iterator<Joueur> iteJoueur = listeJoueur.iterator();
		
		int next = joueurCourant.getPerso().getOrdre()+1;
		if(next < 1 || 9 < next)
		{
			suivant = false;
		}
		
		if(suivant)
		{
			Joueur j;
			while(iteJoueur.hasNext() && next != 0)
			{
				j = iteJoueur.next();
				if(j.getPerso().getOrdre() == next)
				{
					joueurCourant = j;
					next = 0;
				}
			}
		}
	return suivant;
	}
}
