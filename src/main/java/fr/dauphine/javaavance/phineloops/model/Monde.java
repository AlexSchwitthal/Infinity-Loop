package fr.dauphine.javaavance.phineloops.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import fr.dauphine.javaavance.phineloops.view.AffichageMondeConsole;

public class Monde {
	private Grille g;

	public Monde() {

	}

	public Monde(Grille g) {
		this.g = g;
	}

	public Grille GetGrille() {
		return this.g;
	}

	public void afficher() {
		new AffichageMondeConsole(this).afficher();
	}

	public boolean solve(int nbThreads) {
		return this.g.solve(nbThreads);
	}
	
	public void setGrille(Grille g) {
		this.g = g;
	}
	
	/*
	 * Instancie la grille de cette instance à partir des données du fichier texte dont le chemin est donné en paramètre
	 */
	public void importGrille(String inputFile) {
		File file = new File(inputFile);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			int hauteur = Integer.parseInt(reader.readLine());			
			int largeur = Integer.parseInt(reader.readLine());			
			Grille grille = new Grille4D(hauteur,largeur);
			for (int i = 0; i<hauteur; i++) {
				for (int j = 0; j<largeur; j++) {
					String ligne = reader.readLine();
					String[] parts = ligne.split(" ");
					int numPiece = Integer.parseInt(parts[0]); 
					int orientation = Integer.parseInt(parts[1]); 
					grille.affecterPiece(i, j, numPiece, orientation);					
				}
			}			
			reader.close();
			this.g = grille;
		} catch (NumberFormatException | IOException e) {			
			e.printStackTrace();
		}
	}
	
	/*
	 * Exporte la grille de cette instance vers un fichier texte créé suivant le chemin donné en paramètre
	 */
	public void export(String outputFile) {
		File output;
		if (outputFile.contains(".dat")) {
			output = new File(outputFile);		
		}
		else {
			output = new File(outputFile + ".dat");		
		}
		try {
			output.createNewFile();			
			FileWriter writer = new FileWriter(output);
			writer.write(this.g.getHauteur() + System.getProperty("line.separator"));
			writer.write(this.g.getLargeur() + System.getProperty("line.separator"));
			Piece[][] tab = this.g.getGrille();
			for (int i = 0;i<this.g.getHauteur();i++) {
				for (int j = 0;j<this.g.getLargeur();j++) {
					writer.write(tab[i][j].getNumPiece() + " " + tab[i][j].getOrientation() + System.getProperty("line.separator"));
				}
			}
			writer.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
	}
}
