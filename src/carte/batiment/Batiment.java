package carte.batiment;

import carte.Carte;
import carte.Famille;

/**
 * Classe modélisant les différents bâtiments constructibles du jeu.
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 24 oct. 2012
 *
 */
public abstract class Batiment extends Carte implements Comparable<Batiment> {
	
	protected int prix;

	/**
	 * Constructeur de Batiment
	 * @param nom le nom du bâtiment
	 * @param famille la famille du bâtiment
	 * @param prix le prix de construction du bâtiment
	 */
	public Batiment(String nom, Famille famille, int prix) {
		super(nom, famille);
		this.prix = prix;
	}

	/**
	 * Méthode permettant de récupérer le prix du bâtiment
	 * @return le prix de construction du Batiment
	 */
	public int getPrix() {
		return prix;
	}
	
	/**
	 * Méthode permettant de récupérer le nombre de points donnés par le bâtiment
	 * @return la valeur en points du bâtiment, soit son prix de construction.
	 */
	public int calculPoints() {
		return prix;
	}
	
	/**
	 * Méthode permettant de comparer deux quartiers
	 * @return un entier représentant la différence entre les deux quartiers (0 si identiques)
	 */
	public int compareTo(Batiment b) {
		int retour = 0;
		if(this.famille.compareTo(b.famille) == 0) //Si les deux bâtiments appartiennent à la même famille, on les compare selon leur prix
		{
			retour = this.prix - b.prix;
		}
		else //Si les deux bâtiments coûtent le même prix, on les compare selon leur famille
		{
			retour = this.famille.compareTo(b.famille);
		}
		return retour;
	}
	
	/**
	 * Méthode permettant d'afficher l'état du quartier
	 * @return une chaîne contenant les attributs du Quartier
	 */
	public String toString() {
		String retour = nom+"(";
		if(famille != null)
		{
			retour += famille+",";
		}
		retour += prix+")";
		return retour;
	}
	
}
