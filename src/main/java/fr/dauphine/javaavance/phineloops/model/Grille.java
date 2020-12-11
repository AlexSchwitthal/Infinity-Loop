package fr.dauphine.javaavance.phineloops.model;

import java.util.Random;

import org.chocosolver.solver.Model;

import fr.dauphine.javaavance.phineloops.view.AffichageGrille;

public abstract class Grille {
	protected int hauteur;
	protected int largeur;
	protected Piece grille[][];
	protected final double probaRotation = 0.60;

	public int getHauteur() {
		return this.hauteur;
	}

	public int getLargeur() {
		return this.largeur;
	}

	public Piece[][] getGrille() {
		return this.grille;
	}

	public Grille(int x, int y) {
		this.hauteur = x;
		this.largeur = y;
	}

	public Grille() {

	}

	public Grille(int x, int y, Piece[][] grille) {
		this.hauteur = x;
		this.largeur = y;
		this.grille = grille;
	}

	// Méthode qui affiche la grille avec un appel à la vue AffichageGrille
	public void afficher() {
		new AffichageGrille(this).afficher();
	}

	public abstract void generer();
	
	
	/*
	 * Mélange aléatoirement la grille en fonction de l'attribut probaRotation
	 */
	public void melangerGrille() {
		Random r = new Random();
		for (int i = 0; i < this.hauteur; i++) {
			for (int j = 0; j < this.largeur; j++) {
				double proba = r.nextDouble();
				if (proba <= this.probaRotation) {
					int nbRotations = r.nextInt(4) + 1;
					for (int k = 0; k < nbRotations; k++) {
						this.grille[i][j] = this.grille[i][j].rotation();
					}
				}
			}
		}
	}

	public abstract int getNbErreursPiece(int x, int y);

	public int getNbErreurs() {
		int totalErreurs = 0;
		for (int i = 0; i < this.hauteur; i++) {
			for (int j = 0; j < this.largeur; j++) {
				totalErreurs += this.getNbErreursPiece(i, j);
			}
		}
		return totalErreurs;
	}

	public boolean estValide() {
		if (this.getNbErreurs() == 0) {
			return true;
		} 
		else {
			return false;
		}
	}

	public void affecterPiece(int ligne, int colonne, Piece p) {
		this.grille[ligne][colonne] = p;
	}

	public abstract void affecterPiece(int i, int j, int numPiece, int orientation);

	public abstract boolean solve(int nbThreads);

	protected abstract Model genererModel();
}
