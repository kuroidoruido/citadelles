package carte;

/**
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 24 oct. 2012
 *
 */
public abstract class Carte {
	
	protected String nom;
	protected Famille famille;
	
	/**
	 * @param nom le nom de la carte
	 * @param famille la famille de la carte
	 */
	public Carte(String nom, Famille famille) {
		this.nom = nom;
		this.famille = famille;
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
	
	

}
