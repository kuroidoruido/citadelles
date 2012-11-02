package carte.batiment;

import carte.Famille;

/**
 * Classe modélisant les quartiers spéciaux du jeu, les merveilles.
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 24 oct. 2012
 *
 */
public class Merveille extends Batiment {
	
	protected String texteCapacite;
	protected int supplementPoint;
	
	/**
	 * Constructeur de Merveille
	 * @param nom le nom de la merveille
	 * @param famille la famille de la merveille
	 * @param prix le prix de la merveille
	 * @param texteCapacite le texte décrivant la capacité de la merveille
	 * @param supplementPoint le supplément de points apporté par la merveille
	 */
	public Merveille(String nom, Famille famille, int prix,
			String texteCapacite, int supplementPoint) {
		super(nom, famille, prix);
		this.texteCapacite = texteCapacite;
		this.supplementPoint = supplementPoint;
	}
	
	/**
	 * Méthode permettant de récupérer le texte de la carte
	 * @return le texte décrivant la capacité de la merveille
	 */
	public String getTexteCapacite() {
		return texteCapacite;
	}
	
	/**
	 * Méthode permettant de connaître le nombre de points ajoutés par la merveille
	 * @return la valeur en points de la merveille
	 */
	public int calculPoint() {
		return super.getPrix()+supplementPoint;
	}
}
