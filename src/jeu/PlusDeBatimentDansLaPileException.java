/**
 * 
 */
package jeu;

/**
 * Cette exception survient lorsqu'un joueur essaie de piocher un bâtiment alors que la pile de bâtiments est vide.
 * @author Anthony Pena
 *
 */
@SuppressWarnings("serial")
public class PlusDeBatimentDansLaPileException extends Exception {

	/**
	 * 
	 */
	public PlusDeBatimentDansLaPileException() {
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param message
	 */
	public PlusDeBatimentDansLaPileException(String message) {
		super(message);
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param cause
	 */
	public PlusDeBatimentDansLaPileException(Throwable cause) {
		super(cause);
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PlusDeBatimentDansLaPileException(String message, Throwable cause) {
		super(message, cause);
		// TODO Stub du constructeur généré automatiquement
	}

}
