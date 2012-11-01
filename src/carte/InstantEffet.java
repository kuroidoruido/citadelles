/**
 * 
 */
package carte;

/**
 * @author anthony
 *
 */
public class InstantEffet {
	
	public static int sansEffet = -5;
	public static int effetPre = -1;
	public static int effetPreOuPost = 0;
	public static int effetPost = +1;
	public static int effetPassif = +5;
	
	private int valeur;
	
	public InstantEffet(int instant) {
		valeur = instant;
	}
	
	public boolean equals(InstantEffet i) {
		return (this.valeur == i.valeur);
	}
	
	public int getValeur() {
		return valeur;
	}
}
