package jeu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import carte.Famille;
import carte.batiment.Batiment;
import carte.batiment.Merveille;
import carte.batiment.Quartier;
import carte.personnage.*;

/**
 * Classe mod�lisant le d�roulement complet d'une partie de Citadelles
 * @author Bauchet Cl�ment
 * @author Pena Anthony
 * @version 1 nov. 2012
 *
 */
public class Partie implements Iterable<Joueur> {
	
	private int nombreDeTour;
	private LinkedList<Joueur> listeJoueur;
	private Joueur couronne;
	private Joueur gagnant;
	private LinkedList<Batiment> pileBatiment;
	private ArrayList<Personnage> pilePerso;
	private ArrayList<Personnage> listePersoJoue;
	
	// Cr�ation des familles de carte
	public static Famille religion = new Famille("Religion", Color.blue);
	public static Famille noblesse = new Famille("Noblesse", Color.yellow);
	public static Famille commerce = new Famille("Commerce", Color.green);
	public static Famille militaire = new Famille("Militaire", Color.red);
	public static Famille merveille = new Famille("Merveille", Color.magenta);
	
	/** 
	 * Constructeur de Partie, initialise la partie en instanciant les listes et en distribuant les cartes.
	 * @throws NombreDeJoueurIncorrectException 
	 * 
	 */
	public Partie(LinkedList<String> listeJoueur) throws NombreDeJoueurIncorrectException {
		super();
		if(3 <= listeJoueur.size() && listeJoueur.size() <= 8)
		{
			this.nombreDeTour = 1;
			this.listeJoueur = new LinkedList<Joueur>();
			for(String j : listeJoueur)
			{
				this.listeJoueur.add((new Joueur(j,this)));
			}
			this.pileBatiment = new LinkedList<Batiment>();
			this.pilePerso = new ArrayList<Personnage>();
			this.listePersoJoue = new ArrayList<Personnage>();
			initialiser();
		}
		else
		{
			throw new NombreDeJoueurIncorrectException("Erreur sur le nombre de joueurs ! Le nombre de joueurs doit �tre compris entre 3 et 8.");
		}
	}

	/**
	 * M�thode permettand de conna�tre le nombre de tours pass�s depuis le d�but de la partie
	 * @return le nombre de tours �coul�s
	 */
	public int getNombreDeTour() {
		return nombreDeTour;
	}
	
	/**
	 * M�thode permettant de r�cup�rer la liste des joueurs.
	 * @return la listeJoueur
	 */
	public LinkedList<Joueur> getListeJoueur() {
		return listeJoueur;
	}
	
	/**
	 * M�thode permettant de r�cup�rer le nombre de joueurs de la partie.
	 * @return le nombre de joueurs dans la partie
	 */
	public int getNbJoueur() {
		return listeJoueur.size();
	}
	
	/**
	 * M�thode permettant d'incr�menter le compteur de tours
	 */
	public void tourSuivant() {
		nombreDeTour++;
	}
	
	/**
	 * M�thode permettant de rechercher le joueur jouant le personnage sp�cifi�
	 * @param p le personnage dont on veut conna�tre le joueur associ�
	 * @return le joueur jouant le personnage recherch� si ce dernier est trouv�, null sinon
	 */
	public Joueur chercher(Personnage p) {
		Joueur tmp = null;
		for(Joueur j : listeJoueur) {
			if (j.getPerso() == p) {
				tmp = j;
			}
		}
		return tmp;
	}
	
	/**
	 * M�thode permettant de rechercher le joueur jouant le personnage sp�cifi� par son nom.
	 * @param p le nom du personnage dont on veut conna�tre le joueur associ�
	 * @return le joueur jouant le personnage recherch� si ce dernier est trouv�, null sinon
	 */
	public Joueur chercher(String p) {
		Joueur tmp = null;
		for(Joueur j : listeJoueur) {
			if (j.getPerso().getNom() == p) {
				tmp = j;
			}
		}
		return tmp;
	}
	
	/**
	 * M�thode permettant de conna�tre le joueur poss�dant la couronne
	 * @return le joueur qui d�tient la couronne
	 */
	public Joueur getCouronne() {
		return couronne;
	}

	/**
	 * M�thode permettant de donner la couronne � un joueur
	 * @param couronne le joueur qui obtient la couronne
	 */
	public void setCouronne(Joueur couronne) {
		this.couronne = couronne;
	}
	
	/**
	 * M�thode permettant de r�cup�rer la pile de personnages
	 * @return la pile de personnages
	 */
	public ArrayList<Personnage> getPilePerso() {
		return pilePerso;
	}

