package fr.dauphine.javaavance.phineloops;

import fr.dauphine.javaavance.phineloops.controller.*;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import fr.dauphine.javaavance.phineloops.model.Grille4D;
import fr.dauphine.javaavance.phineloops.model.Monde;
import fr.dauphine.javaavance.phineloops.view.AffichageMonde;
import java.awt.*;
import javax.swing.*;

public class Main {
	private static Monde monde = new Monde();
	private static String inputFile = null;
	private static String outputFile = null;
	private static Integer width = -1;
	private static Integer height = -1;
	private static Integer maxcc = -1;

	public static void generate(int width, int height, String outputFile) {
		Grille4D grille = new Grille4D(height, width);
		grille.generer();
		grille.melangerGrille();
		monde.setGrille(grille);
		monde.export(outputFile);
	}

	public static boolean solve(String inputFile, String outputFile, int nbThreads) {
		try {
			if(!(inputFile.contains(".dat"))) {
				inputFile += ".dat";
			}
			monde.importGrille(inputFile);
			monde.solve(nbThreads);	
			monde.export(outputFile);
			if(monde.GetGrille().estValide()) {
				return check(outputFile);
			}
			else {
				return false;
			}
		}
		catch(Exception e) {
			System.out.println("erreur lors de la résolution de la grille");
			return false;
		}
	}

	public static boolean check(String inputFile) {	
		if(!(inputFile.contains(".dat"))) {
			inputFile += ".dat";
		}
		monde.importGrille(inputFile);
		return monde.GetGrille().estValide();
	}
	
	
	
	
	public static void main(String[] args) {		
		Options options = new Options();
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;

		options.addOption("g", "generate ", true, "Generate a grid of size height x width.");
		options.addOption("c", "check", true, "Check whether the grid in <arg> is solved.");

		options.addOption("s", "solve", true, "Solve the grid stored in <arg>.");
		options.addOption("o", "output", true,
				"Store the generated or solved grid in <arg>. (Use only with --generate and --solve.)");
		options.addOption("t", "threads", true, "Maximum number of solver threads. (Use only with --solve.)");
		options.addOption("x", "nbcc", true, "Maximum number of connected components. (Use only with --generate.)");
		options.addOption("G", "gui", true, "Run with the graphic user interface.");
		options.addOption("h", "help", false, "Display this help");

		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			System.err.println("Error: invalid command line format.");
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("phineloops", options);
			System.exit(1);
		}

		try {
			if (cmd.hasOption("g")) {
				System.out.println("Running phineloops generator.");
				String[] gridformat = cmd.getOptionValue("g").split("x");
				width = Integer.parseInt(gridformat[0]);
				height = Integer.parseInt(gridformat[1]);
				if (!cmd.hasOption("o"))
					throw new ParseException("Missing mandatory --output argument.");
				outputFile = cmd.getOptionValue("o");

				generate(width, height, outputFile);
			} else if (cmd.hasOption("s")) {
				System.out.println("Running phineloops solver.");
				inputFile = cmd.getOptionValue("s");
				if (!cmd.hasOption("o"))
					throw new ParseException("Missing mandatory --output argument.");
				outputFile = cmd.getOptionValue("o");
				int nbThreads = 1;
				if(cmd.hasOption("t")) {
					int argument = Integer.valueOf(cmd.getOptionValue("t"));
					if(argument > 1) {
						nbThreads = argument;
					}
				}
				boolean solved = solve(inputFile, outputFile, nbThreads);

				System.out.println("SOLVED: " + solved);
			}

			else if (cmd.hasOption("c")) {
				System.out.println("Running phineloops checker.");
				inputFile = cmd.getOptionValue("c");

				boolean solved = check(inputFile);

				System.out.println("SOLVED: " + solved);
			}
			else if (cmd.hasOption("G")) {
				System.out.println("Running phineloops GUI");
				String[] gridformat = cmd.getOptionValue("G").split("x");
				width = Integer.parseInt(gridformat[0]);
				height = Integer.parseInt(gridformat[1]);
                if(width > 16 || height > 16){
                    JOptionPane.showMessageDialog(null, "Dimensions non conformes", "ERREUR", JOptionPane.PLAIN_MESSAGE);
                }
                else{
                    
                
                    Grille4D g = new Grille4D(width,height);
                    g.generer();
                    Monde m = new Monde(g);

                    JFrame f = new JFrame();
                    f.setSize(70*width+120, Math.max(80*height,410));
                    f.setLocationRelativeTo(null);
                    f.setLayout(null);
                    f.setResizable(false);
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    AffichageMonde plateau = new AffichageMonde(m);
                    f.add(plateau);
                    plateau.setBorder(BorderFactory.createLineBorder(Color.black));
                    plateau.setBounds(0, 0, 70*width, 70*height);

                    JButton bouton1 = new JButton("Mélanger");
                    bouton1.addMouseListener(new ListenerBoutonMelanger(m, plateau, f));
                    f.add(bouton1);
                    bouton1.setBounds(70*width+10, 10, 100, 80);

                    JButton bouton2 = new JButton("Résolution");
                    bouton2.addMouseListener(new ListenerBoutonSolution(m, plateau, f));
                    f.add(bouton2);
                    bouton2.setBounds(70*width+10, 100, 100, 80);

                    JButton bouton3 = new JButton("Check");
                    bouton3.addMouseListener(new ListenerBoutonCheck(m, plateau, f));
                    f.add(bouton3);
                    bouton3.setBounds(70*width+10, 190, 100, 80);

                    JButton bouton4 = new JButton("New game");
                    bouton4.addMouseListener(new ListenerBoutonNewGame(m, plateau, f));
                    f.add(bouton4);
                    bouton4.setBounds(70*width+10, 280, 100, 80);

                    f.setVisible(true);
                    Thread.sleep(2000000);
                    }
			}
			else {
				throw new ParseException(
						"You must specify at least one of the following options: -generate -check -solve ");
			}
		} catch (ParseException | InterruptedException e) {
			System.err.println("Error parsing commandline : " + e.getMessage());
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("phineloops", options);
			System.exit(1); // exit with error
		}

		System.exit(0); // exit with success
	}
}
