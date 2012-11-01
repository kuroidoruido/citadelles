package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

import carte.Famille;
import carte.personnage.Personnage;
import carte.quartier.ComparatorQuartierCout;
import carte.quartier.ComparatorQuartierFamille;
import carte.quartier.Quartier;

/**
 * Classe modélisant les différents joueurs d'une partie
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 24 oct. 2012
 *
 */
public class Joueur {
	
	private String nom;
	private TreeSet<Quartier> main;
	private Personnage perso;
	private int or;
	private ArrayList<Quartier> quartierConstruit;
	private int droitConstruction;
	
	private Partie partie;
	
	/**
	 * Contructeur de Joueur.
	 * @param nom le nom du joueur
	 */
	public Joueur(String nom, Partie partie) {
		super();
		this.nom = nom;
		this.main = new TreeSet<Quartier>();
		this.perso = null;
		this.or = 2;
		this.quartierConstruit = new ArrayList<Quartier>();
		this.droitConstruction = 1;
		this.partie = partie;
	}
	
	/**
	 * Méthode permettant de récupérer le nom du joueur
	 * @return le nom du joueur
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Méthode permettant de récupérer la main du joueur
	 * @return la liste des quartiers que le joueur a en main
	 */
	public TreeSet<Quartier> getMain() {
		return main;
	}
	
	/**
	 * Méthode permettant d'ajouter une carte dans la main du joueur
	 * @param le quartier à ajouter
	 */
	public void addCarteMain(Quartier q) {
		main.add(q);
	}
	
	/**
	 * Méthode permettant de connaître le personnage joué par le joueur.
	 * @return le personnage attribué au joueur
	 */
	public Personnage getPerso() {
		return perso;
	}
	
	/**
	 * Méthode permettant d'attribuer un personnage au joueur.
	 * @param p le personnage à attribuer au joueur
	 */
	public void setPerso(Personnage p) {
		perso = p;
	}
	
	/**
	 * Méthode permettant de récupérer la quantité d'or possédée par le joueur
	 * @return le nombre de pièces d'or disponible
	 */
	public int getOr() {
		return or;
	}
	
	/**
	 * Méthode permettant de donner de l'or au joueur
	 * @param le nombre de pièces d'or à donner
	 */
	public void addOr(int nbPieces) {
		or += nbPieces;
	}
	
	/**
	 * Méthode permettant au joueur de gagner de l'or en fonction des quartiers qu'il a construit
	 * Chaque quartier de la même famille que le personnage du joueur lui fait gagner 1 pièce d'or
	 */
	public void addOrQuartier() {
		Famille famillePerso = perso.getFamille(); //On regarde la famille du personnage joué par le joueur
		if(famillePerso != null)
		{
			for(Quartier q : quartierConstruit) //On parcourt la liste des quartiers construits par le joueur
			{
				if(q.getFamille() == famillePerso) //Si un quartier appartient à la même famille que le personnage, le joueur gagne 1 pièce d'or
				{
					or += 1;
				}
			}
		}
	}
		
	/**
	 * Méthode permettant d'enlever de l'or au joueur, par exemple lorsqu'il construit un quartier, paye un autre joueur ou se fait voler...
	 * @param le nombre de pièces d'or à retirer
	 */
	public void subOr(int nbPieces) throws PasAssezDOrException {
		if(nbPieces <= or) //Si le joueur a assez d'or, on lui en enlève.
		{
			or -= nbPieces;
		}
		else //Si le joueur n'a pas assez d'or, on lève une exception.
		{
			throw new PasAssezDOrException();
		}
	}
	
	
	/**
	 * Méthode permettant au joueur de construire un nouveau quartier.
	 * @param le quartier à construire
	 */
	public void construireQuartier(Quartier q) throws QuartierPasDansLaMainException, QuartierDejaConstruiteException {
		if(main.contains(q)) //On vérifie d'abord que le quartier est bien dans la main du joueur
		{
			if(quartierConstruit.contains(q)) //On vérifie si le quartier n'a pas déjà été construit
			{
				throw new QuartierDejaConstruiteException(); //Si c'est le cas, on lève une exception
			}
			Joueur bailli = partie.isThereBailli(); //On vérifie la présence d'un Bailli parmi les personnages
			if(bailli != null) //S'il y a un Bailli, le joueur paye le prix du quartier, plus une pièce d'or pour le Bailli.
			{
				try {
					subOr(q.getPrix()+1);
					bailli.addOr(1);
				} catch (PasAssezDOrException e) {
					e.printStackTrace();
				}
			}
			else //S'il n'y a pas de Bailli, le joueur paye seulement le prix du quartier.
			{
				try {
					subOr(q.getPrix());
				} catch (PasAssezDOrException e) {
					e.printStackTrace();
				}
			}
			main.remove(q); //On enlève le quartier de la main du joueur...
			quartierConstruit.add(q); //...pour le rajouter dans la liste des quartiers qu'il a construits.
		}
		else //Si le quartier n'est pas dans la main du joueur, on lève une exception
		{
			throw new QuartierPasDansLaMainException();
		}
	}
	
