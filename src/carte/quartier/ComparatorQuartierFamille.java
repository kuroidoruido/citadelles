package carte.quartier;

import java.util.Comparator;

/**
 * Classe servant � comparer des quartiers selon leur famille.
 * @author Anthony Pena
 *
 */

public class ComparatorQuartierFamille implements Comparator<Object> {
	
	/**
	* M�thode permettant de comparer les familles de deux quartiers
	* @return un entier repr�sentant la diff�rence entre les deux familles (0 si identiques)
	*/
	public int compare(Object q1, Object q2) {
		return ((Quartier)q1).getFamille().compareTo(((Quartier)q2).getFamille());
	}

}
