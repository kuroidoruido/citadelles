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
	public static int effetPre = -1; //Effet pouvant �tre d�clench� avant la construction d'un b�timent
	public static int effetPreOuPost = 0; //Effet pouvant �tre d�clench� avant ou apr�s la construction d'un b�timent
	public static int effetPost = +1; //Effet pouvant �tre d�clench� apr�s la construction d'un b�timent
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
