package carte;

import java.awt.Color;

/**
 * @author Bauchet Clément
 * @author Pena Anthony
 * @version 24 oct. 2012
 *
 */
public class Famille {
	
	private static int nbFamille = 0;
	private String nom;
	private Color couleur;
	
	/**
	 * @param nom le nom de la famille
	 * @param couleur la couleur attribuée à la famille
	 */
	public Famille(String nom, Color couleur) {
		this.nom = nom;
		this.couleur = couleur;
		nbFamille++;
	}

	/**
	 * @return le nbFamille
	 */
	public static int getNbFamille() {
		return nbFamille;
	}

	/**
	 * @return le nom de la famille
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return la couleur attribuée à la famille
	 */
	public Color getCouleur() {
		return couleur;
	}
	
	public boolean equals(Famille f) {
		return (this.nom == f.nom && this.couleur == f.couleur);
	}
}
