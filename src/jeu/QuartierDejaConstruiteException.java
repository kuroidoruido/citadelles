package jeu;

/**
 * Cette exception survient si un joueur essaie de construire un quartier qu'il a d�j� construit.
 * Dans cette version, on n'autorise pas les joueurs � construire deux fois le m�me quartier.
 * @author Bauchet Cl�ment
 * @author Pena Anthony
 * @version 25 oct. 2012
 *
 */
@SuppressWarnings("serial")
public class QuartierDejaConstruiteException extends Exception {

	/**
	 * 
	 */
	public QuartierDejaConstruiteException() {
		// TODO Stub du constructeur g�n�r� automatiquement
	}

	/**
	 * @param message
	 */
	public QuartierDejaConstruiteException(String message) {
		super(message);
		// TODO Stub du constructeur g�n�r� automatiquement
	}

	/**
	 * @param cause
	 */
	public QuartierDejaConstruiteException(Throwable cause) {
		super(cause);
		// TODO Stub du constructeur g�n�r� automatiquement
	}

	/**
	 * @param message
	 * @param cause
	 */
	public QuartierDejaConstruiteException(String message, Throwable cause) {
		super(message, cause);
		// TODO Stub du constructeur g�n�r� automatiquement
	}

}
