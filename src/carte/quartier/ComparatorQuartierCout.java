package carte.quartier;

import java.util.Comparator;

/**
 * Classe servant à comparer des quartiers selon leur coût.
 * @author Anthony Pena
 *
 */

public class ComparatorQuartierCout implements Comparator<Object> {
	
	/**
	* Méthode permettant de comparer le prix de deux quartiers
	* @return la différence de prix des deux quartiers
	*/
	public int compare(Object q1, Object q2) {
		return ((Quartier)q1).getPrix() -((Quartier)q2).getPrix();
	}

}
