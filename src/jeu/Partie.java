package jeu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import carte.Famille;
import carte.personnage.*;
import carte.quartier.Merveille;
import carte.quartier.Quartier;

/**
 * Classe modélisant le déroulement complet d'une partie de Citadelles
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 24 oct. 2012
 *
 */
public class Partie implements Iterable<Joueur> {
	
	private int nombreDeTour;
	private LinkedList<Joueur> listeJoueur;
	private Joueur couronne;
	private Joueur gagnant;
	private LinkedList<Quartier> pileQuartier;
	private ArrayList<Personnage> pilePerso;
	private ArrayList<Personnage> listePersoJoue;
	
	// Création des familles de carte
	public static Famille religion = new Famille("Religion", Color.blue);
	public static Famille noblesse = new Famille("Noblesse", Color.yellow);
	public static Famille commerce = new Famille("Commerce", Color.green);
	public static Famille militaire = new Famille("Militaire", Color.red);
	public static Famille merveille = new Famille("Merveille", Color.magenta);
			
	
	/** Constructeur de Partie, initialise la partie en instanciant les listes et en distribuant les cartes.
	 * @throws NombreDeJoueurIncorrectException 
	 * 
	 */
	public Partie(LinkedList<String> listeJoueur) throws NombreDeJoueurIncorrectException {
		super();
		if(3 <= listeJoueur.size() && listeJoueur.size() <= 8)
		{
			this.nombreDeTour = 0;
			this.listeJoueur = new LinkedList<Joueur>();
			for(String j : listeJoueur)
			{
				this.listeJoueur.add((new Joueur(j,this)));
			}
			this.pileQuartier = new LinkedList<Quartier>();
			this.pilePerso = new ArrayList<Personnage>();
			this.listePersoJoue = new ArrayList<Personnage>();
			initialiser();
		}
		else
		{
			throw new NombreDeJoueurIncorrectException("Erreur sur le nombre de joueur ! Le nombre de joueur doit être compris entre 3 et 8.");
		}
	}

	/**
	 * Méthode permettand de connaître le nombre de tours passés depuis le début de la partie
	 * @return le nombre de tours écoulés
	 */
	public int getNombreDeTour() {
		return nombreDeTour;
	}
	
	/**
	 * Méthode permettant de récupérer la liste des joueurs.
	 * @return la listeJoueur
	 */
	public LinkedList<Joueur> getListeJoueur() {
		return listeJoueur;
	}
	
	/**
	 * Méthode permettant de récupérer le nombre de joueurs de la partie.
	 * @return le nombre de joueurs dans la partie
	 */
	public int getNbJoueur() {
		return listeJoueur.size();
	}
	
	/**
	 * Méthode permettant de rechercher le joueur jouant le personnage spécifié
	 * @param p le personnage dont on veut connaître le joueur associé
	 * @return le joueur jouant le personnage recherché si ce dernier est trouvé, null sinon
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
	 * Méthode permettant de connaître le joueur possédant la couronne
	 * @return le joueur qui détient la couronne
	 */
	public Joueur getCouronne() {
		return couronne;
	}

	/**
	 * Méthode permettant de donner la couronne à un joueur
	 * @param couronne le joueur qui obtient la couronne
	 */
	public void setCouronne(Joueur couronne) {
		this.couronne = couronne;
	}
	
	/**
	 * Méthode permettant de récupérer la pile de personnages
	 * @return la pile de personnages
	 */
	public ArrayList<Personnage> getPilePerso() {
		return pilePerso;
	}

	/**
	 * Méthode permettant de savoir s'il y a un Bailli parmi les personnages des joueurs
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
	 * Méthode permettant de piocher des cartes Quartier
	 * @param nbQuartier le nombre de quartiers à piocher
	 * @return les nbQuartier premiers quartiers de la pile de quartier
	 */
	public void piocherQuartier(Joueur j, int nbQuartier) {
		for(int i=0; i<nbQuartier; i++)
		{
			j.addCarteMain(pileQuartier.removeFirst());
		}
	}
	
	/**
	 * Méthode permettant de savoir si un des joueurs a construit 8 quartiers ou plus, ce qui permettra de mettre fin à la partie.
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
	
	/** 
	 * Méthode permettant d'initialiser une partie, en créant tous les quartiers et personnages utilisables par les joueurs.
	 */
	private void initialiser() {
		
		// Création et ajout à la pile de chacune des cartes Quartier et Merveille
		pileQuartier.clear();
		pileQuartier.add(new Quartier("Temple",religion,1));
		pileQuartier.add(new Quartier("Temple",religion,1));
		pileQuartier.add(new Quartier("Temple",religion,1));
		pileQuartier.add(new Quartier("Église",religion,2));
		pileQuartier.add(new Quartier("Église",religion,2));
		pileQuartier.add(new Quartier("Église",religion,2));
		pileQuartier.add(new Quartier("Église",religion,2));
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

		// Mélange aléatoire de la pile de Quartier
		Collections.shuffle((List<Quartier>)(pileQuartier));

		// Création et ajout des cartes Personnage à la liste des Personnages
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

		// On distribue à chaque joueur 4 cartes Quartier.
		for(Joueur j : listeJoueur)
		{
			j.addCarteMain(pileQuartier.removeFirst());
			j.addCarteMain(pileQuartier.removeFirst());
			j.addCarteMain(pileQuartier.removeFirst());
			j.addCarteMain(pileQuartier.removeFirst());
		}
		
		// On choisit aléatoirement le joueur qui portera la couronne au premier tour.
		setCouronne(listeJoueur.get((new Double(Math.random()*listeJoueur.size()).intValue())));
		couronneEnPremier();
	}
	
