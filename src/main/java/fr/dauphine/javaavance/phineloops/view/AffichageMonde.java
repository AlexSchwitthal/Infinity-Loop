package fr.dauphine.javaavance.phineloops.view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import fr.dauphine.javaavance.phineloops.controller.ListenerCase;
import fr.dauphine.javaavance.phineloops.model.Grille;
import fr.dauphine.javaavance.phineloops.model.Monde;

@SuppressWarnings("serial")

// Classe AffichageMonde permettant l'affichage d'un monde en mode graphique
public class AffichageMonde extends JPanel {

	private Monde m;

        // Constructure de la classe AffichageMonde prenant en paramètre un monde
	public AffichageMonde(Monde m) {
		this.m = m;
		Grille g = m.GetGrille();
		setLayout(new GridLayout(g.getLargeur(), g.getHauteur()));
		setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		for (int i = 0; i < g.getHauteur() ; i++) {
			for (int j = 0; j < g.getLargeur(); j++) {
					ajouterCase(new AffichagePiece(g.getGrille()[i][j]), i, j);
			}
		}
	}

        // Méthode qui permet d'ajouter une pièce dans le monde
        // Ajoute également le ListenerCase sur la pièce
	public void ajouterCase(AffichagePiece c, int x, int y) {
		c.addMouseListener(new ListenerCase(AffichageMonde.this, c, x, y));
		add(c);
	}

        // Getter de la variable Monde m
	public Monde getM() {
		return m;
	}

}
