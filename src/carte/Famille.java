package carte;

import java.awt.Color;

/**
 * Classe modélisant les différentes familles de personnages et quartiers du jeu.
 * @author Bauchet Clément
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
	 * @param couleur la couleur attribuée à la famille
	 */
	public Famille(String nom, Color couleur) {
		this.nom = nom;
		this.couleur = couleur;
		nbFamille++;
	}

	/**
	 * Méthode permettant de connaître le nombre de familles existantes
	 * @return le nombre de familles
	 */
	public static int getNbFamille() {
		return nbFamille;
	}

	/**
	 * Méthode permettant de récupérer le nom de la famille
	 * @return le nom de la famille
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Méthode permettant de récupérer la couleur de la famille
	 * @return la couleur attribuée à la famille
	 */
	public Color getCouleur() {
		return couleur;
	}
	
	/**
	 * La méthode equals()
	 * @param f la famille à comparer
	 * @return true si les deux familles sont identiques, false sinon
	 */
	public boolean equals(Famille f) {
		return (this.nom == f.nom && this.couleur == f.couleur);
	}

	/**
	 * Méthode permettant de comparer deux familles
	 * @param f la famille à comparer
	 * @return un entier représentant la différence entre les deux familles (0 si identiques)
	 */
	public int compareTo(Famille f) {
		return this.nom.compareTo(f.nom);
	}
	
	public String toString() {
		return nom;
	}
}