	/**
	 * @return un Iterator<Joueur> qui permet de parcourir les joueurs selon l'ordre de leurs Personnage.
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
	 * Méthode permettant de définir quels personnages seront joués pendant le tour.
	 * @param perso la liste des personnages à jouer
	 */
	public void selectPerso(ArrayList<Personnage> perso) {
		listePersoJoue = perso;
	}
	
	/**
	 * Méthode permettant de récupérer la liste des personnages joués du tour actuel.
	 * @return la liste des personnages joués du tour
	 */
	public ArrayList<Personnage> getListePersoJoue() {
		return listePersoJoue;
	}
	
	
	/**
	 * Méthode permettant de vérifier si la sélection des personnages est valide.
	 * @return true si le nombre de cartes est égal au nombre de joueurs plus un, ET s'il n'y a pas plusieurs personnages de même ordre de passage, ET s'il y a un Roi ou un Empereur.
	 * @throws NombreDePersonnageSelectionneIncorrectException
	 * @throws PlusieursPersonnageDuMemeOrdreException
	 * @throws PasDeRoiOuDEmpereurException
	 */
	public boolean verifierPersoSelectionne() throws NombreDePersonnageSelectionneIncorrectException, PlusieursPersonnageDuMemeOrdreException, PasDeRoiOuDEmpereurException {
		boolean retour = true;
		
		// On vérifie le nombre de cartes.
		if((getNbJoueur() < 7 && listePersoJoue.size() != 8) || (getNbJoueur() == 8 && listePersoJoue.size() != 9) || (listePersoJoue.size() > 9))
		{
			throw new NombreDePersonnageSelectionneIncorrectException("nombre de joueur = "+getNbJoueur()+" nombre de Personnage sélectionnés = "+listePersoJoue.size());
		}
		
		// On vérifie qu'un seul Personnage de chaque ordre soit dans les cartes sélectionnées
		// Sauf pour l'ordre 9 qui peut être à zéro
		// ET
		//On vérifie qu'il y a un Roi ou un Empereur
		Integer[] ordre = new Integer[9];
		boolean roi = false;
		for(int i=0;i<9;i++)
		{
			ordre[i]=0;
		}
		for(Personnage p : listePersoJoue)
		{
			ordre[p.getOrdre()-1]++;
			
			if(p.getNom() == "Roi" || p.getNom() == "Empereur") //On vérifie s'il y a un Roi OU un Empereur
			{
				roi = true;
			}
		}
		int i =0;
		String ordreString = "";
		while(retour && i<9)
		{
			ordreString += ordre[i]+" ";
			if((i < 7 & ordre[i] != 1) || (i == 8 && ordre[i] > 1)) //On vérifie que chaque personnage a un ordre unique
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
	 * Méthode permettant de savoir si un l'ordre d'un personnage a déjà été sélectionné.
	 * @param perso le personnage dont on vérifie l'ordre
	 * @return true si un personnage de même ordre est déjà sélectionné
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
	 * Méthode permettant de connaître le gagnant de la partie.
	 */
	public void trouverGagnant() {
		gagnant = null;
		Iterator<Joueur> iteJoueur = iterator();
		Joueur j;
		while(gagnant == null && iteJoueur.hasNext())
		{
			j = iteJoueur.next();
			if(j.huitQuartier()) //Le premier joueur ayant construit 8 quartiers est le gagnant.
			{
				gagnant = j;
			}
		}
	}
	
	/**
	 * Méthode permettant de savoir si un joueur est le gagnant.
	 * @param j le joueur à vérifier
	 * @return true si j est le gagnant
	 */
	public boolean isGagnant(Joueur j) {
		return (gagnant == j);
	}
	
	/**
	 * Méthode permettant de faire le classement des joueurs selon leur score.
	 * @return la liste des joueurs triée par leurs scores.
	 */
	public ArrayList<Joueur> getClassement() {
		ArrayList<Joueur> classement = new ArrayList<Joueur>(); //Le classement des joueurs, vide au début
		ArrayList<Joueur> tmpListeJoueur = new ArrayList<Joueur>(listeJoueur); //Liste temporaire listant les joueurs pas encore classés
		Joueur tmp;
		do
		{
			tmp = tmpListeJoueur.get(1); //On prend le premier joueur comme joueur temporaire
			for(Joueur j : tmpListeJoueur) //Pour chaque joueur, on vérifie si son score est supérieur au joueur temporaire
			{
				if(j.calculerPoints() > tmp.calculerPoints()) //Le premier joueur ayant un score supérieur au joueur temporaire prend sa place...
				{
					tmp = j;
				}
			}
			classement.add(tmpListeJoueur.remove(tmpListeJoueur.indexOf(tmp))); //...et sort de la liste temporaire pour entrer dans le classement.
		}
		while(tmpListeJoueur.isEmpty()); //On répète jusqu'à ce que la liste temporaire soit vide.
		return classement;
	}
}
