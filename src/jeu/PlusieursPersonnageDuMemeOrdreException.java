/**
 * 
 */
package jeu;

/**
 * Cette exception survient lorsque la liste des personnages s�lectionn�s contient plusieurs personnages dont l'ordre de passage est le m�me.
 * On ne peut pas faire jouer deux personnages en m�me temps !
 * @author Anthony Pena
 *
 */
@SuppressWarnings("serial")
public class PlusieursPersonnageDuMemeOrdreException extends Exception {

	/**
	 * 
	 */
	public PlusieursPersonnageDuMemeOrdreException() {
		// TODO Stub du constructeur g�n�r� automatiquement
	}

	/**
	 * @param message
	 */
	public PlusieursPersonnageDuMemeOrdreException(String message) {
		super(message);
		// TODO Stub du constructeur g�n�r� automatiquement
	}

	/**
	 * @param cause
	 */
	public PlusieursPersonnageDuMemeOrdreException(Throwable cause) {
		super(cause);
		// TODO Stub du constructeur g�n�r� automatiquement
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PlusieursPersonnageDuMemeOrdreException(String message,
			Throwable cause) {
		super(message, cause);
		// TODO Stub du constructeur g�n�r� automatiquement
	}

}
