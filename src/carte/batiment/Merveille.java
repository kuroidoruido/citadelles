package carte.batiment;

import carte.Famille;

/**
 * Classe mod�lisant les quartiers sp�ciaux du jeu, les merveilles.
 * @author Bauchet Cl�ment
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
	 * @param texteCapacite le texte d�crivant la capacit� de la merveille
	 * @param supplementPoint le suppl�ment de points apport� par la merveille
	 */
	public Merveille(String nom, Famille famille, int prix,
			String texteCapacite, int supplementPoint) {
		super(nom, famille, prix);
		this.texteCapacite = texteCapacite;
		this.supplementPoint = supplementPoint;
	}
	
	/**
	 * M�thode permettant de r�cup�rer le texte de la carte
	 * @return le texte d�crivant la capacit� de la merveille
	 */
	public String getTexteCapacite() {
		return texteCapacite;
	}
	
	/**
	 * M�thode permettant de conna�tre le nombre de points ajout�s par la merveille
	 * @return la valeur en points de la merveille
	 */
	public int calculPoint() {
		return super.getPrix()+supplementPoint;
	}
}
