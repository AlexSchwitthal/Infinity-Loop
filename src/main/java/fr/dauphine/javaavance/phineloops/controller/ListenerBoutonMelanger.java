package fr.dauphine.javaavance.phineloops.controller;

import fr.dauphine.javaavance.phineloops.model.*;
import fr.dauphine.javaavance.phineloops.view.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

// Classe ListenerBoutonMelanger permettant de capturer les événements de la souris sur le bouton "Melanger"
public class ListenerBoutonMelanger extends ListenerBouton {

        // Constructeur de la classe ListenerBoutonMelanger
	public ListenerBoutonMelanger(Monde m, AffichageMonde am, JFrame f) {
		super(m, am, f);
	}

        // Redéfinition de la méthode concernant l'événement "Enfoncer"
	@Override
	public void mousePressed(MouseEvent e) {
                
                // La grille m est mélangée
		m.GetGrille().melangerGrille();
                
                // Retrait de l'ancien composant AffichageMonde
		f.remove(am);
                
                // Création du nouveau composant AffichageMonde avec la grille mélangée
		AffichageMonde plateau = new AffichageMonde(m);
                
                // Ajout du nouveau composant AffichageMonde
		f.add(plateau);
                
                // Paramètrage des bordures et des dimensions du composant
		plateau.setBorder(BorderFactory.createLineBorder(Color.black));
		plateau.setBounds(0, 0, 70 * m.GetGrille().getHauteur(), 70 * m.GetGrille().getLargeur());

	}

}
