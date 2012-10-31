package carte.quartier;

import java.util.Comparator;

/**
 * @author anthony
 *
 */

public class ComparatorQuartierFamille implements Comparator<Object> {
	
	public int compare(Object q1, Object q2) {
		return ((Quartier)q1).getFamille().compareTo(((Quartier)q2).getFamille());
	}

}