	/**
	 * M�thode permettant de piocher des cartes b�timent
	 * @param nbb�timent le nombre de b�timents � piocher
	 * @return les nbBatiment premiers b�timents de la pile de b�timent
	 */
	public void piocherBatiment(Joueur j, int nbBatiment) throws PlusDeBatimentDansLaPileException {
		if(!pileBatiment.isEmpty()) //On peut piocher tant qu'il reste des cartes dans la pile
		{
			for(int i=0; i<nbBatiment; i++)
			{
				j.addCarteMain(pileBatiment.removeFirst());
			}
		}
		else //Si la pile est vide, on l�ve une exception
		{
			throw new PlusDeBatimentDansLaPileException();
		}
	}
	
	/**
	 * M�thode permettant de savoir si un des joueurs a construit 8 b�timents ou plus, ce qui permettra de mettre fin � la partie.
	 * @return true si au moins un des joueurs � construit 8 b�timents
	 */
	public boolean huitBatiments() {
		boolean retour = false;
		for(Joueur j : listeJoueur)
		{
			retour = (retour || j.huitBatiments());
		}
		return retour;
	}
	
	/** 
	 * M�thode permettant d'initialiser une partie, en cr�ant tous les quartiers et personnages utilisables par les joueurs.
	 */
	private void initialiser() {
		
		// Cr�ation et ajout � la pile de chacune des cartes Quartier et Merveille
		pileBatiment.clear();
		pileBatiment.add(new Quartier("Temple",religion,1));
		pileBatiment.add(new Quartier("Temple",religion,1));
		pileBatiment.add(new Quartier("Temple",religion,1));
		pileBatiment.add(new Quartier("Eglise",religion,2));
		pileBatiment.add(new Quartier("Eglise",religion,2));
		pileBatiment.add(new Quartier("Eglise",religion,2));
		pileBatiment.add(new Quartier("Eglise",religion,2));
		pileBatiment.add(new Quartier("Monast�re",religion,3));
		pileBatiment.add(new Quartier("Monast�re",religion,3));
		pileBatiment.add(new Quartier("Monast�re",religion,3));
		pileBatiment.add(new Quartier("Cath�drale",religion,5));
		pileBatiment.add(new Quartier("Cath�drale",religion,5));

		pileBatiment.add(new Quartier("Manoir",noblesse,3));
		pileBatiment.add(new Quartier("Manoir",noblesse,3));
		pileBatiment.add(new Quartier("Manoir",noblesse,3));
		pileBatiment.add(new Quartier("Manoir",noblesse,3));
		pileBatiment.add(new Quartier("Manoir",noblesse,3));
		pileBatiment.add(new Quartier("Ch�teau",noblesse,4));
		pileBatiment.add(new Quartier("Ch�teau",noblesse,4));
		pileBatiment.add(new Quartier("Ch�teau",noblesse,4));
		pileBatiment.add(new Quartier("Ch�teau",noblesse,4));
		pileBatiment.add(new Quartier("Palais",noblesse,5));
		pileBatiment.add(new Quartier("Palais",noblesse,5));

		pileBatiment.add(new Quartier("Taverne",commerce,1));
		pileBatiment.add(new Quartier("Taverne",commerce,1));
		pileBatiment.add(new Quartier("Taverne",commerce,1));
		pileBatiment.add(new Quartier("Taverne",commerce,1));
		pileBatiment.add(new Quartier("Taverne",commerce,1));
		pileBatiment.add(new Quartier("ɉchoppe",commerce,2));
		pileBatiment.add(new Quartier("ɉchoppe",commerce,2));
		pileBatiment.add(new Quartier("ɉchoppe",commerce,2));
		pileBatiment.add(new Quartier("March�",commerce,2));
		pileBatiment.add(new Quartier("March�",commerce,2));
		pileBatiment.add(new Quartier("March�",commerce,2));
		pileBatiment.add(new Quartier("March�",commerce,2));
		pileBatiment.add(new Quartier("Comptoir",commerce,3));
		pileBatiment.add(new Quartier("Comptoir",commerce,3));
		pileBatiment.add(new Quartier("Comptoir",commerce,3));
		pileBatiment.add(new Quartier("Port",commerce,4));
		pileBatiment.add(new Quartier("Port",commerce,4));
		pileBatiment.add(new Quartier("Port",commerce,4));
		pileBatiment.add(new Quartier("H�tel de ville",commerce,5));
		pileBatiment.add(new Quartier("H�tel de ville",commerce,5));

		pileBatiment.add(new Quartier("Tour de guet",militaire,1));
		pileBatiment.add(new Quartier("Tour de guet",militaire,1));
		pileBatiment.add(new Quartier("Tour de guet",militaire,1));
		pileBatiment.add(new Quartier("Prison",militaire,2));
		pileBatiment.add(new Quartier("Prison",militaire,2));
		pileBatiment.add(new Quartier("Prison",militaire,2));
		pileBatiment.add(new Quartier("Caserne",militaire,3));
		pileBatiment.add(new Quartier("Caserne",militaire,3));
		pileBatiment.add(new Quartier("Caserne",militaire,3));
		pileBatiment.add(new Quartier("Forteresse",militaire,5));
		pileBatiment.add(new Quartier("Forteresse",militaire,5));

		pileBatiment.add(new Merveille("Cour des miracles",merveille,2,"Pour le d�compte final des points, la cour des miracles est consid�r�e comme un quartier de la couleur de votre choix. Vous ne pouvez pas utilisez cette capacit� si vous avez construit la cour des miracles au dernier tour de jeu.",0));
		pileBatiment.add(new Merveille("Donjon",merveille,3,"Le Donjon ne peut pas �tre d�truit par le Condotti�re.",0));
		pileBatiment.add(new Merveille("Laboratoire",merveille,5,"Une fois par tour, vous pouvez vous d�fausser d'une carte quartier de votre main et recevoir une pi�ce d'or en contrepartie",0));
		pileBatiment.add(new Merveille("Manufacture",merveille,5,"Une fois par tour, vous pouvez payer trois pi�ces d'or pour piocher trois cartes.",0));
		pileBatiment.add(new Merveille("Observatoire",merveille,3,"Si vous choisissez de piocher des cartes au d�but de votre tour, vous en piochez trois, en choisissez une et d�faussez les deux autres.",0));
		pileBatiment.add(new Merveille("Cimeti�re",merveille,5,"Lorsque le Condotti�re d�truit un quartier, vous pouvez payer une pi�ce d'or pour le reprendre dans votre main. Vous ne pouvez pas faire cela si vous �tes vous-m�me Condottiere.",0));
		pileBatiment.add(new Merveille("Biblioth�que",merveille,6,"Si vous choisissez de piocher des cartes au d�but de votre tour, vous en piochez deux et les conservez toutes les deux.",0));
		pileBatiment.add(new Merveille("ɉcole de Magie",merveille,6,"Pour la perception des revenus, l'�cole de magie est consid�r�e comme un quartier de la couleur de votre choix, elle vous rapporte donc si vous �tes, Roi, Ev�que, Marchand ou Condottiere",0));
		pileBatiment.add(new Merveille("Universit�",merveille,6,"Cette r�alisation de prestige (nul n'a jamais compris � quoi pouvait bien servir une universit�) co�te six pi�ces d'or � b�tir mais vaut huit points dans le d�compte de fin de partie.",2));
		pileBatiment.add(new Merveille("Dracoport",merveille,6,"Cette r�alisation de prestige (on n'a pas vu de dragon dans le Royaume depuis bient�t mille ans) co�te six pi�ces d'or � b�tir mais vaut huit points dans le d�compte de fin de partie.",2));

		// M�lange al�atoire de la pile de Quartier
		Collections.shuffle((List<Batiment>)(pileBatiment));

		// Cr�ation et ajout des cartes Personnage � la liste des Personnages
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

		// On distribue � chaque joueur 4 cartes Quartier.
		for(Joueur j : listeJoueur)
		{
			j.addCarteMain(pileBatiment.removeFirst());
			j.addCarteMain(pileBatiment.removeFirst());
			j.addCarteMain(pileBatiment.removeFirst());
			j.addCarteMain(pileBatiment.removeFirst());
		}
		
		// On choisit al�atoirement le joueur qui portera la couronne au premier tour.
		setCouronne(listeJoueur.get((new Double(Math.random()*listeJoueur.size()).intValue())));
		couronneEnPremier();
	}
	
