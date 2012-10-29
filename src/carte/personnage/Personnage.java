package carte.personnage;

import carte.Carte;
import carte.Famille;
import jeu.Partie;

/**
* @author Bauchet Clément
* @author Pena Anthony
* @version 24 oct. 2012
*
*/
public abstract class Personnage extends Carte {

	protected String texteCapacite;
	protected int ordre;
	protected Partie partie;
	
	/**
	* @param nom le nom du personnage
	* @param famille la famille du personnage
	* @param texteCapacite le texte décrivant la capacité du personnage
	* @param ordre le numéro d'ordre du personnage
	*/
	public Personnage(String nom, Famille famille, String texteCapacite, int ordre, Partie p) {
		super(nom, famille);
		this.texteCapacite = texteCapacite;
		this.ordre = ordre;
		this.partie = p;
	}
	
	/**
	* @return le texte décrivant la capacité du personnage
	*/
	public String getTexteCapacite() {
		return texteCapacite;
	}
	
	/**
	* @return le numéro d'ordre du personnage
	*/
	public int getOrdre() {
		return ordre;
	}
	
	public abstract void capacite();

}