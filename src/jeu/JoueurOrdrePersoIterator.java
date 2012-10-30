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
		boolean suivant = true;
		int next;
		if(joueurCourant == null)
		{
			next = 1;
		}
		else
		{
			next = joueurCourant.getPerso().getOrdre()+1;
		}
		if(next <= 1 || 9 < next)
		{
			suivant = false;
		}
		return suivant;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public Joueur next() {
		int ordreNext;
		if(joueurCourant == null)
		{
			ordreNext = 1;
		}
		else
		{
			ordreNext = joueurCourant.getPerso().getOrdre()+1;
		}
		
		for(Joueur j : partie.getListeJoueur())
		{
			if(j.getPerso().getOrdre() == ordreNext)
			{
				joueurCourant = j;
			}
		}
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