	/**
	 * @return un Iterator<Joueur> qui permet de parcourir les joueurs selon l'ordre de leurs Personnages.
	 */
	public Iterator<Joueur> iterator() {
		return new JoueurOrdrePersoIterator(this);
	}
	
	/** 
	 * Positionne le premier joueur au porteur de la couronne
	 * 
	 */
	public void couronneEnPremier() {
		while(listeJoueur.getFirst() != couronne)
		{
			listeJoueur.addLast(listeJoueur.removeFirst());
		}
	}
	
	/**
	 * M�thode permettant de d�finir quels personnages seront jou�s pendant le tour.
	 * @param perso la liste des personnages � jouer
	 */
	public void selectPerso(ArrayList<Personnage> perso) {
		listePersoJoue = perso;
	}
	
	/**
	 * M�thode permettant de r�cup�rer la liste des personnages jou�s du tour actuel.
	 * @return la liste des personnages jou�s du tour
	 */
	public ArrayList<Personnage> getListePersoJoue() {
		return listePersoJoue;
	}
	
	/**
	 * M�thode permettant de v�rifier si la s�lection des personnages est valide.
	 * @return true si le nombre de cartes est �gal au nombre de joueurs plus un, ET s'il n'y a pas plusieurs personnages de m�me ordre de passage, ET s'il y a un Roi ou un Empereur.
	 * @throws NombreDePersonnageSelectionneIncorrectException
	 * @throws PlusieursPersonnageDuMemeOrdreException
	 * @throws PasDeRoiOuDEmpereurException
	 */
	public boolean verifierPersoSelectionne() throws NombreDePersonnageSelectionneIncorrectException, PlusieursPersonnageDuMemeOrdreException, PasDeRoiOuDEmpereurException {
		boolean retour = true;
		
		// On v�rifie le nombre de carte.
		if((getNbJoueur() < 7 && listePersoJoue.size() != 8) || (getNbJoueur() == 8 && listePersoJoue.size() != 9) || (listePersoJoue.size() > 9))
		{
			throw new NombreDePersonnageSelectionneIncorrectException("nombre de joueur = "+getNbJoueur()+" nombre de Personnage s�lectionn�s = "+listePersoJoue.size());
		}
		
		// On v�rifie qu'un seul Personnage de chaque ordre soit dans les cartes s�lectionn�es
		// Sauf pour l'ordre 9 qui peut �tre � z�ro
		// ET
		//On v�rifie qu'il y a un Roi ou un Empereur
		Integer[] ordre = new Integer[9];
		boolean roi = false;
		for(int i=0;i<9;i++)
		{
			ordre[i]=0;
		}
		for(Personnage p : listePersoJoue)
		{
			ordre[p.getOrdre()-1]++;
			
			if(p.getNom() == "Roi" || p.getNom() == "Empereur") //On v�rifie s'il y a un Roi OU un Empereur
			{
				roi = true;
			}
		}
		int i =0;
		String ordreString = "";
		while(retour && i<9)
		{
			ordreString += ordre[i]+" ";
			if((i < 7 & ordre[i] != 1) || (i == 8 && ordre[i] > 1)) //On v�rifie que chaque personnage a un ordre unique
			{
				retour = false;
			}
			i++;
		}
		if(!retour)
		{
			throw new PlusieursPersonnageDuMemeOrdreException(ordreString);
		}

		if(!roi)
		{
			throw new PasDeRoiOuDEmpereurException();
		}
		
		return retour;
	}
	
