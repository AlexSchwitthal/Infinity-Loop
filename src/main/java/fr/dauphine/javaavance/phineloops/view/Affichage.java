package fr.dauphine.javaavance.phineloops.view;

import javax.swing.JPanel;

@SuppressWarnings("serial")

// Classe abstraite Affichage qui est la classe mère de toutes les autres classe Affichage*
// gérant l'affichage console uniquement
public abstract class Affichage extends JPanel {
	public abstract void afficher();
}
