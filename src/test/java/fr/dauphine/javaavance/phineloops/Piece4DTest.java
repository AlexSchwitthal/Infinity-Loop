package fr.dauphine.javaavance.phineloops;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.dauphine.javaavance.phineloops.model.Piece4D;
import fr.dauphine.javaavance.phineloops.model.Piece4D.Piece4DType;

public class Piece4DTest {
	
	@Test
	public void conversionDirectionPieceTest() {
		Piece4D p1 = new Piece4D("1101");
		assertEquals(p1.getPiece4DType(), Piece4DType.P3NSO);
		
		Piece4D p2 = new Piece4D("1010");
		assertEquals(p2.getPiece4DType(), Piece4DType.PLNE);
		
		Piece4D p3 = new Piece4D("0001");
		assertEquals(p3.getPiece4DType(), Piece4DType.P1O);
		
		Piece4D p4 = new Piece4D("0000");
		assertEquals(p4.getPiece4DType(), Piece4DType.PVide);
	}
	
	@Test
	public void getPositionPossiblesPiecesTest() {
		Piece4D p1 = new Piece4D(Piece4DType.P1E);
		assertEquals(p1.getPositionPossiblesPieces().size(), 4);
		
		Piece4D p2 = new Piece4D(Piece4DType.P2NS);
		assertEquals(p2.getPositionPossiblesPieces().size(), 2);
		
		Piece4D p3 = new Piece4D(Piece4DType.P4);
		assertEquals(p3.getPositionPossiblesPieces().size(), 1);
	}
	
	@Test
	public void rotationTest() {
		Piece4D p1 = new Piece4D(Piece4DType.P1N);
		Piece4D p2 = new Piece4D(Piece4DType.P1E);
		Piece4D p3 = new Piece4D(Piece4DType.P1S);
		Piece4D p4 = new Piece4D(Piece4DType.P1O);
		assertEquals(p1.rotation(), p2);
		assertEquals(p2.rotation(), p3);
		assertEquals(p4.rotation(), p1);
		
		Piece4D p5 = new Piece4D(Piece4DType.P4);
		assertEquals(p5.rotation(), p5);
		
		Piece4D p6 = new Piece4D(Piece4DType.P2NS);
		Piece4D p7 = new Piece4D(Piece4DType.P2EO);
		assertEquals(p6.rotation(), p7);
		assertEquals(p7.rotation(), p6);	
	}	
}
