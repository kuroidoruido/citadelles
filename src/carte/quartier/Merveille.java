package carte.quartier;

import carte.Famille;

/**
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 24 oct. 2012
 *
 */
public class Merveille extends Quartier {
	
	protected String texteCapacite;
	protected int supplementPoint;
	
	/**
	 * @param nom le nom de la merveille
	 * @param famille la famille de la merveille
	 * @param prix le prix de la merveille
	 * @param texteCapacite le texte décrivant la capacité de la merveille
	 * @param supplementPoint le supplément de point apporté par la merveille
	 */
	public Merveille(String nom, Famille famille, int prix,
			String texteCapacite, int supplementPoint) {
		super(nom, famille, prix);
		this.texteCapacite = texteCapacite;
		this.supplementPoint = supplementPoint;
	}
	/**
	 * @return le texte décrivant la capacité de la merveille
	 */
	public String getTexteCapacite() {
		return texteCapacite;
	}
	
	public int calculPoint() {
		return super.getPrix()+supplementPoint;
	}
}
