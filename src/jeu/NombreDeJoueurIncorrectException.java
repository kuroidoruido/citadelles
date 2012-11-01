/**
 * 
 */
package jeu;

/**
 * Cette exception survient si le nombre de joueurs correspondant � une partie est incorrect
 * Ce nombre doit �tre compris entre 3 et 8.
 * @author Anthony
 *
 */
@SuppressWarnings("serial")
public class NombreDeJoueurIncorrectException extends Exception {

	/**
	 * 
	 */
	public NombreDeJoueurIncorrectException() {
		// TODO Stub du constructeur g�n�r� automatiquement
	}

	/**
	 * @param message
	 */
	public NombreDeJoueurIncorrectException(String message) {
		super(message);
		// TODO Stub du constructeur g�n�r� automatiquement
	}

	/**
	 * @param cause
	 */
	public NombreDeJoueurIncorrectException(Throwable cause) {
		super(cause);
		// TODO Stub du constructeur g�n�r� automatiquement
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NombreDeJoueurIncorrectException(String message, Throwable cause) {
		super(message, cause);
		// TODO Stub du constructeur g�n�r� automatiquement
	}

}
