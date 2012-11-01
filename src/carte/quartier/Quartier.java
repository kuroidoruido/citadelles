package carte.quartier;

import carte.Carte;
import carte.Famille;

/**
 * Classe modélisant les différents quartiers constructibles du jeu.
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 24 oct. 2012
 *
 */
public class Quartier extends Carte implements Comparable<Quartier> {
	
	protected int prix;

	/**
	 * Constructeur de Quartier
	 * @param nom le nom du quartier
	 * @param famille la famille du quatier
	 * @param prix le prix de construction du quartier
	 */
	public Quartier(String nom, Famille famille, int prix) {
		super(nom, famille);
		this.prix = prix;
	}
	
	/**
	 * Constructeur de Quartier n'ayant pas de famille
	 * @param nom le nom du quartier
	 * @param prix le prix de construction du quartier
	 */
	public Quartier(String nom, int prix) {
		this(nom, null, prix);
	}

	/**
	 * Méthode permettant de récupérer le prix du quartier
	 * @return le prix de construction du quartier
	 */
	public int getPrix() {
		return prix;
	}
	
	/**
	 * Méthode permettant de récupérer le nombre de points donnés par le quartier
	 * @return la valeur en points du quartier, soit son prix de construction.
	 */
	public int calculPoints() {
		return prix;
	}
	
	/**
	 * Méthode permettant de comparer deux quartiers
	 * @return un entier représentant la différence entre les deux quartiers (0 si identiques)
	 */
	public int compareTo(Quartier q) {
		int retour = 0;
		if(this.famille.compareTo(q.famille) == 0) //Si les deux quartiers appartiennent à la même famille, on les compare selon leur prix
		{
			retour = this.prix - q.prix;
		}
		else //Si les deux quartiers coûtent le même prix, on les compare selon leur famille
		{
			retour = this.famille.compareTo(q.famille);
		}
		return retour;
	}
	
	
	/**
	 * Méthode permettant d'afficher l'état du quartier
	 * @return une chaîne contenant les attributs du Quartier
	 */
	public String toString() {
		String retour = nom+"(";
		if(famille != null) //On n'affiche la famille que si le quartier en a une
		{
			retour += famille+",";
		}
		retour += prix+")";
		return retour;
	}
	
}
