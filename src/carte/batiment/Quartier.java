/**
 * 
 */
package carte.batiment;

import carte.Famille;

/**
 * @author anthony
 *
 */
public class Quartier extends Batiment {

	/**
	 * @param nom le nom du Batiment
	 * @param famille la famille du quatier
	 * @param prix le prix de construction du Batiment
	 */
	public Quartier(String nom, Famille famille, int prix) {
		super(nom, famille,prix);
	}
	
	/**
	 * @param nom le nom du Batiment
	 * @param prix le prix de construction du Batiment
	 */
	public Quartier(String nom, int prix) {
		super(nom, prix);
	}

}
