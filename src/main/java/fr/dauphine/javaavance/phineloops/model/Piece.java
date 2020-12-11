package fr.dauphine.javaavance.phineloops.model;

import java.util.ArrayList;

public abstract class Piece {

	protected abstract Piece pieceAleatoire();
	protected abstract ArrayList<String> getConnexionsPossibles();
	public abstract Piece rotation();
	public abstract String getSymbole();
	public abstract int getId();
	public abstract int getNumPiece();
	public abstract int getOrientation();
	public abstract ArrayList<Piece> getPositionPossiblesPieces();
	
	public Piece() {
		
	}
	
	public Piece(String s) {
		
	}
	
	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}
}
