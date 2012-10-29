package jeu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import carte.Famille;
import carte.Merveille;
import carte.Quartier;
import carte.personnage.*;

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
		Famille religion = new Famille("Religion", Color.blue);
		Famille noblesse = new Famille("Noblesse", Color.yellow);
		Famille commerce = new Famille("Commerce", Color.green);
		Famille militaire = new Famille("Militaire", Color.red);
		Famille merveille = new Famille("Merveille", Color.magenta);

		pileQuartier.clear();
		pileQuartier.add(new Quartier("Temple",religion,1));
		pileQuartier.add(new Quartier("Temple",religion,1));
		pileQuartier.add(new Quartier("Temple",religion,1));
		pileQuartier.add(new Quartier("Eglise",religion,2));
		pileQuartier.add(new Quartier("Eglise",religion,2));
		pileQuartier.add(new Quartier("Eglise",religion,2));
		pileQuartier.add(new Quartier("Eglise",religion,2));
		pileQuartier.add(new Quartier("Monastère",religion,3));
		pileQuartier.add(new Quartier("Monastère",religion,3));
		pileQuartier.add(new Quartier("Monastère",religion,3));
		pileQuartier.add(new Quartier("Cathédrale",religion,5));
		pileQuartier.add(new Quartier("Cathédrale",religion,5));

		pileQuartier.add(new Quartier("Manoir",noblesse,3));
		pileQuartier.add(new Quartier("Manoir",noblesse,3));
		pileQuartier.add(new Quartier("Manoir",noblesse,3));
		pileQuartier.add(new Quartier("Manoir",noblesse,3));
		pileQuartier.add(new Quartier("Manoir",noblesse,3));
		pileQuartier.add(new Quartier("Château",noblesse,4));
		pileQuartier.add(new Quartier("Château",noblesse,4));
		pileQuartier.add(new Quartier("Château",noblesse,4));
		pileQuartier.add(new Quartier("Château",noblesse,4));
		pileQuartier.add(new Quartier("Palais",noblesse,5));
		pileQuartier.add(new Quartier("Palais",noblesse,5));

		pileQuartier.add(new Quartier("Taverne",commerce,1));
		pileQuartier.add(new Quartier("Taverne",commerce,1));
		pileQuartier.add(new Quartier("Taverne",commerce,1));
		pileQuartier.add(new Quartier("Taverne",commerce,1));
		pileQuartier.add(new Quartier("Taverne",commerce,1));
		pileQuartier.add(new Quartier("Échoppe",commerce,2));
		pileQuartier.add(new Quartier("Échoppe",commerce,2));
		pileQuartier.add(new Quartier("Échoppe",commerce,2));
		pileQuartier.add(new Quartier("Marché",commerce,2));
		pileQuartier.add(new Quartier("Marché",commerce,2));
		pileQuartier.add(new Quartier("Marché",commerce,2));
		pileQuartier.add(new Quartier("Marché",commerce,2));
		pileQuartier.add(new Quartier("Comptoir",commerce,3));
		pileQuartier.add(new Quartier("Comptoir",commerce,3));
		pileQuartier.add(new Quartier("Comptoir",commerce,3));
		pileQuartier.add(new Quartier("Port",commerce,4));
		pileQuartier.add(new Quartier("Port",commerce,4));
		pileQuartier.add(new Quartier("Port",commerce,4));
		pileQuartier.add(new Quartier("Hôtel de ville",commerce,5));
		pileQuartier.add(new Quartier("Hôtel de ville",commerce,5));

		pileQuartier.add(new Quartier("Tour de guet",militaire,1));
		pileQuartier.add(new Quartier("Tour de guet",militaire,1));
		pileQuartier.add(new Quartier("Tour de guet",militaire,1));
		pileQuartier.add(new Quartier("Prison",militaire,2));
		pileQuartier.add(new Quartier("Prison",militaire,2));
		pileQuartier.add(new Quartier("Prison",militaire,2));
		pileQuartier.add(new Quartier("Caserne",militaire,3));
		pileQuartier.add(new Quartier("Caserne",militaire,3));
		pileQuartier.add(new Quartier("Caserne",militaire,3));
		pileQuartier.add(new Quartier("Forteresse",militaire,5));
		pileQuartier.add(new Quartier("Forteresse",militaire,5));

		pileQuartier.add(new Merveille("Cour des miracles",merveille,2,"Pour le décompte final des points, la cour des miracles est considérée comme un quartier de la couleur de votre choix. Vous ne pouvez pas utilisez cette capacité si vous avez construit la cour des miracles au dernier tour de jeu.",0));
		pileQuartier.add(new Merveille("Donjon",merveille,3,"Le Donjon ne peut pas être détruit par le Condottière.",0));
		pileQuartier.add(new Merveille("Laboratoire",merveille,5,"Une fois par tour, vous pouvez vous défausser d'une carte quartier de votre main et recevoir une pièce d'or en contrepartie",0));
		pileQuartier.add(new Merveille("Manufacture",merveille,5,"Une fois par tour, vous pouvez payer trois pièces d'or pour piocher trois cartes.",0));
		pileQuartier.add(new Merveille("Observatoire",merveille,3,"Si vous choisissez de piocher des cartes au début de votre tour, vous en piochez trois, en choisissez une et défaussez les deux autres.",0));
		pileQuartier.add(new Merveille("Cimetière",merveille,5,"Lorsque le Condottière détruit un quartier, vous pouvez payer une pièce d'or pour le reprendre dans votre main. Vous ne pouvez pas faire cela si vous êtes vous-même Condottiere.",0));
		pileQuartier.add(new Merveille("Bibliothèque",merveille,6,"Si vous choisissez de piocher des cartes au début de votre tour, vous en piochez deux et les conservez toutes les deux.",0));
		pileQuartier.add(new Merveille("École de Magie",merveille,6,"Pour la perception des revenus, l'école de magie est considérée comme un quartier de la couleur de votre choix, elle vous rapporte donc si vous êtes, Roi, Evêque, Marchand ou Condottiere",0));
		pileQuartier.add(new Merveille("Université",merveille,6,"Cette réalisation de prestige (nul n'a jamais compris à quoi pouvait bien servir une université) coûte six pièces d'or à bâtir mais vaut huit points dans le décompte de fin de partie.",2));
		pileQuartier.add(new Merveille("Dracoport",merveille,6,"Cette réalisation de prestige (on n'a pas vu de dragon dans le Royaume depuis bientôt mille ans) coûte six pièces d'or à bâtir mais vaut huit points dans le décompte de fin de partie.",2));

		Collections.shuffle((List<Quartier>)(pileQuartier));

		pilePerso.clear();
		pilePerso.add(new Assassin(null,this));
		pilePerso.add(new Bailli(null,this));
		pilePerso.add(new Magicien(null,this));
		pilePerso.add(new Empereur(noblesse,this));
		pilePerso.add(new Roi(noblesse,this));
		pilePerso.add(new Abbe(religion,this));
		pilePerso.add(new Marchand(commerce,this));
		pilePerso.add(new Architecte(null,this));
		pilePerso.add(new Condottiere(militaire,this));
		pilePerso.add(new Reine(noblesse,this));

		//Collections.shuffle((List<Personnage>)(pilePerso));
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
