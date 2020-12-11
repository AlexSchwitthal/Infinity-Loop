package fr.dauphine.javaavance.phineloops.model;

import java.util.ArrayList;
import java.util.Random;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.ParallelPortfolio;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.BoolVar;

import fr.dauphine.javaavance.phineloops.model.Piece4D.Piece4DType;

public class Grille4D extends Grille {

	public Grille4D(int x, int y) {
		super(x, y);
		this.grille = new Piece4D[x][y];
	}
	
	public Grille4D() {
		super();
	}
	
	public Grille4D(int x, int y, Piece[][] grille) {
		super(x, y, grille);
	}

	@Override
	// Méthode qui remplit la grille selon les conditions
	public void generer() {

		Random r = new Random();
		// Début du processus de sélection des pièces, position par position (i,j)
		for (int i = 0; i < this.hauteur; i++) {
			for (int j = 0; j < this.largeur; j++) {
				ArrayList<Piece4D> piecesPossibles = this.piecesPossibles(i, j);

				// verification pièce au nord
				if (i > 0) {
					// si la pièce au nord pointe vers le sud
					// alors la pièce courante devra
					if (this.grille[i - 1][j].getConnexionsPossibles().contains("SUD")) {
						piecesPossibles.retainAll(Piece4D.getPiecesDirection("NORD"));
					} 
					// sinon, la pièce ne devra PAS pointer vers le nord
					else {
						piecesPossibles.removeAll(Piece4D.getPiecesDirection("NORD"));
					}
				}

				// vérification pièce à l'ouest
				if (j > 0) {
					// si la pièce à l'ouest pointe vers l'est
					// alors la pièce courante devra pointer vers l'ouest
					if (this.grille[i][j - 1].getConnexionsPossibles().contains("EST")) {
						piecesPossibles.retainAll(Piece4D.getPiecesDirection("OUEST"));
					}
					// sinon, la pièce ne devra PAS pointer vers l'ouest
					else {
						piecesPossibles.removeAll(Piece4D.getPiecesDirection("OUEST"));
					}
				}
				// fin - vérification pièce à l'ouest

				this.grille[i][j] = piecesPossibles.get(r.nextInt(piecesPossibles.size()));
			}
		}
	}

	// Méthode prenant en paramètre les coordonnées x et y d'une case et renvoyant
	// les pièces autorisées selon la position
	public ArrayList<Piece4D> piecesPossibles(int x, int y) {

		ArrayList<Piece4D> listePieces = Piece4D.getPieces();

		// Si l'on est sur la première ligne de la grille
		if (x == 0) {
			listePieces.removeAll(Piece4D.getPiecesDirection("NORD"));
		}
		// Si l'on est sur la dernière ligne de la grille
		else if (x == this.hauteur - 1) {
			listePieces.removeAll(Piece4D.getPiecesDirection("SUD"));
		}

		// Si l'on est sur la première colonne de la grille
		if (y == 0) {
			listePieces.removeAll(Piece4D.getPiecesDirection("OUEST"));
		}
		// Si l'on est sur la dernière colonne de la grille
		else if (y == this.largeur - 1) {
			listePieces.removeAll(Piece4D.getPiecesDirection("EST"));
		}
		return listePieces;
	}

	
	// Méthode qui initialise la grille avec des pièces vides
	public void init() {
		for (int i = 0; i < this.hauteur; i++) {
			for (int j = 0; j < this.largeur; j++) {
				this.grille[i][j] = new Piece4D(Piece4DType.PVide);
			}
		}
	}
	
	@Override
	public int getNbErreursPiece(int x, int y) {
		int totalErreursPiece = 0;
		Piece p = this.grille[x][y];

		// Nord
		if (p.getConnexionsPossibles().contains("NORD")) {
			if(x != 0) {
				if(!(this.grille[x-1][y].getConnexionsPossibles().contains("SUD"))) {
					totalErreursPiece++;
				}
			}
			else {
				totalErreursPiece++;
			}
		}
		
		// Sud
		if (p.getConnexionsPossibles().contains("SUD")) {
			if(x < this.hauteur-1) {
				if(!(this.grille[x+1][y].getConnexionsPossibles().contains("NORD"))) {
					totalErreursPiece++;
				}
			}
			else {
				totalErreursPiece++;
			}
		}
		
		// Est
		if (p.getConnexionsPossibles().contains("EST")) {
			if(y < this.largeur-1) {
				if(!(this.grille[x][y+1].getConnexionsPossibles().contains("OUEST"))) {
					totalErreursPiece++;
				}
			}
			else {
				totalErreursPiece++;
			}
		}
		
		// Ouest
		if (p.getConnexionsPossibles().contains("OUEST")) {
			if(y != 0) {
				if(!(this.grille[x][y-1].getConnexionsPossibles().contains("EST"))) {
					totalErreursPiece++;
				}
			}
			else {
				totalErreursPiece++;
			}
		}
		return totalErreursPiece;
	}
	
