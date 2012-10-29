package carte;

/**
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 24 oct. 2012
 *
 */
public class Quartier extends Carte {
	
	protected int prix;

	/**
	 * @param nom le nom du quartier
	 * @param famille la famille du quatier
	 * @param prix le prix de construction du quartier
	 */
	public Quartier(String nom, Famille famille, int prix) {
		super(nom, famille);
		this.prix = prix;
	}
	
	/**
	 * @param nom le nom du quartier
	 * @param prix le prix de construction du quartier
	 */
	public Quartier(String nom, int prix) {
		this(nom, null, prix);
	}

	/**
	 * @return le prix de construction du quartier
	 */
	public int getPrix() {
		return prix;
	}
	
	public int calculPoints() {
		return prix;
	}
}
