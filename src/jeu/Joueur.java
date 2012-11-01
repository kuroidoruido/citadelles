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
	 * @param nom le nom du joueur
	 */
	public Joueur(String nom, Partie partie) {
		super();
		this.nom = nom;
		this.main = new TreeSet<Batiment>();
		this.perso = null;
		this.or = 2;
		this.batimentConstruit = new ArrayList<Batiment>();
		this.droitConstruction = 1;
		this.partie = partie;
	}
	
	/**
	 * @return le nom du joueur
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * @return la liste des quartiers que le joueur a en main
	 */
	public TreeSet<Batiment> getMain() {
		return main;
	}
	
	public void addCarteMain(Batiment b) {
		main.add(b);
	}
	
	/**
	 * @return le personnage attribu� au joueur
	 */
	public Personnage getPerso() {
		return perso;
	}
	
	/**
	 * @param p le personnage � attribuer au joueur
	 */
	public void setPerso(Personnage p) {
		perso = p;
	}
	
	/**
	 * @return le nombre de pi�ce d'or disponible
	 */
	public int getOr() {
		return or;
	}
	
	public void addOr(int nbPieces) {
		or += nbPieces;
	}
	
	/**
	 * ajoute l'or correspondant aux quartier construit
	 */
	public void addOrQuartier() {
		Famille famillePerso = perso.getFamille();
		if(famillePerso != null)
		{
			for(Batiment b : batimentConstruit)
			{
				if(b.getFamille() == famillePerso)
				{
					or += 1;
				}
			}
		}
	}
		
	public void subOr(int nbPieces) throws PasAssezDOrException {
		if(nbPieces <= or)
		{
			or -= nbPieces;
		}
		else
		{
			throw new PasAssezDOrException();
		}
	}
	
	public void construireBatiment(Batiment b) throws BatimentPasDansLaMainException, BatimentDejaConstruiteException {
		if(main.contains(b))
		{
			if(batimentConstruit.contains(b))
			{
				throw new BatimentDejaConstruiteException();
			}
			Joueur bailli = partie.chercher("Bailli");
			if(bailli != null)
			{
				try {
					subOr(b.getPrix()+1);
					bailli.addOr(1);
				} catch (PasAssezDOrException e) {
					e.printStackTrace();
				}
			}
			else
			{
				try {
					subOr(b.getPrix());
				} catch (PasAssezDOrException e) {
					e.printStackTrace();
				}
			}
			main.remove(b);
			batimentConstruit.add(b);
		}
		else
		{
			throw new BatimentPasDansLaMainException();
		}
	}
	
	/**
	 * @return la liste des quartiers construits
	 */
	public ArrayList<Batiment> getBatimentConstruit() {
		return batimentConstruit;
	}
	
	/**
	 * @return le nombre de quartier que le joueur est autoris� � construire au prochain tour
	 */
	public int getDroitConstruction() {
		return droitConstruction;
	}
	
	public void addDroitConstruction(int supDroit) {
		droitConstruction += supDroit;
	}
	
	public boolean huitBatiments() {
		return (batimentConstruit.size() >= 8);
	}
	
	public int calculerPoints() {
		int points = 0;
		ArrayList<Famille> listeFamilleBatiment = new ArrayList<Famille>();
		for(Batiment b : batimentConstruit)
		{
			if(!listeFamilleBatiment.contains(b.getFamille()))
			{
				listeFamilleBatiment.add(b.getFamille());
			}
			points += b.calculPoints();
		}
		if(listeFamilleBatiment.size() == Famille.getNbFamille())
		{
			points += 3;
		}
		if(huitBatiments())
		{
			points += 2;
		}
		if(partie.isGagnant(this))
		{
			points += 2;
		}
		return points;
	}
	
	private int getNbBatimentConstruit(Famille f) {
		int nbQuartier = 0;
		for(Batiment b : batimentConstruit)
		{
			if(b.getFamille().equals(f))
			{
				nbQuartier++;
			}
		}
		return nbQuartier;
	}
	
	public int getNbBatimentReligion() {
		return getNbBatimentConstruit(Partie.religion);
	}
	
	public int getNbBatimentNoblesse() {
		return getNbBatimentConstruit(Partie.noblesse);
	}
	
	public int getNbBatimentCommerce() {
		return getNbBatimentConstruit(Partie.commerce);
	}
	
	public int getNbBatimentMilitaire() {
		return getNbBatimentConstruit(Partie.militaire);
	}
	
	public int getNbBatimentMerveille() {
		return getNbBatimentConstruit(Partie.merveille);
	}
	
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
	
	public void trierBatimentConstruitFamille() {
		Collections.sort(batimentConstruit, new ComparatorBatimentFamille());
	}
	
	public void trierBatimentConstruitCout() {
		Collections.sort(batimentConstruit, new ComparatorBatimentCout());
	}
}
