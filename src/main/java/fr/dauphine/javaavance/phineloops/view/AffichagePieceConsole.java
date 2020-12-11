package fr.dauphine.javaavance.phineloops.view;

import fr.dauphine.javaavance.phineloops.model.Piece;

@SuppressWarnings("serial")

// Classe permettant l'affichage des pièces en mode console
public class AffichagePieceConsole extends Affichage {
	
	private Piece p;
	
        // Constructeur de la classe AffichagePieceConsole prenant en paramètre une pièce
	public AffichagePieceConsole(Piece p) {
		this.p = p;
	}

        //Redéfinition de la méthode afficher() de la classe mère Affichage
	@Override
	public void afficher() {
		System.out.print(p.getSymbole());
	}
}
