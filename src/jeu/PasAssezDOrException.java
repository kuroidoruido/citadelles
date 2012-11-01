package jeu;

/**
 * Cette exception survient lorsqu'on essaie d'enlever à un joueur plus d'or qu'il n'en possède.
 * Par exemple, lorsqu'un joueur essaie de construire un quartier trop cher pour lui...
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 25 oct. 2012
 *
 */
@SuppressWarnings("serial")
public class PasAssezDOrException extends Exception {

	/**
	 * 
	 */
	public PasAssezDOrException() {
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param message
	 */
	public PasAssezDOrException(String message) {
		super(message);
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param cause
	 */
	public PasAssezDOrException(Throwable cause) {
		super(cause);
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PasAssezDOrException(String message, Throwable cause) {
		super(message, cause);
		// TODO Stub du constructeur généré automatiquement
	}

}
