package fr.dauphine.javaavance.phineloops.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fr.dauphine.javaavance.phineloops.model.Piece;

@SuppressWarnings("serial")

// Classe AffichagePiece permettant l'affiche des pièces en mode graphique
public class AffichagePiece extends JPanel {

	private Image image;

        // Constructeur de la classe AffichagePiece prenant en paramètre une pièce
	public AffichagePiece(Piece p) {
		setLayout(new GridLayout(1, 0));
		int numero = p.getId();
		try {
			image = ImageIO.read(new File("Ressources/" + numero + ".png"));
		} 
		catch (IOException ex) {
			Logger.getLogger(AffichagePiece.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
        
        // Redéfinition de la méthode paintComponent(Graphics g) de la classe mère JPanel
        @Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		int x = (this.getWidth() - image.getWidth(null)) / 2;
		int y = (this.getHeight() - image.getHeight(null)) / 2;
		g2d.drawImage(image, x, y, null);
	}

}
