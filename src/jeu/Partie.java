package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import carte.Famille;
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
	private Iterator<Joueur> iteJoueur;
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
	 * @param p le personnage à attribuer au joueur courant
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
	
	public boolean joueurSuivant() {
		if(iteJoueur == null)
		{
			iteJoueur = listeJoueur.iterator();
		}
		
		if(iteJoueur.hasNext())
		{
			joueurCourant = iteJoueur.next();
		}
	return false;
	}
}
