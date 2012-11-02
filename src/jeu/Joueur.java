package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

import carte.Famille;
import carte.batiment.Batiment;
import carte.batiment.ComparatorBatimentCout;
import carte.batiment.ComparatorBatimentFamille;
import carte.personnage.Personnage;

/**
 * Classe modélisant les différents joueurs d'une partie
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 24 oct. 2012
 *
 */
public class Joueur {
	
	private String nom;
	private TreeSet<Batiment> main;
	private Personnage perso;
	private int or;
	private ArrayList<Batiment> batimentConstruit;
	private int droitConstruction;
	
	private Partie partie;
	
	/**
	 * Constructeur de Joueur.
	 * @param nom le nom du joueur
	 */
	public Joueur(String nom, Partie partie) {
		super();
		this.nom = nom;
		this.main = new TreeSet<Batiment>();
		this.perso = null;
		this.or = 2;
		this.batimentConstruit = new ArrayList<Batiment>();
		this.droitConstruction = 0;
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
	 * @return la liste des bâtiments que le joueur a en main
	 */
	public TreeSet<Batiment> getMain() {
		return main;
	}
	
	/**
	 * Méthode permettant d'ajouter une carte dans la main du joueur
	 * @param le bâtiment à ajouter
	 */
	public void addCarteMain(Batiment b) {
		main.add(b);
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
	 * Méthode permettant au joueur de gagner de l'or en fonction des bâtiments qu'il a construits
	 * Chaque bâtiment de la même famille que le personnage du joueur lui fait gagner 1 pièce d'or
	 */
	public void addOrBatiment() {
		Famille famillePerso = perso.getFamille(); //On regarde la famille du personnage joué par le joueur
		if(famillePerso != null)
		{
			for(Batiment b : batimentConstruit) //On parcourt la liste des bâtiments construits par le joueur
			{
				if(b.getFamille() == famillePerso)  //Si un bâtiment appartient à la même famille que le personnage, le joueur gagne 1 pièce d'or
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
			throw new PasAssezDOrException((new Integer(nbPieces)).toString());
		}
	}
	
	/**
	 * Méthode permettant au joueur de construire un nouveau bâtiment.
	 * @param le bâtiment à construire
	 */
	public void construireBatiment(Batiment b) throws BatimentPasDansLaMainException, BatimentDejaConstruiteException, PasAssezDOrException {
		if(main.contains(b)) //On vérifie d'abord que le quartier est bien dans la main du joueur
		{
			if(batimentConstruit.contains(b)) //On vérifie si le quartier n'a pas déjà été construit
			{
				throw new BatimentDejaConstruiteException(); //Si c'est le cas, on lève une exception
			}
			Joueur bailli = partie.chercher("Bailli"); //On vérifie la présence d'un Bailli parmi les personnages
			if(this.perso.getOrdre() >= 2 && bailli != null) //S'il y a un Bailli, le joueur paye le prix du bâtiment, plus une pièce d'or pour le Bailli.
			{
					subOr(b.getPrix()+1);
					bailli.addOr(1);
			}
			else //S'il n'y a pas de Bailli, le joueur paye seulement le prix du bâtiment.
			{
					subOr(b.getPrix());
			}
			droitConstruction--;
			main.remove(b); //On enlève le quartier de la main du joueur...
			batimentConstruit.add(b); //...pour le rajouter dans la liste des quartiers qu'il a construits.
		}
		else //Si le quartier n'est pas dans la main du joueur, on lève une exception
		{
			throw new BatimentPasDansLaMainException();
		}
	}
	
	/**
	 * Méthode permettant de récupérer la liste des bâtiments construits par le joueur
	 * @return la liste des bâtiments construits
	 */
	public ArrayList<Batiment> getBatimentConstruit() {
		return batimentConstruit;
	}
	
	/**
	 * Méthode permettant de connaître le nombre de bâtiments que le joueur a le droit de construire à son prochain tour.
	 * @return le nombre de bâtiments que le joueur peut construire
	 */
	public int getDroitConstruction() {
		return droitConstruction;
	}
	
	/**
	 * Méthode permettant de définir le nombre de bâtiments que le joueur peut construire par tour.
	 * @param le nombre de bâtiments que le joueur peut construire
	 */
	public void setDroitConstruction(int nbDroit) {
		droitConstruction = nbDroit;
	}
	
	/**
	 * Méthode permettant de donner au joueur le droit de construire plus de bâtiments.
	 * @return le nombre de bâtiments supplémentaires que le joueur peut construire
	 */
	public void addDroitConstruction(int supDroit) {
		droitConstruction += supDroit;
	}
	
	/**
	 * Méthode permettant de savoir si le joueur a construit 8 bâtiments (ou plus)
	 * @return true si le joueur a construit 8 bâtiments ou plus
	 */
	public boolean huitBatiments() {
		return (batimentConstruit.size() >= 8);
	}
	
	/**
	 * Méthode permettant de calculer le score total d'un joueur, en fonction des bâtiments construits et des éventuels bonus
	 * @return le score du joueur
	 */
	public int calculerPoints() {
		int points = 0;
		ArrayList<Famille> listeFamilleBatiment = new ArrayList<Famille>(); //On crée une liste vide de Familles
		for(Batiment b : batimentConstruit) //On parcourt la liste des bâtiments construits par le joueur
		{
			if(!listeFamilleBatiment.contains(b.getFamille())) //Si la famille d'un bâtiment n'est pas déjà dans la liste, on l'y ajoute 
			{
				listeFamilleBatiment.add(b.getFamille());
			}
			points += b.calculPoints(); //On ajoute la valeur de chaque bâtiment au total de points
		}
		if(listeFamilleBatiment.size() == Famille.getNbFamille()) //Si la liste de familles contient toutes les familles du jeu, on donne au joueur un bonus de 3 points
		{
			points += 3;
		}
		if(huitBatiments()) //Si le joueur a construit 8 bâtiments ou plus, il reçoit un bonus de 2 points
		{
			points += 2;
		}
		if(partie.isGagnant(this)) //Si le joueur a été le premier à construire suffisamment de bâtiments, il reçoit encore 2 points de bonus.
		{
			points += 2;
		}
		return points;
	}
	
	/**
	 * Méthode permettant de connaître le nombre de bâtiments d'une famille spécifiée construits
	 * @param f la famille dont on veut connaître le nombre de bâtiments construits
	 * @return le nombre de bâtiments construits correspondant à la famille
	 */
	private int getNbBatimentConstruit(Famille f) {
		int nbBatiments = 0;
		for(Batiment b : batimentConstruit)
		{
			if(b.getFamille().equals(f)) //Si la famille du bâtiment est celle passée en paramètre, on incrémente le compteur
			{
				nbBatiments++;
			}
		}
		return nbBatiments;
	}
	
	/**
	 * Méthode permettant de connaître le nombre de bâtiments religieux construits
	 * @return le nombre de bâtiments religieux construits
	 */
	public int getNbBatimentReligion() {
		return getNbBatimentConstruit(Partie.religion);
	}
	
	/**
	 * Méthode permettant de connaître le nombre de bâtiments nobles construits
	 * @return le nombre de bâtiments nobles construits
	 */
	public int getNbBatimentNoblesse() {
		return getNbBatimentConstruit(Partie.noblesse);
	}
	
	/**
	 * Méthode permettant de connaître le nombre de bâtiments commerciaux construits
	 * @return le nombre de bâtiments commerciaux construits
	 */
	public int getNbBatimentCommerce() {
		return getNbBatimentConstruit(Partie.commerce);
	}
	
	/**
	 * Méthode permettant de connaître le nombre de bâtiments militaires construits
	 * @return le nombre de bâtiments militaires construits
	 */
	public int getNbBatimentMilitaire() {
		return getNbBatimentConstruit(Partie.militaire);
	}
	
	/**
	 * Méthode permettant de connaître le nombre de merveilles construites
	 * @return le nombre de quartiers merveilles construites
	 */
	public int getNbBatimentMerveille() {
		return getNbBatimentConstruit(Partie.merveille);
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
		for(Batiment b : main)
		{
			retour += b.toString()+" ";
		}
		retour += "\nQuartier Construit : ";
		for(Batiment b : batimentConstruit)
		{
			retour += b.toString()+" ";
		}
		return retour;
	}
	
	/**
	 * Méthode permettant de trier la liste des bâtiments construits par leur famille
	 */
	public void trierBatimentConstruitFamille() {
		Collections.sort(batimentConstruit, new ComparatorBatimentFamille());
	}
	
	/**
	 * Méthode permettant de trier la liste des bâtiments construits par leur coût
	 */
	public void trierBatimentConstruitCout() {
		Collections.sort(batimentConstruit, new ComparatorBatimentCout());
	}
}
