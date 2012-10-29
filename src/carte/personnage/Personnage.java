package carte.personnage;

import carte.Carte;
import carte.Famille;
import jeu.Partie;

/**
 * @author Bauchet Cl�ment
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
	 * @param texteCapacite le texte d�crivant la capacit� du personnage
	 * @param ordre le num�ro d'ordre du personnage
	 */
	public Personnage(String nom, Famille famille, String texteCapacite, int ordre, Partie p) {
		super(nom, famille);
		this.texteCapacite = texteCapacite;
		this.ordre = ordre;
		this.partie = p;
	}
	
	/**
	 * @return le texte d�crivant la capacit� du personnage
	 */
	public String getTexteCapacite() {
		return texteCapacite;
	}
	
	/**
	 * @return le num�ro d'ordre du personnage
	 */
	public int getOrdre() {
		return ordre;
	}
	
	public abstract void capacite();
	
	public abstract void capacite(int j);
	
}
