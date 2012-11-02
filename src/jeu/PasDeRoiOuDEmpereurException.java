/**
 * 
 */
package jeu;

/**
 * Cette exception survient lorsque la liste de personnages s�lectionn�s ne contient ni Roi ni Empereur.
 * Il faut au moins un des deux.
 * @author Anthony Pena
 *
 */
@SuppressWarnings("serial")
public class PasDeRoiOuDEmpereurException extends Exception {

	/**
	 * 
	 */
	public PasDeRoiOuDEmpereurException() {
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param message
	 */
	public PasDeRoiOuDEmpereurException(String message) {
		super(message);
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param cause
	 */
	public PasDeRoiOuDEmpereurException(Throwable cause) {
		super(cause);
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PasDeRoiOuDEmpereurException(String message, Throwable cause) {
		super(message, cause);
		// TODO Stub du constructeur généré automatiquement
	}

}
