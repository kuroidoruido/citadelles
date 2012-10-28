package carte.personnage;

import carte.Carte;
import carte.Famille;

/**
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 24 oct. 2012
 *
 */
public abstract class Personnage extends Carte {

	protected String texteCapacite;
	protected int ordre;
	
	/**
	 * @param nom le nom du personnage
	 * @param famille la famille du personnage
	 * @param texteCapacite le texte décrivant la capacité du personnage
	 * @param ordre le numéro d'ordre du personnage
	 */
	public Personnage(String nom, Famille famille, String texteCapacite, int ordre) {
		super(nom, famille);
		this.texteCapacite = texteCapacite;
		this.ordre = ordre;
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
