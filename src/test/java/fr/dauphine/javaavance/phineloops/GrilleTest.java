package fr.dauphine.javaavance.phineloops;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.dauphine.javaavance.phineloops.model.Grille4D;

public class GrilleTest {
	
	@Test
	public void generationTest() {
		Grille4D g = new Grille4D(64, 64);
		
		g.generer();
		assertEquals(g.getNbErreurs(), 0);
		assertEquals(g.estValide(), true);
		
		g.generer();
		assertEquals(g.getNbErreurs(), 0);
		assertEquals(g.estValide(), true);
		
		g.generer();
		assertEquals(g.getNbErreurs(), 0);
		assertEquals(g.estValide(), true);
	}
}