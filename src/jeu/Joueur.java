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
 * Classe mod�lisant les diff�rents joueurs d'une partie
 * @author Bauchet Cl�ment
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
	 * M�thode permettant de r�cup�rer le nom du joueur
	 * @return le nom du joueur
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * M�thode permettant de r�cup�rer la main du joueur
	 * @return la liste des b�timents que le joueur a en main
	 */
	public TreeSet<Batiment> getMain() {
		return main;
	}
	
	/**
	 * M�thode permettant d'ajouter une carte dans la main du joueur
	 * @param le b�timent � ajouter
	 */
	public void addCarteMain(Batiment b) {
		main.add(b);
	}
	
	/**
	 * M�thode permettant de conna�tre le personnage jou� par le joueur.
	 * @return le personnage attribu� au joueur
	 */
	public Personnage getPerso() {
		return perso;
	}
	
	/**
	 * M�thode permettant d'attribuer un personnage au joueur.
	 * @param p le personnage � attribuer au joueur
	 */
	public void setPerso(Personnage p) {
		perso = p;
	}
	
	/**
	 * M�thode permettant de r�cup�rer la quantit� d'or poss�d�e par le joueur
	 * @return le nombre de pi�ces d'or disponible
	 */
	public int getOr() {
		return or;
	}
	
	/**
	 * M�thode permettant de donner de l'or au joueur
	 * @param le nombre de pi�ces d'or � donner
	 */
	public void addOr(int nbPieces) {
		or += nbPieces;
	}
	
	/**
	 * M�thode permettant au joueur de gagner de l'or en fonction des b�timents qu'il a construits
	 * Chaque b�timent de la m�me famille que le personnage du joueur lui fait gagner 1 pi�ce d'or
	 */
	public void addOrBatiment() {
		Famille famillePerso = perso.getFamille(); //On regarde la famille du personnage jou� par le joueur
		if(famillePerso != null)
		{
			for(Batiment b : batimentConstruit) //On parcourt la liste des b�timents construits par le joueur
			{
				if(b.getFamille() == famillePerso)  //Si un b�timent appartient � la m�me famille que le personnage, le joueur gagne 1 pi�ce d'or
				{
					or += 1;
				}
			}
		}
	}
		
	/**
	 * M�thode permettant d'enlever de l'or au joueur, par exemple lorsqu'il construit un quartier, paye un autre joueur ou se fait voler...
	 * @param le nombre de pi�ces d'or � retirer
	 */
	public void subOr(int nbPieces) throws PasAssezDOrException {
		if(nbPieces <= or) //Si le joueur a assez d'or, on lui en enl�ve.
		{
			or -= nbPieces;
		}
		else //Si le joueur n'a pas assez d'or, on l�ve une exception.
		{
			throw new PasAssezDOrException((new Integer(nbPieces)).toString());
		}
	}
	
	/**
	 * M�thode permettant au joueur de construire un nouveau b�timent.
	 * @param le b�timent � construire
	 */
	public void construireBatiment(Batiment b) throws BatimentPasDansLaMainException, BatimentDejaConstruiteException, PasAssezDOrException {
		if(main.contains(b)) //On v�rifie d'abord que le quartier est bien dans la main du joueur
		{
			if(batimentConstruit.contains(b)) //On v�rifie si le quartier n'a pas d�j� �t� construit
			{
				throw new BatimentDejaConstruiteException(); //Si c'est le cas, on l�ve une exception
			}
			Joueur bailli = partie.chercher("Bailli"); //On v�rifie la pr�sence d'un Bailli parmi les personnages
			if(this.perso.getOrdre() >= 2 && bailli != null) //S'il y a un Bailli, le joueur paye le prix du b�timent, plus une pi�ce d'or pour le Bailli.
			{
					subOr(b.getPrix()+1);
					bailli.addOr(1);
			}
			else //S'il n'y a pas de Bailli, le joueur paye seulement le prix du b�timent.
			{
					subOr(b.getPrix());
			}
			droitConstruction--;
			main.remove(b); //On enl�ve le quartier de la main du joueur...
			batimentConstruit.add(b); //...pour le rajouter dans la liste des quartiers qu'il a construits.
		}
		else //Si le quartier n'est pas dans la main du joueur, on l�ve une exception
		{
			throw new BatimentPasDansLaMainException();
		}
	}
	
	/**
	 * M�thode permettant de r�cup�rer la liste des b�timents construits par le joueur
	 * @return la liste des b�timents construits
	 */
	public ArrayList<Batiment> getBatimentConstruit() {
		return batimentConstruit;
	}
	
	/**
	 * M�thode permettant de conna�tre le nombre de b�timents que le joueur a le droit de construire � son prochain tour.
	 * @return le nombre de b�timents que le joueur peut construire
	 */
	public int getDroitConstruction() {
		return droitConstruction;
	}
	
	/**
	 * M�thode permettant de d�finir le nombre de b�timents que le joueur peut construire par tour.
	 * @param le nombre de b�timents que le joueur peut construire
	 */
	public void setDroitConstruction(int nbDroit) {
		droitConstruction = nbDroit;
	}
	
	/**
	 * M�thode permettant de donner au joueur le droit de construire plus de b�timents.
	 * @return le nombre de b�timents suppl�mentaires que le joueur peut construire
	 */
	public void addDroitConstruction(int supDroit) {
		droitConstruction += supDroit;
	}
	
	/**
	 * M�thode permettant de savoir si le joueur a construit 8 b�timents (ou plus)
	 * @return true si le joueur a construit 8 b�timents ou plus
	 */
	public boolean huitBatiments() {
		return (batimentConstruit.size() >= 8);
	}
	
	/**
	 * M�thode permettant de calculer le score total d'un joueur, en fonction des b�timents construits et des �ventuels bonus
	 * @return le score du joueur
	 */
	public int calculerPoints() {
		int points = 0;
		ArrayList<Famille> listeFamilleBatiment = new ArrayList<Famille>(); //On cr�e une liste vide de Familles
		for(Batiment b : batimentConstruit) //On parcourt la liste des b�timents construits par le joueur
		{
			if(!listeFamilleBatiment.contains(b.getFamille())) //Si la famille d'un b�timent n'est pas d�j� dans la liste, on l'y ajoute 
			{
				listeFamilleBatiment.add(b.getFamille());
			}
			points += b.calculPoints(); //On ajoute la valeur de chaque b�timent au total de points
		}
		if(listeFamilleBatiment.size() == Famille.getNbFamille()) //Si la liste de familles contient toutes les familles du jeu, on donne au joueur un bonus de 3 points
		{
			points += 3;
		}
		if(huitBatiments()) //Si le joueur a construit 8 b�timents ou plus, il re�oit un bonus de 2 points
		{
			points += 2;
		}
		if(partie.isGagnant(this)) //Si le joueur a �t� le premier � construire suffisamment de b�timents, il re�oit encore 2 points de bonus.
		{
			points += 2;
		}
		return points;
	}
	
	/**
	 * M�thode permettant de conna�tre le nombre de b�timents d'une famille sp�cifi�e construits
	 * @param f la famille dont on veut conna�tre le nombre de b�timents construits
	 * @return le nombre de b�timents construits correspondant � la famille
	 */
	private int getNbBatimentConstruit(Famille f) {
		int nbBatiments = 0;
		for(Batiment b : batimentConstruit)
		{
			if(b.getFamille().equals(f)) //Si la famille du b�timent est celle pass�e en param�tre, on incr�mente le compteur
			{
				nbBatiments++;
			}
		}
		return nbBatiments;
	}
	
	/**
	 * M�thode permettant de conna�tre le nombre de b�timents religieux construits
	 * @return le nombre de b�timents religieux construits
	 */
	public int getNbBatimentReligion() {
		return getNbBatimentConstruit(Partie.religion);
	}
	
	/**
	 * M�thode permettant de conna�tre le nombre de b�timents nobles construits
	 * @return le nombre de b�timents nobles construits
	 */
	public int getNbBatimentNoblesse() {
		return getNbBatimentConstruit(Partie.noblesse);
	}
	
	/**
	 * M�thode permettant de conna�tre le nombre de b�timents commerciaux construits
	 * @return le nombre de b�timents commerciaux construits
	 */
	public int getNbBatimentCommerce() {
		return getNbBatimentConstruit(Partie.commerce);
	}
	
	/**
	 * M�thode permettant de conna�tre le nombre de b�timents militaires construits
	 * @return le nombre de b�timents militaires construits
	 */
	public int getNbBatimentMilitaire() {
		return getNbBatimentConstruit(Partie.militaire);
	}
	
	/**
	 * M�thode permettant de conna�tre le nombre de merveilles construites
	 * @return le nombre de quartiers merveilles construites
	 */
	public int getNbBatimentMerveille() {
		return getNbBatimentConstruit(Partie.merveille);
	}
	
	/**
	 * La m�thode equals(), permettant de v�rifier si deux joueurs sont identiques
	 * @return true si deux joueurs ont le m�me nom
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
	 * M�thode permettant de trier la liste des b�timents construits par leur famille
	 */
	public void trierBatimentConstruitFamille() {
		Collections.sort(batimentConstruit, new ComparatorBatimentFamille());
	}
	
	/**
	 * M�thode permettant de trier la liste des b�timents construits par leur co�t
	 */
	public void trierBatimentConstruitCout() {
		Collections.sort(batimentConstruit, new ComparatorBatimentCout());
	}
}
