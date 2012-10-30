package jeu;

import java.util.ArrayList;
import java.util.TreeSet;

import carte.Famille;
import carte.Quartier;
import carte.personnage.Personnage;

/**
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
	 * @return le nom du joueur
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * @return la liste des quartiers que le joueur a en main
	 */
	public TreeSet<Quartier> getMain() {
		return main;
	}
	
	public void addCarteMain(Quartier q) {
		main.add(q);
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
	
	public void construireQuartier(Quartier q) throws QuartierPasDansLaMainException, QuartierDejaConstruiteException {
		if(main.contains(q))
		{
			if(quartierConstruit.contains(q))
			{
				throw new QuartierDejaConstruiteException();
			}
			Joueur bailli = partie.isThereBailli();
			if(bailli != null)
			{
				try {
					subOr(q.getPrix()+1);
					bailli.addOr(1);
				} catch (PasAssezDOrException e) {
					e.printStackTrace();
				}
			}
			else
			{
				try {
					subOr(q.getPrix());
				} catch (PasAssezDOrException e) {
					e.printStackTrace();
				}
			}
			main.remove(q);
			quartierConstruit.add(q);
		}
		else
		{
			throw new QuartierPasDansLaMainException();
		}
	}
	
	/**
	 * @return la liste des quartiers construits
	 */
	public ArrayList<Quartier> getQuartierConstruit() {
		return quartierConstruit;
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
	
	public boolean huitQuartier() {
		return (quartierConstruit.size() >= 8);
	}
	
	public int calculerPoints() {
		int points = 0;
		ArrayList<Famille> listeFamilleQuartier = new ArrayList<Famille>();
		for(Quartier q : quartierConstruit)
		{
			if(!listeFamilleQuartier.contains(q.getFamille()))
			{
				listeFamilleQuartier.add(q.getFamille());
			}
			points += q.calculPoints();
		}
		if(listeFamilleQuartier.size() == Famille.getNbFamille())
		{
			points += 3;
		}
		if(huitQuartier())
		{
			points += 2;
		}
		return points;
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
	
}
