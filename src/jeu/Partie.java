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
public class Partie implements Iterable<Joueur> {
	
	private int nombreDeTour;
	private LinkedList<Joueur> listeJoueur;
	private Joueur couronne;
	private LinkedList<Quartier> pileQuartier;
	private ArrayList<Personnage> pilePerso;
	private ArrayList<Personnage> listePersoJoue;
	
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
	 * @return le nombre de tour écoulé
	 */
	public int getNombreDeTour() {
		return nombreDeTour;
	}
	
	/**
	 * @return la listeJoueur
	 */
	public LinkedList<Joueur> getListeJoueur() {
		return listeJoueur;
	}
	
	public int getNbJoueur() {
		return listeJoueur.size();
	}
	
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
	 * @return le joueur qui détient la couronne
	 */
	public Joueur getCouronne() {
		return couronne;
	}

	/**
	 * @param couronne le joueur qui obtient la couronne
	 */
	public void setCouronne(Joueur couronne) {
		this.couronne = couronne;
	}
	
	/**
	 * @return le pilePerso
	 */
	public ArrayList<Personnage> getPilePerso() {
		return pilePerso;
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
	public void piocherQuartier(Joueur j, int nbQuartier) {
		for(int i=0; i<nbQuartier; i++)
		{
			j.addCarteMain(pileQuartier.removeFirst());
		}
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
	
	/** Méthode permettant d'initialiser une partie
	 * 
	 */
	private void initialiser() {
		// Création des familles de carte
		Famille religion = new Famille("Religion", Color.blue);
		Famille noblesse = new Famille("Noblesse", Color.yellow);
		Famille commerce = new Famille("Commerce", Color.green);
		Famille militaire = new Famille("Militaire", Color.red);
		Famille merveille = new Famille("Merveille", Color.magenta);
		
		// Création et ajout à la pile de chacune des cartes Quartier et Merveille
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
	 * @return un Iterator<Joueur> qui permet de parcourir les joueurs selon leur ordre autour de la table, en commençant par le possésseur de la couronne.
	 */
	public Iterator<Joueur> iteratorTable() {
		return listeJoueur.iterator();
	}

	/**
	 * @return un Iterator<Joueur> qui permet de parcourir les joueurs selon l'ordre de leurs Personnage.
	 */
	public Iterator<Joueur> iterator() {
		return new JoueurOrdrePersoIterator(this);
	}
	
	/** positionne le premier joueur au porteur de la couronne
	 * 
	 */
	public void couronneEnPremier() {
		while(listeJoueur.getFirst() != couronne)
		{
			listeJoueur.addLast(listeJoueur.removeFirst());
		}
	}
	
	public void selectPerso(ArrayList<Personnage> perso) {
		listePersoJoue = perso;
	}
	
	public ArrayList<Personnage> getListePersoJoue() {
		return listePersoJoue;
	}
	
	public boolean verifierPersoSelectionne() throws NombreDePersonnageSelectionneIncorrectException, PlusieursPersonnageDuMemeOrdreException, PasDeRoiOuDEmpereurException {
		boolean retour = true;
		
		// On vérifie le nombre de carte.
		if((getNbJoueur() == 8 && listePersoJoue.size() != 9) || (getNbJoueur() < 7 && listePersoJoue.size() != 8))
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
			
			if(p.getNom() == "Roi" || p.getNom() == "Empereur")
			{
				roi = true;
			}
		}
		int i =0;
		while(retour && i<9)
		{
			if(ordre[i] != 1 || (i==8 && ordre[i] != 0))
			{
				retour = false;
			}
			i++;
		}
		if(!retour)
		{
			throw new PlusieursPersonnageDuMemeOrdreException();
		}

		if(!roi)
		{
			throw new PasDeRoiOuDEmpereurException();
		}
		
		return retour;
	}
	
	public boolean ordreDejaSelectionne(Personnage perso) {
		boolean retour = false;
		if(!listePersoJoue.isEmpty())
		{
			for(Personnage p : listePersoJoue)
			{
				if(p.memeOrdre(perso))
				{
					retour = true;
				}
			}
		}
		return retour;
	}
}
