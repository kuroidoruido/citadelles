package carte.quartier;

import java.util.Comparator;

/**
 * Classe servant à comparer des quartiers selon leur famille.
 * @author Anthony Pena
 *
 */

public class ComparatorQuartierFamille implements Comparator<Object> {
	
	/**
	* Méthode permettant de comparer les familles de deux quartiers
	* @return un entier représentant la différence entre les deux familles (0 si identiques)
	*/
	public int compare(Object q1, Object q2) {
		return ((Quartier)q1).getFamille().compareTo(((Quartier)q2).getFamille());
	}

}
