package carte.batiment;

import java.util.Comparator;

/**
 * @author anthony
 *
 */

public class ComparatorBatimentFamille implements Comparator<Object> {
	
	public int compare(Object q1, Object q2) {
		return ((Batiment)q1).getFamille().compareTo(((Batiment)q2).getFamille());
	}

}
