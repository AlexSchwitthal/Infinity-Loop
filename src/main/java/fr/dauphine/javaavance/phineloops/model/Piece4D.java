package fr.dauphine.javaavance.phineloops.model;

import java.util.ArrayList;
import java.util.Random;

public class Piece4D extends Piece {

	private Piece4DType typePiece;

	public Piece4D(Piece4DType typePiece) {
		this.typePiece = typePiece;
	}
	
	/***
	 * 
	 * @param String contenant 4 caractères, uniquement des 0 et des 1, chaque caractères correspondant à une direction de la pièce.
	 * @return la Pièce ayant les directions indiqués par la String passé en paramètres. 
	 */
	public Piece4D(String s) {
		loop:
		for(Piece4DType p : Piece4DType.values()) {
			String s2 = "";
			s2 += ((p.connexionsPossibles.contains("NORD")) ? "1" : "0");
			s2 += ((p.connexionsPossibles.contains("SUD")) ? "1" : "0");
			s2 += ((p.connexionsPossibles.contains("EST")) ? "1" : "0");
			s2 += ((p.connexionsPossibles.contains("OUEST")) ? "1" : "0");
			if(s.equals(s2)) {
				this.typePiece = p;
				break loop;
			}
		}
	}
	
	public Piece4DType getTypePiece() {
		return this.typePiece;
	}
	
	public int getId() {
		return this.typePiece.id;
	}

	public int getNumPiece() {
		return this.typePiece.numPiece;
	}

	public int getOrientation() {
		return this.typePiece.orientation;
	}

	public String getSymbole() {
		return this.typePiece.symbole;
	}

	public ArrayList<String> getConnexionsPossibles() {
		return this.typePiece.connexionsPossibles;
	}

	/***
	 * 
	 * @return ArrayList de toutes les pièces différentes possibles
	 */
	public static ArrayList<Piece4D> getPieces() {
		Piece4DType tab[] = Piece4DType.values();
		ArrayList<Piece4D> tabAll = new ArrayList<>();
		for (int i = 0; i < tab.length; i++) {
			tabAll.add(new Piece4D(tab[i]));
		}
		return tabAll;
	}

	/***
	 * 
	 * @param direction : String représentant une direction, valeurs possible : "NORD", "SUD", "EST", "OUEST"
	 * @return ArrayList de toutes les pièces ayant une connnexion dans la direction de la string passé en paramètre.
	 */
	public static ArrayList<Piece4D> getPiecesDirection(String direction) {
		ArrayList<Piece4D> Pieces = new ArrayList<>();
		for(Piece4DType p : Piece4DType.values()) {
			if(p.connexionsPossibles.contains(direction)) {
				Pieces.add(new Piece4D(p));
			}
		}
		return Pieces;
	}
	
	

	/***
	 * 
	 * @return Une Piece4D aléatoire 
	 */
	@Override
	public Piece4D pieceAleatoire() {
		Random random = new Random();
		return new Piece4D(Piece4DType.values()[random.nextInt(Piece4DType.values().length)]);
	}

	/***
	 * 
	 * @return Une Piece4D égal à la Piece4D courante, mais avec une rotation de 90°
	 */
	@Override
	public Piece4D rotation() {
		int i = this.typePiece.id + 1;
		Piece4DType p = null;
		if (i < Piece4DType.values().length) {
			p = Piece4DType.values()[i];
		}

		// si la piece suivante dans la liste énuméré est null (fin de la liste)
		// ou si il ne s'agit pas d'une pièce n'étant pas du même type
		if ((p == null) || (p.numPiece != this.typePiece.numPiece)) {
			p = this.typePiece;
			// boucle pour revenir en arrière, jusqu'à trouver le premier élement de la
			// liste énuméré ayant le même type que la pièce courante
			while (p.numPiece == this.typePiece.numPiece) {
				i = p.id - 1;
				if (i < 0) {
					return new Piece4D(Piece4DType.values()[0]);
				}
				p = Piece4DType.values()[i];
			}
			return new Piece4D(Piece4DType.values()[i + 1]);
		}
		// sinon, retourne la pièce suivante
		else {
			return new Piece4D(p);
		}
	}

	// Méthode qui prend en paramètre le numéro de la pièce et son orientation et
	// qui retourne la pièce correspondante
	public static Piece4D getPieceFromNumAndOrientation(int num, int orientation) {
		Piece4DType tab[] = Piece4DType.values();
		for (int i = 0; i < tab.length; i++) {
			if (num == tab[i].numPiece && orientation == tab[i].orientation) {
				return new Piece4D(tab[i]);
			}
		}
		return new Piece4D(Piece4DType.PVide);
	}

	
	public Piece4DType getPiece4DType() {
		return this.typePiece;
	}

	@Override
	public String toString() {
		return this.typePiece.symbole;
	}
	
	@Override
	public boolean equals(Object o) {
		Piece4D p = (Piece4D) o;
		return (this.typePiece == p.typePiece);
	}
	
	@Override
	public ArrayList<Piece> getPositionPossiblesPieces() {
		ArrayList<Piece> listePieces = new ArrayList<>();
		for(Piece4DType piece : Piece4DType.values()) {
			if(piece.getNumPiece() == this.getNumPiece()) {
				listePieces.add(new Piece4D(piece));
			}
		}
		return listePieces;
	}
	
	public enum Piece4DType {
		PVide(0, 0, 0, " ", " "),
		P1N(1, 1, 0, "╹", "NORD"), 
		P1E(2, 1, 1, "╺", "EST"), 
		P1S(3, 1, 2, "╻", "SUD"),
		P1O(4, 1, 3, "╸", "OUEST"), 
		P2NS(5, 2, 0, "│", "NORD,SUD"), 
		P2EO(6, 2, 1, "─", "EST,OUEST"),
		P3NEO(7, 3, 0, "┴", "NORD,EST,OUEST"),
		P3NSE(8, 3, 1, "├", "NORD,SUD,EST"),
		P3ESO(9, 3, 2, "┬", "EST,SUD,OUEST"), 
		P3NSO(10, 3, 3, "┤", "NORD,SUD,OUEST"),
		P4(11, 4, 0, "┼", "NORD,SUD,EST,OUEST"), 
		PLNE(12, 5, 0, "╰", "NORD,EST"), 
		PLES(13, 5, 1, "╭", "EST,SUD"),
		PLSO(14, 5, 2, "╮", "SUD,OUEST"), 
		PLON(15, 5, 3, "╯", "OUEST,NORD");

		private int id;
		private int numPiece;
		private int orientation;
		private String symbole;
		private ArrayList<String> connexionsPossibles;

		private Piece4DType(int id, int numPiece, int orientation, String symbole, String connexions) {
			this.id = id;
			this.numPiece = numPiece;
			this.orientation = orientation;
			this.symbole = symbole;
			this.connexionsPossibles = new ArrayList<String>();
			String[] directions = connexions.split(",");
			for (int i = 0; i < directions.length; i++) {
				this.connexionsPossibles.add(directions[i]);
			}
		}

		public int getNumPiece() {
			return this.numPiece;
		}
	}
}
