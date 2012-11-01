package carte.batiment;

import java.util.Comparator;


/**
 * @author anthony
 *
 */

public class ComparatorBatimentCout implements Comparator<Object> {
	
	public int compare(Object q1, Object q2) {
		return ((Batiment)q1).getPrix() -((Batiment)q2).getPrix();
	}

}
