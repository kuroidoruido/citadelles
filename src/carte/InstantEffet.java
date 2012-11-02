/**
 * 
 */
package carte;

/**
 * @author anthony
 *
 */
public class InstantEffet {
	
	public static int sansEffet = -5; //Carte sans effet
	public static int effetPre = -1; //Effet pouvant être déclenché avant la construction d'un bâtiment
	public static int effetPreOuPost = 0; //Effet pouvant être déclenché avant ou après la construction d'un bâtiment
	public static int effetPost = +1; //Effet pouvant être déclenché après la construction d'un bâtiment
	public static int effetPassif = +5; //Effet permanent
	
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
