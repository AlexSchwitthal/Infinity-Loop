package fr.dauphine.javaavance.phineloops.view;

import fr.dauphine.javaavance.phineloops.model.Grille;
import fr.dauphine.javaavance.phineloops.model.Piece;

@SuppressWarnings("serial")

// Classe AffichageGrille permettant l'affichage d'une grille en mode console
public class AffichageGrille extends Affichage {

	private Grille g;
	
        // Constructure de la classe AffichageGrille prenant en paramètre une grille
	public AffichageGrille(Grille g) {
		this.g = g;
	}
	
        // Redéfinition de la méthode affichage() de la classe mère Affichage
	@Override
	public void afficher() {
		for(Piece[] lignePiece : g.getGrille()) {
			for(Piece p : lignePiece) {
				new AffichagePieceConsole(p).afficher();
			}
			System.out.println();
		}	
	}
}
