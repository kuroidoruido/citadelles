package jeu;

/**
 * Cette exception survient si un joueur essaie de construire un b�timent qu'il a d�j� construit.
 * Dans cette version, on n'autorise pas les joueurs � construire deux fois le m�me b�timent.
 * @author Bauchet Cl�ment
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
		// TODO Stub du constructeur g�n�r� automatiquement
	}

	/**
	 * @param message
	 */
	public BatimentDejaConstruiteException(String message) {
		super(message);
		// TODO Stub du constructeur g�n�r� automatiquement
	}

	/**
	 * @param cause
	 */
	public BatimentDejaConstruiteException(Throwable cause) {
		super(cause);
		// TODO Stub du constructeur g�n�r� automatiquement
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BatimentDejaConstruiteException(String message, Throwable cause) {
		super(message, cause);
		// TODO Stub du constructeur g�n�r� automatiquement
	}

}
