package carte.quartier;

import java.util.Comparator;

/**
 * @author anthony
 *
 */

public class ComparatorQuartierCout implements Comparator<Object> {
	
	public int compare(Object q1, Object q2) {
		return ((Quartier)q1).getPrix() -((Quartier)q2).getPrix();
	}

}
