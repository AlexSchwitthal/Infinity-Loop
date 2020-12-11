package fr.dauphine.javaavance.phineloops;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.dauphine.javaavance.phineloops.model.Grille4D;
import fr.dauphine.javaavance.phineloops.model.Monde;

public class ResolutionTest {
	
		
	@Test
	public void solveTest() {
		   
		Monde m = new Monde(new Grille4D(64, 64));
		m.GetGrille().generer();
		m.GetGrille().melangerGrille();
		assertEquals(m.solve(1), true);
		
		m.GetGrille().generer();
		m.GetGrille().melangerGrille();
		assertEquals(m.solve(1), true);
		
		m.GetGrille().generer();
		m.GetGrille().melangerGrille();
		assertEquals(m.solve(1), true);
	}
 	
}
