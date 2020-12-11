package fr.dauphine.javaavance.phineloops.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import fr.dauphine.javaavance.phineloops.view.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ListenerCase implements MouseListener {

	private AffichageMonde am;
	private AffichagePiece ap;
	private int x;
	private int y;

        // Constructeur de la classe ListenerCase
	public ListenerCase(AffichageMonde am, AffichagePiece ap, int x, int y) {
		this.am = am;
		this.ap = ap;
		this.x = x;
		this.y = y;
	}

        // Redéfinition de la méthode concernant l'événement "Cliquer" (= Enfoncer + Relacher)
        @Override
	public void mouseClicked(MouseEvent arg0) {
	}

        // Redéfinition de la méthode concernant l'événement "La souris est entrée dans l'espace du composant"
        @Override
	public void mouseEntered(MouseEvent arg0) {
	}

        // Redéfition de la méthode concernant l'événement "la souris est sortie de l'espace du composant"
        @Override
	public void mouseExited(MouseEvent arg0) {
	}

        // Redéfinition de la méthode concernant l'événement "Enfoncer"
        @Override
	public void mousePressed(MouseEvent arg0) {
            
		// Changement model
		am.getM().GetGrille().getGrille()[x][y] = am.getM().GetGrille().getGrille()[x][y].rotation();

		// Changement view
		ap.getGraphics().clearRect(0, 0, ap.getWidth(), ap.getHeight());
		int numero = am.getM().GetGrille().getGrille()[x][y].getId();
		Image image = null;
		try {
			image = ImageIO.read(new File("Ressources/" + numero + ".png"));
		} 
		catch (IOException ex) {
			Logger.getLogger(ListenerCase.class.getName()).log(Level.SEVERE, null, ex);
		}
		int a = (ap.getWidth() - image.getWidth(null)) / 2;
		int b = (ap.getHeight() - image.getHeight(null)) / 2;
		ap.getGraphics().drawImage(image, a, b, null);
	}

        // Redéfinition de la méthode concernant l'événement "Relacher"
        @Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
