/**
 * 
 */
package jeu;

/**
 * Cette exception survient si le nombre de joueurs correspondant à une partie est incorrect
 * Ce nombre doit être compris entre 3 et 8.
 * @author Anthony
 *
 */
@SuppressWarnings("serial")
public class NombreDeJoueurIncorrectException extends Exception {

	/**
	 * 
	 */
	public NombreDeJoueurIncorrectException() {
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param message
	 */
	public NombreDeJoueurIncorrectException(String message) {
		super(message);
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param cause
	 */
	public NombreDeJoueurIncorrectException(Throwable cause) {
		super(cause);
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NombreDeJoueurIncorrectException(String message, Throwable cause) {
		super(message, cause);
		// TODO Stub du constructeur généré automatiquement
	}

}
