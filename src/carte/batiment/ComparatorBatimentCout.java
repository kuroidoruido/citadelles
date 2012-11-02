package carte.batiment;

import java.util.Comparator;


/**
 * Classe servant à comparer des bâtiments selon leur coût.
 * @author Anthony Pena
 *
 */

/**
* Méthode permettant de comparer le prix de deux bâtiments
* @return la différence de prix des deux bâtiments
*/
public class ComparatorBatimentCout implements Comparator<Object> {
	
	public int compare(Object q1, Object q2) {
		return ((Batiment)q1).getPrix() -((Batiment)q2).getPrix();
	}

}
