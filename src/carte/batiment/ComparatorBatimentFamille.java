package carte.batiment;

import java.util.Comparator;

/**
 * Classe servant � comparer des b�timents selon leur famille.
 * @author Anthony Pena
 *
 */


/**
* M�thode permettant de comparer les familles de deux b�timents
* @return un entier repr�sentant la diff�rence entre les deux familles (0 si identiques)
*/
public class ComparatorBatimentFamille implements Comparator<Object> {
	
	public int compare(Object q1, Object q2) {
		return ((Batiment)q1).getFamille().compareTo(((Batiment)q2).getFamille());
	}

}
