/**
 * 
 */
package jeu;

import java.util.Iterator;

/**
 * @author anthony
 *
 */
public class JoueurOrdrePersoIterator implements Iterator<Joueur> {

	private Partie partie;
	private Joueur joueurCourant;
	/**
	 * 
	 */
	public JoueurOrdrePersoIterator(Partie p) {
		partie = p;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		boolean suivant = false;
		if(joueurCourant == null && !partie.getListeJoueur().isEmpty())
		{
			suivant = true;
		}
		else
		{
			for(Joueur j : partie.getListeJoueur())
			{
				if(j.getPerso().compareTo(joueurCourant.getPerso()) > 0)
				{
					suivant = true;
				}
			}
		}
		return suivant;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public Joueur next() {
		Joueur possibleSuivant = null;
		if(joueurCourant != null)
		{
			for(Joueur j : partie.getListeJoueur())
			{
				if(j.getPerso().compareTo(joueurCourant.getPerso()) > 0)
				{
					if(possibleSuivant == null)
					{
						possibleSuivant = j;
					}
					else
					{
						if(j.getPerso().compareTo(possibleSuivant.getPerso()) < 0)
						{
							possibleSuivant = j;
						}
					}
				}
			}
		}
		else
		{
			for(Joueur j : partie.getListeJoueur())
			{
				if(possibleSuivant == null)
				{
					possibleSuivant = j;
				}
				else
				{
					if(j.getPerso().compareTo(possibleSuivant.getPerso()) < 0)
					{
						possibleSuivant = j;
					}
				}
			}
		}
		joueurCourant = possibleSuivant;
		return joueurCourant;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		// TODO Stub de la méthode généré automatiquement

	}

}
