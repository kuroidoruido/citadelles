package carte;

import java.awt.Color;

/**
 * Classe mod�lisant les diff�rentes familles de personnages et quartiers du jeu.
 * @author Bauchet Cl�ment
 * @author Pena Anthony
 * @version 24 oct. 2012
 *
 */
public class Famille implements Comparable<Famille> {
	
	private static int nbFamille = 0; //On garde une trace du nombre de familles existantes
	private String nom;
	private Color couleur;
	
	/**
	 * Constructeur de Famille
	 * @param nom le nom de la famille
	 * @param couleur la couleur attribu�e � la famille
	 */
	public Famille(String nom, Color couleur) {
		this.nom = nom;
		this.couleur = couleur;
		nbFamille++; //A la cr�ation d'une famille, on incr�mente le nombre de familles existantes
	}

	/**
	 * M�thode permettant de conna�tre le nombre de familles existantes
	 * @return le nombre de familles
	 */
	public static int getNbFamille() {
		return nbFamille;
	}

	/**
	 * M�thode permettant de r�cup�rer le nom de la famille
	 * @return le nom de la famille
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * M�thode permettant de r�cup�rer la couleur de la famille
	 * @return la couleur attribu�e � la famille
	 */
	public Color getCouleur() {
		return couleur;
	}
	
	/**
	 * La m�thode equals()
	 * @param f la famille � comparer
	 * @return true si les deux familles sont identiques, false sinon
	 */
	public boolean equals(Famille f) {
		return (this.nom == f.nom && this.couleur == f.couleur);
	}

	public int compareTo(Famille f) {
		return this.nom.compareTo(f.nom);
	}
	
	public String toString() {
		return nom;
	}
}