	/**
	 * Méthode permettant de récupérer la liste des quartiers construits par le joueur
	 * @return la liste des quartiers construits
	 */
	public ArrayList<Quartier> getQuartierConstruit() {
		return quartierConstruit;
	}
	
	/**
	 * Méthode permettant de connaître le nombre de quartiers que le joueur a le droit de construire à son prochain tour.
	 * @return le nombre de quartiers que le joueur peut construire
	 */
	public int getDroitConstruction() {
		return droitConstruction;
	}
	
	/**
	 * Méthode permettant de donner au joueur le droit de construire plus de quartiers.
	 * @return le nombre de quartiers supplémentaires que le joueur peut construire
	 */
	public void addDroitConstruction(int supDroit) {
		droitConstruction += supDroit;
	}
	
	/**
	 * Méthode permettant de savoir si le joueur a construit 8 quartiers (ou plus)
	 * @return true si le joueur a construit 8 quartiers ou plus
	 */
	public boolean huitQuartier() {
		return (quartierConstruit.size() >= 8);
	}
	
	/**
	 * Méthode permettant de calculer le score total d'un joueur, en fonction des quartiers construits et des éventuels bonus
	 * @return le score du joueur
	 */
	public int calculerPoints() {
		int points = 0;
		ArrayList<Famille> listeFamilleQuartier = new ArrayList<Famille>(); //On crée une liste vide de Familles
		for(Quartier q : quartierConstruit) //On parcourt la liste des quartiers construits par le joueur
		{
			if(!listeFamilleQuartier.contains(q.getFamille())) //Si la famille d'un quartier n'est pas déjà dans la liste, on l'y ajoute 
			{
				listeFamilleQuartier.add(q.getFamille());
			}
			points += q.calculPoints(); //On ajoute la valeur de chaque quartier au total de points
		}
		if(listeFamilleQuartier.size() == Famille.getNbFamille()) //Si la liste de familles contient toutes les familles du jeu, on donne au joueur un bonus de 3 points
		{
			points += 3;
		}
		if(huitQuartier()) //Si le joueur a construit 8 quartiers ou plus, il reçoit un bonus de 2 points
		{
			points += 2;
		}
		if(partie.isGagnant(this)) //Si le joueur a été le premier à construire suffisamment de quartiers, il reçoit encore 2 points de bonus.
		{
			points += 2;
		}
		return points;
	}
	
	
	/**
	 * Méthode permettant de connaître le nombre de quartiers d'une famille spécifiée construits
	 * @param f la famille dont on veut connaître le nombre de quartiers construits
	 * @return le nombre de quartiers construits correspondant à la famille
	 */
	private int getNbQuartierConstruit(Famille f) {
		int nbQuartier = 0;
		for(Quartier q : quartierConstruit)
		{
			if(q.getFamille().equals(f)) //Si la famille du quartier est celle passée en paramètre, on incrémente le compteur
			{
				nbQuartier++;
			}
		}
		return nbQuartier;
	}
	
	/**
	 * Méthode permettant de connaître le nombre de quartiers religieux construits
	 * @return le nombre de quartiers religieux construits
	 */
	public int getNbQuartierReligion() {
		return getNbQuartierConstruit(Partie.religion);
	}
	
	/**
	 * Méthode permettant de connaître le nombre de quartiers nobles construits
	 * @return le nombre de quartiers nobles construits
	 */
	public int getNbQuartierNoblesse() {
		return getNbQuartierConstruit(Partie.noblesse);
	}
	
	/**
	 * Méthode permettant de connaître le nombre de quartiers commerciaux construits
	 * @return le nombre de quartiers commerciaux construits
	 */
	public int getNbQuartierCommerce() {
		return getNbQuartierConstruit(Partie.commerce);
	}
	
	/**
	 * Méthode permettant de connaître le nombre de quartiers militaires construits
	 * @return le nombre de quartiers militaires construits
	 */
	public int getNbQuartierMilitaire() {
		return getNbQuartierConstruit(Partie.militaire);
	}
	
	/**
	 * Méthode permettant de connaître le nombre de merveilles construites
	 * @return le nombre de quartiers merveilles construites
	 */
	public int getNbQuartierMerveille() {
		return getNbQuartierConstruit(Partie.merveille);
	}
	
	/**
	 * La méthode equals(), permettant de vérifier si deux joueurs sont identiques
	 * @return true si deux joueurs ont le même nom
	 */
	public boolean equals(Joueur j) {
		return (this.nom == j.nom);
	}
	
	public String toString() {
		String retour = nom+" :\nOr : "+or+"\nMain : ";
		for(Quartier q : main)
		{
			retour += q.toString()+" ";
		}
		retour += "\nQuartier Construit : ";
		for(Quartier q : quartierConstruit)
		{
			retour += q.toString()+" ";
		}
		return retour;
	}
	
	/**
	 * Méthode permettant de trier la liste des quartiers construits par leur famille
	 */
	public void trierQuartierConstruitFamille() {
		Collections.sort(quartierConstruit, new ComparatorQuartierFamille());
	}
	
	/**
	 * Méthode permettant de trier la liste des quartiers construits par leur coût
	 */
	public void trierQuartierConstruitCout() {
		Collections.sort(quartierConstruit, new ComparatorQuartierCout());
	}
}
