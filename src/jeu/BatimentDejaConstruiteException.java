package jeu;

/**
 * Cette exception survient si un joueur essaie de construire un bâtiment qu'il a déjà construit.
 * Dans cette version, on n'autorise pas les joueurs à construire deux fois le même bâtiment.
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 25 oct. 2012
 *
 */
@SuppressWarnings("serial")
public class BatimentDejaConstruiteException extends Exception {

	/**
	 * 
	 */
	public BatimentDejaConstruiteException() {
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param message
	 */
	public BatimentDejaConstruiteException(String message) {
		super(message);
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param cause
	 */
	public BatimentDejaConstruiteException(Throwable cause) {
		super(cause);
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BatimentDejaConstruiteException(String message, Throwable cause) {
		super(message, cause);
		// TODO Stub du constructeur généré automatiquement
	}

}
