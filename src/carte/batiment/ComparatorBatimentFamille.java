package carte.batiment;

import java.util.Comparator;

/**
 * Classe servant à comparer des bâtiments selon leur famille.
 * @author Anthony Pena
 *
 */


/**
* Méthode permettant de comparer les familles de deux bâtiments
* @return un entier représentant la différence entre les deux familles (0 si identiques)
*/
public class ComparatorBatimentFamille implements Comparator<Object> {
	
	public int compare(Object q1, Object q2) {
		return ((Batiment)q1).getFamille().compareTo(((Batiment)q2).getFamille());
	}

}
