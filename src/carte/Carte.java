package carte;

/**
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 24 oct. 2012
 *
 */
public abstract class Carte {
	
	public static int sansEffet = -5;
	public static int effetPre = -1;
	public static int effetPreOuPost = 0;
	public static int effetPost = +1;
	public static int effetPassif = +5;
	
	protected String nom;
	protected Famille famille;
	protected int instantEffet;
	
	/**
	 * @param nom le nom de la carte
	 * @param famille la famille de la carte
	 * @param instantEffet indique à quel moment du tour peut être utilisé l'effet : -1 avant la phase de construction, +1 après la phase de construction, 0 avant ou après
	 */
	public Carte(String nom, Famille famille, int instantEffet) {
		this.nom = nom;
		this.famille = famille;
		this.instantEffet = instantEffet;
	}
	
	/**
	 * @param nom le nom de la carte
	 * @param famille la famille de la carte
	 * * @param instantEffet indique à quel moment du tour peut être utilisé l'effet : -1 avant la phase de construction, +1 après la phase de construction, 0 avant ou après
	 */
	public Carte(String nom, Famille famille) {
		this(nom, famille, Carte.sansEffet);
	}
	
	/**
	 * @return le nom de la carte
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return la famille de la carte
	 */
	public Famille getFamille() {
		return famille;
	}
	
	public int getInstantEffet() {
		return instantEffet;
	}

}
