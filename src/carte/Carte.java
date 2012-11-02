package carte;

/**
 * Cette classe mod�lise les cartes utilis�es dans le jeu. Chaque carte a un nom, une famille et �ventuellement un effet sp�cial.
 * @author Bauchet Cl�ment
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
	 * @param instantEffet indique � quel moment du tour peut �tre utilis� l'effet : -1 avant la phase de construction, +1 apr�s la phase de construction, 0 avant ou apr�s
	 */
	public Carte(String nom, Famille famille, int instantEffet) {
		this.nom = nom;
		this.famille = famille;
		this.instantEffet = new InstantEffet(instantEffet);
	}
	
	/**
	 * Constructeur de Carte par d�faut n'ayant pas d'effet
	 * @param nom le nom de la carte
	 * @param famille la famille de la carte
	 */
	public Carte(String nom, Famille famille) {
		this(nom, famille, InstantEffet.sansEffet);
	}
	
	/**
	 * M�thode permettant de r�cup�rer le nom de la carte
	 * @return le nom de la carte
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * M�thode permettant de r�cup�rer la famille de la carte
	 * @return la famille de la carte
	 */
	public Famille getFamille() {
		return famille;
	}
	
	/**
	 * M�thode permettant de r�cup�rer l'instant de l'effet de la carte
	 * @return l'instant auquel peut s'activer l'effet de la carte
	 */
	public InstantEffet getInstantEffet() {
		return instantEffet;
	}
	
}
