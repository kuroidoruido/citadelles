package jeu;

/**
 * Cette exception survient si un joueur essaie de construire un quartier qui n'est pas dans sa main.
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 25 oct. 2012
 *
 */
@SuppressWarnings("serial")
public class QuartierPasDansLaMainException extends Exception {

	/**
	 * 
	 */
	public QuartierPasDansLaMainException() {
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param message
	 */
	public QuartierPasDansLaMainException(String message) {
		super(message);
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param cause
	 */
	public QuartierPasDansLaMainException(Throwable cause) {
		super(cause);
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param message
	 * @param cause
	 */
	public QuartierPasDansLaMainException(String message, Throwable cause) {
		super(message, cause);
		// TODO Stub du constructeur généré automatiquement
	}

}
