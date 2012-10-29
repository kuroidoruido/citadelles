package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import carte.Famille;
import carte.Quartier;
import carte.personnage.Personnage;

/**
 * @author Bauchet Cl�ment
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
	
	/**
	 * @param nombreDeTour
	 * @param listeJoueur
	 * @param joueurCourant
	 * @param pileQuartier
	 * @param pilePerso
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
	 * @return le nombre de tour �coul�
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
	 * @return le listeJoueur
	 */
	public LinkedList<Joueur> getListeJoueur() {
		return listeJoueur;
	}
	
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
	 * @return le premier quartier de la pile de quartier
	 */
	public void piocherQuartier(int nbQuartier) {
		for(int i=0; i<nbQuartier; i++)
		{
			joueurCourant.addCarteMain(pileQuartier.removeFirst());
		}
	}
	
	/**
	 * @param p le personnage � attribuer au joueur courant
	 */
	public void choisirPerso(Personnage p) {
		joueurCourant.setPerso(p);
	}
	
	/**
	 * @return
	 */
	public boolean huitQuartiers() {
		boolean retour = false;
		for(Joueur j : listeJoueur)
		{
			retour = (retour || j.huitQuartier());
		}
		return retour;
	}
	
	public void calculPoints() {
		for(Joueur j : listeJoueur)
		{
			j.calculerPoints();
		}
	}
	
	private void initialiser() {
/* ------------------------------------------------ A FAIRE ------------------------------- */
	}
	
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
