package fr.dauphine.javaavance.phineloops.controller;

import fr.dauphine.javaavance.phineloops.model.Monde;
import fr.dauphine.javaavance.phineloops.view.AffichageMonde;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

// Classe abstraite ListenerBouton qui est la classe mère de tous les autres ListenerBouton
// Permet de capturer les événements effectués avec la souris sur les composants JPanel
public abstract class ListenerBouton implements MouseListener {
    
    // Variables de la classe ListenerBouton
    protected Monde m;
    protected AffichageMonde am;
    protected JFrame f;

    // Constructeur d'un ListenerBouton prenant en paramètre un Monde, un AffichageMonde et un JFrame
    public ListenerBouton(Monde m, AffichageMonde am, JFrame f) {
        this.m = m;
        this.am = am;
        this.f = f;
    }
    
    // Redéfinition de la méthode concernant l'événement "Cliquer" (= Enfoncer + Relacher)
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    // Redéfinition de la méthode concernant l'événement "La souris est enfoncée"
    @Override
    public void mousePressed(MouseEvent e) {
    }

    // Redéfinition de la méthode concernant l'événement "La souris est relachée"
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    // Redéfinition de la méthode concernant l'événement "La souris est entrée dans l'espace du composant"
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    // Redéfition de la méthode concernant l'événement "la souris est sortie de l'espace du composant"
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
