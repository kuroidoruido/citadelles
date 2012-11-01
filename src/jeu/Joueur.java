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
 * Classe mod�lisant les diff�rents joueurs d'une partie
 * @author Bauchet Cl�ment
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
	 * M�thode permettant de r�cup�rer le nom du joueur
	 * @return le nom du joueur
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * M�thode permettant de r�cup�rer la main du joueur
	 * @return la liste des quartiers que le joueur a en main
	 */
	public TreeSet<Quartier> getMain() {
		return main;
	}
	
	/**
	 * M�thode permettant d'ajouter une carte dans la main du joueur
	 * @param le quartier � ajouter
	 */
	public void addCarteMain(Quartier q) {
		main.add(q);
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
	 * M�thode permettant au joueur de gagner de l'or en fonction des quartiers qu'il a construit
	 * Chaque quartier de la m�me famille que le personnage du joueur lui fait gagner 1 pi�ce d'or
	 */
	public void addOrQuartier() {
		Famille famillePerso = perso.getFamille(); //On regarde la famille du personnage jou� par le joueur
		if(famillePerso != null)
		{
			for(Quartier q : quartierConstruit) //On parcourt la liste des quartiers construits par le joueur
			{
				if(q.getFamille() == famillePerso) //Si un quartier appartient � la m�me famille que le personnage, le joueur gagne 1 pi�ce d'or
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
			throw new PasAssezDOrException();
		}
	}
	
	
	/**
	 * M�thode permettant au joueur de construire un nouveau quartier.
	 * @param le quartier � construire
	 */
	public void construireQuartier(Quartier q) throws QuartierPasDansLaMainException, QuartierDejaConstruiteException {
		if(main.contains(q)) //On v�rifie d'abord que le quartier est bien dans la main du joueur
		{
			if(quartierConstruit.contains(q)) //On v�rifie si le quartier n'a pas d�j� �t� construit
			{
				throw new QuartierDejaConstruiteException(); //Si c'est le cas, on l�ve une exception
			}
			Joueur bailli = partie.isThereBailli(); //On v�rifie la pr�sence d'un Bailli parmi les personnages
			if(bailli != null) //S'il y a un Bailli, le joueur paye le prix du quartier, plus une pi�ce d'or pour le Bailli.
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
			main.remove(q); //On enl�ve le quartier de la main du joueur...
			quartierConstruit.add(q); //...pour le rajouter dans la liste des quartiers qu'il a construits.
		}
		else //Si le quartier n'est pas dans la main du joueur, on l�ve une exception
		{
			throw new QuartierPasDansLaMainException();
		}
	}
	
	/**
	 * M�thode permettant de r�cup�rer la liste des quartiers construits par le joueur
	 * @return la liste des quartiers construits
	 */
	public ArrayList<Quartier> getQuartierConstruit() {
		return quartierConstruit;
	}
	
	/**
	 * M�thode permettant de conna�tre le nombre de quartiers que le joueur a le droit de construire � son prochain tour.
	 * @return le nombre de quartiers que le joueur peut construire
	 */
	public int getDroitConstruction() {
		return droitConstruction;
	}
	
	/**
	 * M�thode permettant de donner au joueur le droit de construire plus de quartiers.
	 * @return le nombre de quartiers suppl�mentaires que le joueur peut construire
	 */
	public void addDroitConstruction(int supDroit) {
		droitConstruction += supDroit;
	}
	
	/**
	 * M�thode permettant de savoir si le joueur a construit 8 quartiers (ou plus)
	 * @return true si le joueur a construit 8 quartiers ou plus
	 */
	public boolean huitQuartier() {
		return (quartierConstruit.size() >= 8);
	}
	
	/**
	 * M�thode permettant de calculer le score total d'un joueur, en fonction des quartiers construits et des �ventuels bonus
	 * @return le score du joueur
	 */
	public int calculerPoints() {
		int points = 0;
		ArrayList<Famille> listeFamilleQuartier = new ArrayList<Famille>(); //On cr�e une liste vide de Familles
		for(Quartier q : quartierConstruit) //On parcourt la liste des quartiers construits par le joueur
		{
			if(!listeFamilleQuartier.contains(q.getFamille())) //Si la famille d'un quartier n'est pas d�j� dans la liste, on l'y ajoute 
			{
				listeFamilleQuartier.add(q.getFamille());
			}
			points += q.calculPoints(); //On ajoute la valeur de chaque quartier au total de points
		}
		if(listeFamilleQuartier.size() == Famille.getNbFamille()) //Si la liste de familles contient toutes les familles du jeu, on donne au joueur un bonus de 3 points
		{
			points += 3;
		}
		if(huitQuartier()) //Si le joueur a construit 8 quartiers ou plus, il re�oit un bonus de 2 points
		{
			points += 2;
		}
		if(partie.isGagnant(this)) //Si le joueur a �t� le premier � construire suffisamment de quartiers, il re�oit encore 2 points de bonus.
		{
			points += 2;
		}
		return points;
	}
	
	
	/**
	 * M�thode permettant de conna�tre le nombre de quartiers d'une famille sp�cifi�e construits
	 * @param f la famille dont on veut conna�tre le nombre de quartiers construits
	 * @return le nombre de quartiers construits correspondant � la famille
	 */
	private int getNbQuartierConstruit(Famille f) {
		int nbQuartier = 0;
		for(Quartier q : quartierConstruit)
		{
			if(q.getFamille().equals(f)) //Si la famille du quartier est celle pass�e en param�tre, on incr�mente le compteur
			{
				nbQuartier++;
			}
		}
		return nbQuartier;
	}
	
	/**
	 * M�thode permettant de conna�tre le nombre de quartiers religieux construits
	 * @return le nombre de quartiers religieux construits
	 */
	public int getNbQuartierReligion() {
		return getNbQuartierConstruit(Partie.religion);
	}
	
	/**
	 * M�thode permettant de conna�tre le nombre de quartiers nobles construits
	 * @return le nombre de quartiers nobles construits
	 */
	public int getNbQuartierNoblesse() {
		return getNbQuartierConstruit(Partie.noblesse);
	}
	
	/**
	 * M�thode permettant de conna�tre le nombre de quartiers commerciaux construits
	 * @return le nombre de quartiers commerciaux construits
	 */
	public int getNbQuartierCommerce() {
		return getNbQuartierConstruit(Partie.commerce);
	}
	
	/**
	 * M�thode permettant de conna�tre le nombre de quartiers militaires construits
	 * @return le nombre de quartiers militaires construits
	 */
	public int getNbQuartierMilitaire() {
		return getNbQuartierConstruit(Partie.militaire);
	}
	
	/**
	 * M�thode permettant de conna�tre le nombre de merveilles construites
	 * @return le nombre de quartiers merveilles construites
	 */
	public int getNbQuartierMerveille() {
		return getNbQuartierConstruit(Partie.merveille);
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
	 * M�thode permettant de trier la liste des quartiers construits par leur famille
	 */
	public void trierQuartierConstruitFamille() {
		Collections.sort(quartierConstruit, new ComparatorQuartierFamille());
	}
	
	/**
	 * M�thode permettant de trier la liste des quartiers construits par leur co�t
	 */
	public void trierQuartierConstruitCout() {
		Collections.sort(quartierConstruit, new ComparatorQuartierCout());
	}
}
