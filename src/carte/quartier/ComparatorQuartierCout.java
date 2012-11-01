package carte.quartier;

import java.util.Comparator;

/**
 * Classe servant � comparer des quartiers selon leur co�t.
 * @author Anthony Pena
 *
 */

public class ComparatorQuartierCout implements Comparator<Object> {
	
	/**
	* M�thode permettant de comparer le prix de deux quartiers
	* @return la diff�rence de prix des deux quartiers
	*/
	public int compare(Object q1, Object q2) {
		return ((Quartier)q1).getPrix() -((Quartier)q2).getPrix();
	}

}
