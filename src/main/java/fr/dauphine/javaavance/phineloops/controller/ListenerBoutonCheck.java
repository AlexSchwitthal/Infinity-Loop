package fr.dauphine.javaavance.phineloops.controller;

import fr.dauphine.javaavance.phineloops.model.Monde;
import fr.dauphine.javaavance.phineloops.view.AffichageMonde;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// Classe ListenerBoutonCheck permettant de capturer les événements de la souris sur le bouton "Check"
public class ListenerBoutonCheck extends ListenerBouton {

        // Constructeur de la classe ListenerBoutonCheck
	public ListenerBoutonCheck(Monde m, AffichageMonde am, JFrame f) {
		super(m, am, f);
	}

        // Redéfinition de la méthode concernant l'événement "Enfoncer"
	@Override
	public void mousePressed(MouseEvent e) {

                // Si la grille est valide, une fênetre avec le message "YOU WIN" s'affiche
		if (m.GetGrille().estValide() == true) {
			JOptionPane.showMessageDialog(null, "YOU WIN", "CHECK", JOptionPane.PLAIN_MESSAGE);
		} 
                
                //Si la grille n'est pas valide, une fênetre avec le message "TRY AGAIN..." s'affiche
		else {
			JOptionPane.showMessageDialog(null, "TRY AGAIN...", "CHECK", JOptionPane.PLAIN_MESSAGE);
		}
	}

}
