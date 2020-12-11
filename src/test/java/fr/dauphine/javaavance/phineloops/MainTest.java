package fr.dauphine.javaavance.phineloops;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class MainTest {

	@Test
	public void checkTest() {
		boolean solved;
		
		solved = Main.check("instances/public/grid_128x128_dist.0_vflip.true_hflip.true_messedup.true_id.0.dat");
		assertEquals(solved, false);
		
		solved = Main.check("instances/public/grid_256x256_dist.0_vflip.true_hflip.true_messedup.false_id.1.dat");
		assertEquals(solved, false);
		
		solved = Main.check("instances/public/grid_64x64_dist.6_vflip.true_hflip.false_messedup.false_id.0.dat");
		assertEquals(solved, true);
	}
	
	@Test
	public void solveTest() {
		boolean solved;
		String file;
		File f;
		
		file = "test1.dat";
		solved = Main.solve("instances/public/grid_8x8_dist.0_vflip.true_hflip.false_messedup.false_id.0.dat", file, 1);
		assertEquals(solved, true);
		solved = Main.check(file);
		f = new File(file);
		f.delete();
		
		file = "test2.dat";
		solved = Main.solve("instances/public/grid_16x16_dist.6_vflip.false_hflip.false_messedup.false_id.0.dat", file, 1);
		assertEquals(solved, true);
		solved = Main.check(file);
		f = new File(file);
		f.delete();
		
		file = "test3.dat";
		solved = Main.solve("instances/public/grid_32x32_dist.6_vflip.false_hflip.true_messedup.false_id.0.dat", file, 1);
		assertEquals(solved, true);
		solved = Main.check(file);
		f = new File(file);
		f.delete();
		
		file = "test4.dat";
		solved = Main.solve("instances/public/grid_64x64_dist.0_vflip.true_hflip.true_messedup.false_id.2.dat", file, 1);
		assertEquals(solved, true);
		solved = Main.check(file);
		f = new File(file);
		f.delete();
		
		file = "test5.dat";
		solved = Main.solve("instances/public/grid_128x128_dist.0_vflip.true_hflip.true_messedup.false_id.0.dat", file, 1);
		assertEquals(solved, true);
		solved = Main.check(file);
		f = new File(file);
		f.delete();
		
		file = "test6.dat";
		solved = Main.solve("instances/public/grid_256x256_dist.6_vflip.false_hflip.true_messedup.false_id.0.dat", file, 1);
		assertEquals(solved, true);
		solved = Main.check(file);
		f = new File(file);
		f.delete();
	}
}
