package carte.personnage;

import carte.Carte;
import carte.Famille;
import jeu.Partie;

/**
 * Classe abstraite servant à modéliser chaque personnage du jeu et leurs capacités.
* @author Bauchet Clément
* @author Pena Anthony
* @version 24 oct. 2012
*
*/
public abstract class Personnage extends Carte implements Comparable<Personnage> {

	protected String texteCapacite;
	protected int ordre;
	protected Partie partie;
	
	/**
	 * Constructeur de Personnage
	* @param nom le nom du personnage
	* @param famille la famille du personnage
	* @param texteCapacite le texte décrivant la capacité du personnage
	* @param ordre le numéro d'ordre du personnage
	* @param instantEffet l'instant auquel l'effet peut se déclencher
	*/
	public Personnage(String nom, Famille famille, String texteCapacite, int ordre, Partie p, int instantEffet) {
		super(nom, famille,instantEffet);
		this.texteCapacite = texteCapacite;
		this.ordre = ordre;
		this.partie = p;
	}
	
	/**
	 * Méthode permettant de récupérer le texte de la carte
	* @return le texte décrivant la capacité du personnage
	*/
	public String getTexteCapacite() {
		return texteCapacite;
	}

	/**
	 * Méthode permettant de récupérer le numéro d'ordre de jeu du personnage.
	* @return le numéro d'ordre du personnage
	*/
	public int getOrdre() {
		return ordre;
	}
	
	public abstract void capacite() throws Exception;
	
	
	/**
	 * Méthode permettant de comparer les numéros d'ordre du personnage avec un autre
	* @return la différence des numéros d'ordre des deux personnages
	*/
	public int compareTo(Personnage p) {
		return (this.ordre - p.ordre);
	}
	
	public String toString() {
		return nom;
	}
}