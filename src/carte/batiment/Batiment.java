package carte.batiment;

import carte.Carte;
import carte.Famille;

/**
 * Classe mod�lisant les diff�rents b�timents constructibles du jeu.
 * @author Bauchet Cl�ment
 * @author Pena Anthony
 * @version 24 oct. 2012
 *
 */
public abstract class Batiment extends Carte implements Comparable<Batiment> {
	
	protected int prix;

	/**
	 * Constructeur de Batiment
	 * @param nom le nom du b�timent
	 * @param famille la famille du b�timent
	 * @param prix le prix de construction du b�timent
	 */
	public Batiment(String nom, Famille famille, int prix) {
		super(nom, famille);
		this.prix = prix;
	}

	/**
	 * M�thode permettant de r�cup�rer le prix du b�timent
	 * @return le prix de construction du Batiment
	 */
	public int getPrix() {
		return prix;
	}
	
	/**
	 * M�thode permettant de r�cup�rer le nombre de points donn�s par le b�timent
	 * @return la valeur en points du b�timent, soit son prix de construction.
	 */
	public int calculPoints() {
		return prix;
	}
	
	/**
	 * M�thode permettant de comparer deux quartiers
	 * @return un entier repr�sentant la diff�rence entre les deux quartiers (0 si identiques)
	 */
	public int compareTo(Batiment b) {
		int retour = 0;
		if(this.famille.compareTo(b.famille) == 0) //Si les deux b�timents appartiennent � la m�me famille, on les compare selon leur prix
		{
			retour = this.prix - b.prix;
		}
		else //Si les deux b�timents co�tent le m�me prix, on les compare selon leur famille
		{
			retour = this.famille.compareTo(b.famille);
		}
		return retour;
	}
	
	/**
	 * M�thode permettant d'afficher l'�tat du quartier
	 * @return une cha�ne contenant les attributs du Quartier
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