	// Méthode affectant une pièce selon son numéro et son orientation à l'emplacement (ligne,colonne)
	public void affecterPiece(int ligne, int colonne, int numPiece, int orientation) {
		this.grille[ligne][colonne] = Piece4D.getPieceFromNumAndOrientation(numPiece, orientation);
	}
	
	
	@Override
	public boolean solve(int nbThreads) {
		
		if(!this.estValide()) {
			
			boolean result;
			Model model;
			if(nbThreads == 1) {
				model = genererModel();
				Solver s = model.getSolver();
				s.limitTime("60s");
				result = s.solve();
				//result = model.getSolver().solve();
			}
			else {
				ParallelPortfolio portfolio = new ParallelPortfolio();
				/* chaque modèle est traité dans un thread séparé */
				for(int i = 0; i < nbThreads;i++){
					Model m = genererModel();
					m.getSolver().limitTime("60s");
				    portfolio.addModel(m);
				}
				result = portfolio.solve();
				if(result) {
					model = portfolio.getBestModel(); 
				}
				else {
					return false;
				}
			}
			
			
			/* début MAJ de la grille */
			if(result) {				
				String s = "";
				int i = 0;
				int j = 0;
				for(BoolVar b : model.retrieveBoolVars())  {
					if(b.getName().equals("HAUT") || b.getName().equals("BAS") || b.getName().equals("DROITE") || b.getName().equals("GAUCHE")) {
						s += b.getValue();
						if(s.length() == 4) {
							Piece p = new Piece4D(s);
							this.affecterPiece(i, j, p);	
							j++;
							s = "";
							if(j > this.getHauteur() - 1) {
								j = 0;
								i++;
							}
						}
					}	
				}
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return true;
		}
	}
	
	@Override
	protected Model genererModel() {
		Model model = new Model("Choco-solver");
		
		/* début de déclaration des contraintes de domaine */
		BoolVar[][][] tb = new BoolVar[this.getHauteur()][this.getLargeur()][4];
		for (int i = 0; i < this.getHauteur(); i++) {
			for (int j = 0; j < this.getLargeur(); j++) {
				
				/* si l'une des pièces ce trouve sur une des extrémités du bord
				 * alors elle ne doit pas pointer vers l'extérieur
				 */
				tb[i][j][0]  = ((i == 0) ? 
						model.boolVar("HAUT", false) : model.boolVar("HAUT"));
				
				tb[i][j][1]  = ((i == this.getHauteur() - 1) ? 
						model.boolVar("BAS", false) : model.boolVar("BAS"));
				
				tb[i][j][2]  = ((j == this.getLargeur() - 1) ? 
						model.boolVar("DROITE", false) : model.boolVar("DROITE"));
				
				tb[i][j][3]  = ((j == 0) ? 
						model.boolVar("GAUCHE", false) : model.boolVar("GAUCHE"));
				
				
				/* condition pour chacune des pièces */
				switch(this.grille[i][j].getNumPiece()) {
				case 1:
					// Piece : ╹ ou ╻ ou ╺ ou ╸
					model.sum(tb[i][j], "=", 1).post();
					break;
				case 2:
					// Piece : │ ou ─
					model.sum(tb[i][j], "=", 2).post();
					model.arithm(tb[i][j][0], "=", tb[i][j][1]).post();
					model.arithm(tb[i][j][2], "=", tb[i][j][3]).post();
					break;
				case 3:
					// Piece : ┴ ou ├ ou ┬ ou ┤
					model.sum(tb[i][j], "=", 3).post();
					break;
				case 4:
					// Piece : ┼
					model.sum(tb[i][j], "=", 4).post();
					break;
				case 5:
					// Piece : ╰ ou ╭ ou ╮ ou ╯
					model.sum(tb[i][j], "=", 2).post();
					model.arithm(tb[i][j][0], "!=", tb[i][j][1]).post();
					model.arithm(tb[i][j][2], "!=", tb[i][j][3]).post();
					break;
				case 0:
					// Piece Vide
					model.sum(tb[i][j], "=", 0).post();
					break;
				}
			}
		}
		/* fin de déclaration des contraintes de domaine */
		
		
		/* condition pour chacune des pièces entres elles */
		for (int i = 0; i < this.getHauteur(); i++) {
			for (int j = 0; j < this.getLargeur(); j++) {
				
				if((i!=0)) {
					model.arithm(tb[i][j][0], "=", tb[i-1][j][1]).post();
				}
				
				if((j!=0)) {
					model.arithm(tb[i][j][3], "=", tb[i][j-1][2]).post();
				}
			}
		}
		return model;
	}
}
