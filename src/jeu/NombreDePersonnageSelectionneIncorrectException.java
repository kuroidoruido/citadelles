/**
 * 
 */
package jeu;

/**
 * Cette exception surviet lorsque le nombre de personnages s�lectionn�s n'est pas valide
 * Le nombre de personnages s�lectionn�s doit �tre �gal au nombre de joueurs plus un.
 * @author Anthony Pena
 *
 */
@SuppressWarnings("serial")
public class NombreDePersonnageSelectionneIncorrectException extends Exception {

	/**
	 * 
	 */
	public NombreDePersonnageSelectionneIncorrectException() {
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param message
	 */
	public NombreDePersonnageSelectionneIncorrectException(String message) {
		super(message);
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param cause
	 */
	public NombreDePersonnageSelectionneIncorrectException(Throwable cause) {
		super(cause);
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NombreDePersonnageSelectionneIncorrectException(String message,
			Throwable cause) {
		super(message, cause);
		// TODO Stub du constructeur généré automatiquement
	}

}
