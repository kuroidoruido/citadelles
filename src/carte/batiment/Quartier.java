/**
 * 
 */
package carte.batiment;

import carte.Famille;

/**
 * Classe modélisant les bâtiments standards du jeu, les quartiers.
 * @author Anthony Pena
 *
 */
public class Quartier extends Batiment {

	/**
	 * @param nom le nom du quartier
	 * @param famille la famille du quaetier
	 * @param prix le prix de construction du quartier
	 */
	public Quartier(String nom, Famille famille, int prix) {
		super(nom, famille,prix);
	}

}
