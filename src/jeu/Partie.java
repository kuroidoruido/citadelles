package jeu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import carte.Famille;
import carte.Quartier;
import carte.Merveille;
import carte.personnage.*;

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
		pileQuartier.add(new Quartier("Monast�re",religion,3));
		pileQuartier.add(new Quartier("Monast�re",religion,3));
		pileQuartier.add(new Quartier("Monast�re",religion,3));
		pileQuartier.add(new Quartier("Cath�drale",religion,5));
		pileQuartier.add(new Quartier("Cath�drale",religion,5));
		
		pileQuartier.add(new Quartier("Manoir",noblesse,3));
		pileQuartier.add(new Quartier("Manoir",noblesse,3));
		pileQuartier.add(new Quartier("Manoir",noblesse,3));
		pileQuartier.add(new Quartier("Manoir",noblesse,3));
		pileQuartier.add(new Quartier("Manoir",noblesse,3));
		pileQuartier.add(new Quartier("Ch�teau",noblesse,4));
		pileQuartier.add(new Quartier("Ch�teau",noblesse,4));
		pileQuartier.add(new Quartier("Ch�teau",noblesse,4));
		pileQuartier.add(new Quartier("Ch�teau",noblesse,4));
		pileQuartier.add(new Quartier("Palais",noblesse,5));
		pileQuartier.add(new Quartier("Palais",noblesse,5));
		
		pileQuartier.add(new Quartier("Taverne",commerce,1));
		pileQuartier.add(new Quartier("Taverne",commerce,1));
		pileQuartier.add(new Quartier("Taverne",commerce,1));
		pileQuartier.add(new Quartier("Taverne",commerce,1));
		pileQuartier.add(new Quartier("Taverne",commerce,1));
		pileQuartier.add(new Quartier("�choppe",commerce,2));
		pileQuartier.add(new Quartier("�choppe",commerce,2));
		pileQuartier.add(new Quartier("�choppe",commerce,2));
		pileQuartier.add(new Quartier("March�",commerce,2));
		pileQuartier.add(new Quartier("March�",commerce,2));
		pileQuartier.add(new Quartier("March�",commerce,2));
		pileQuartier.add(new Quartier("March�",commerce,2));
		pileQuartier.add(new Quartier("Comptoir",commerce,3));
		pileQuartier.add(new Quartier("Comptoir",commerce,3));
		pileQuartier.add(new Quartier("Comptoir",commerce,3));
		pileQuartier.add(new Quartier("Port",commerce,4));
		pileQuartier.add(new Quartier("Port",commerce,4));
		pileQuartier.add(new Quartier("Port",commerce,4));
		pileQuartier.add(new Quartier("H�tel de ville",commerce,5));
		pileQuartier.add(new Quartier("H�tel de ville",commerce,5));
		
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
		
		pileQuartier.add(new Merveille("Cour des miracles",merveille,2,"Pour le d�compte final des points, la cour des miracles est consid�r�e comme un quartier de la couleur de votre choix. Vous ne pouvez pas utilisez cette capacit� si vous avez construit la cour des miracles au dernier tour de jeu.",0));
		pileQuartier.add(new Merveille("Donjon",merveille,3,"Le Donjon ne peut pas �tre d�truit par le Condotti�re.",0));
		pileQuartier.add(new Merveille("Laboratoire",merveille,5,"Une fois par tour, vous pouvez vous d�fausser d'une carte quartier de votre main et recevoir une pi�ce d'or en contrepartie",0));
		pileQuartier.add(new Merveille("Manufacture",merveille,5,"Une fois par tour, vous pouvez payer trois pi�ces d'or pour piocher trois cartes.",0));
		pileQuartier.add(new Merveille("Observatoire",merveille,3,"Si vous choisissez de piocher des cartes au d�but de votre tour, vous en piochez trois, en choisissez une et d�faussez les deux autres.",0));
		pileQuartier.add(new Merveille("Cimeti�re",merveille,5,"Lorsque le Condotti�re d�truit un quartier, vous pouvez payer une pi�ce d'or pour le reprendre dans votre main. Vous ne pouvez pas faire cela si vous �tes vous-m�me Condottiere.",0));
		pileQuartier.add(new Merveille("Biblioth�que",merveille,6,"Si vous choisissez de piocher des cartes au d�but de votre tour, vous en piochez deux et les conservez toutes les deux.",0));
		pileQuartier.add(new Merveille("�cole de Magie",merveille,6,"Pour la perception des revenus, l'�cole de magie est consid�r�e comme un quartier de la couleur de votre choix, elle vous rapporte donc si vous �tes, Roi, Ev�que, Marchand ou Condottiere",0));
		pileQuartier.add(new Merveille("Universit�",merveille,6,"Cette r�alisation de prestige (nul n'a jamais compris � quoi pouvait bien servir une universit�) co�te six pi�ces d'or � b�tir mais vaut huit points dans le d�compte de fin de partie.",2));
		pileQuartier.add(new Merveille("Dracoport",merveille,6,"Cette r�alisation de prestige (on n'a pas vu de dragon dans le Royaume depuis bient�t mille ans) co�te six pi�ces d'or � b�tir mais vaut huit points dans le d�compte de fin de partie.",2));
	
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
		
		Collections.shuffle((List<Personnage>)(pilePerso));
		
		
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
