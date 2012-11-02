package carte.batiment;

import java.util.Comparator;


/**
 * Classe servant � comparer des b�timents selon leur co�t.
 * @author Anthony Pena
 *
 */

/**
* M�thode permettant de comparer le prix de deux b�timents
* @return la diff�rence de prix des deux b�timents
*/
public class ComparatorBatimentCout implements Comparator<Object> {
	
	public int compare(Object q1, Object q2) {
		return ((Batiment)q1).getPrix() -((Batiment)q2).getPrix();
	}

}
