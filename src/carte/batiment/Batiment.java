package carte.batiment;

import carte.Carte;
import carte.Famille;

/**
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 24 oct. 2012
 *
 */
public abstract class Batiment extends Carte implements Comparable<Batiment> {
	
	protected int prix;

	/**
	 * @param nom le nom du Batiment
	 * @param famille la famille du quatier
	 * @param prix le prix de construction du Batiment
	 */
	public Batiment(String nom, Famille famille, int prix) {
		super(nom, famille);
		this.prix = prix;
	}
	
	/**
	 * @param nom le nom du Batiment
	 * @param prix le prix de construction du Batiment
	 */
	public Batiment(String nom, int prix) {
		this(nom, null, prix);
	}

	/**
	 * @return le prix de construction du Batiment
	 */
	public int getPrix() {
		return prix;
	}
	
	public int calculPoints() {
		return prix;
	}
	
	public int compareTo(Batiment b) {
		int retour = 0;
		if(this.famille.compareTo(b.famille) == 0)
		{
			retour = this.prix - b.prix;
		}
		else
		{
			retour = this.famille.compareTo(b.famille);
		}
		return retour;
	}
	
	public String toString() {
		String retour = nom+"(";
		if(famille != null)
		{
			retour += famille+",";
		}
		retour += prix+")";
		return retour;
	}
	
}