	/**
	 * M�thode permettant de savoir si un l'ordre d'un personnage a d�j� �t� s�lectionn�.
	 * @param perso le personnage dont on v�rifie l'ordre
	 * @return true si un personnage de m�me ordre est d�j� s�lectionn�
	 */
	public boolean ordreDejaSelectionne(Personnage perso) {
		boolean retour = false;
		if(!listePersoJoue.isEmpty())
		{
			for(Personnage p : listePersoJoue)
			{
				if(p.compareTo(perso) == 0)
				{
					retour = true;
				}
			}
		}
		return retour;
	}
	
	/**
	 * M�thode permettant de conna�tre le gagnant de la partie.
	 */
	public void trouverGagnant() {
		gagnant = null;
		Iterator<Joueur> iteJoueur = iterator();
		Joueur j;
		while(gagnant == null && iteJoueur.hasNext())
		{
			j = iteJoueur.next();
			if(j.huitBatiments()) //Le premier joueur ayant construit 8 b�timents est le gagnant.
			{
				gagnant = j;
			}
		}
	}
	
	/**
	 * M�thode permettant de savoir si un joueur est le gagnant.
	 * @param j le joueur � v�rifier
	 * @return true si j est le gagnant
	 */
	public boolean isGagnant(Joueur j) {
		return (gagnant == j);
	}
	
	/**
	 * M�thode permettant de faire le classement des joueurs selon leur score.
	 * @return la liste des joueurs tri�e par leurs scores.
	 */
	public ArrayList<Joueur> getClassement() {
		ArrayList<Joueur> classement = new ArrayList<Joueur>(); //Le classement des joueurs, vide au d�but
		ArrayList<Joueur> tmpListeJoueur = new ArrayList<Joueur>(listeJoueur); //Liste temporaire listant les joueurs pas encore class�s
		Joueur tmp;
		do
		{
			tmp = tmpListeJoueur.get(0); //On prend le premier joueur comme joueur temporaire
			for(Joueur j : tmpListeJoueur) //Pour chaque joueur, on v�rifie si son score est sup�rieur au joueur temporaire
			{
				if(j.calculerPoints() > tmp.calculerPoints()) //Le premier joueur ayant un score sup�rieur au joueur temporaire prend sa place...
				{
					tmp = j;
				}
			}
			classement.add(tmpListeJoueur.remove(tmpListeJoueur.indexOf(tmp))); //...et sort de la liste temporaire pour entrer dans le classement.
		}
		while(!tmpListeJoueur.isEmpty()); //On r�p�te jusqu'� ce que la liste temporaire soit vide.
		return classement;
	}
	
	public boolean pileBatimentVide() {
		return pileBatiment.isEmpty();
	}
}
