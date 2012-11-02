package jeu;

/**
 * Cette exception survient si un joueur essaie de construire un b�timent qui n'est pas dans sa main.
 * @author Bauchet Cl�ment
 * @author Pena Anthony
 * @version 25 oct. 2012
 *
 */
@SuppressWarnings("serial")
public class BatimentPasDansLaMainException extends Exception {

	/**
	 * 
	 */
	public BatimentPasDansLaMainException() {
		// TODO Stub du constructeur g�n�r� automatiquement
	}

	/**
	 * @param message
	 */
	public BatimentPasDansLaMainException(String message) {
		super(message);
		// TODO Stub du constructeur g�n�r� automatiquement
	}

	/**
	 * @param cause
	 */
	public BatimentPasDansLaMainException(Throwable cause) {
		super(cause);
		// TODO Stub du constructeur g�n�r� automatiquement
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BatimentPasDansLaMainException(String message, Throwable cause) {
		super(message, cause);
		// TODO Stub du constructeur g�n�r� automatiquement
	}

}
