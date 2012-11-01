/**
 * 
 */
package jeu;

/**
 * Cette exception survient lorsque la liste des personnages sélectionnés contient plusieurs personnages dont l'ordre de passage est le même.
 * On ne peut pas faire jouer deux personnages en même temps !
 * @author Anthony Pena
 *
 */
@SuppressWarnings("serial")
public class PlusieursPersonnageDuMemeOrdreException extends Exception {

	/**
	 * 
	 */
	public PlusieursPersonnageDuMemeOrdreException() {
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param message
	 */
	public PlusieursPersonnageDuMemeOrdreException(String message) {
		super(message);
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param cause
	 */
	public PlusieursPersonnageDuMemeOrdreException(Throwable cause) {
		super(cause);
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PlusieursPersonnageDuMemeOrdreException(String message,
			Throwable cause) {
		super(message, cause);
		// TODO Stub du constructeur généré automatiquement
	}

}
