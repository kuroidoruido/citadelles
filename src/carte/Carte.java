package carte;

/**
 * Cette classe modélise les cartes utilisées dans le jeu. Chaque carte a un nom, une famille et éventuellement un effet spécial.
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 24 oct. 2012
 *
 */
public abstract class Carte {
	
	protected String nom;
	protected Famille famille;
	protected InstantEffet instantEffet;
	
	/**
	 * Constructeur de Carte
	 * @param nom le nom de la carte
	 * @param famille la famille de la carte
	 * @param instantEffet indique à quel moment du tour peut être utilisé l'effet : -1 avant la phase de construction, +1 après la phase de construction, 0 avant ou après
	 */
	public Carte(String nom, Famille famille, int instantEffet) {
		this.nom = nom;
		this.famille = famille;
		this.instantEffet = new InstantEffet(instantEffet);
	}
	
	/**
	 * Constructeur de Carte par défaut n'ayant pas d'effet
	 * @param nom le nom de la carte
	 * @param famille la famille de la carte
	 */
	public Carte(String nom, Famille famille) {
		this(nom, famille, InstantEffet.sansEffet);
	}
	
	/**
	 * Méthode permettant de récupérer le nom de la carte
	 * @return le nom de la carte
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Méthode permettant de récupérer la famille de la carte
	 * @return la famille de la carte
	 */
	public Famille getFamille() {
		return famille;
	}
	
	/**
	 * Méthode permettant de récupérer l'instant de l'effet de la carte
	 * @return l'instant auquel peut s'activer l'effet de la carte
	 */
	public InstantEffet getInstantEffet() {
		return instantEffet;
	}
	
}
