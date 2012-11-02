package carte.personnage;

import carte.Carte;
import carte.Famille;
import jeu.Partie;

/**
 * Classe abstraite servant � mod�liser chaque personnage du jeu et leurs capacit�s.
* @author Bauchet Cl�ment
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
	* @param texteCapacite le texte d�crivant la capacit� du personnage
	* @param ordre le num�ro d'ordre du personnage
	* @param instantEffet l'instant auquel l'effet peut se d�clencher
	*/
	public Personnage(String nom, Famille famille, String texteCapacite, int ordre, Partie p, int instantEffet) {
		super(nom, famille,instantEffet);
		this.texteCapacite = texteCapacite;
		this.ordre = ordre;
		this.partie = p;
	}
	
	/**
	 * M�thode permettant de r�cup�rer le texte de la carte
	* @return le texte d�crivant la capacit� du personnage
	*/
	public String getTexteCapacite() {
		return texteCapacite;
	}

	/**
	 * M�thode permettant de r�cup�rer le num�ro d'ordre de jeu du personnage.
	* @return le num�ro d'ordre du personnage
	*/
	public int getOrdre() {
		return ordre;
	}
	
	public abstract void capacite() throws Exception;
	
	
	/**
	 * M�thode permettant de comparer les num�ros d'ordre du personnage avec un autre
	* @return la diff�rence des num�ros d'ordre des deux personnages
	*/
	public int compareTo(Personnage p) {
		return (this.ordre - p.ordre);
	}
	
	public String toString() {
		return nom;
	}
}