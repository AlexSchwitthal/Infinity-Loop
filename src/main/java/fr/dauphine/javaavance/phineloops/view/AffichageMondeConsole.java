package fr.dauphine.javaavance.phineloops.view;

import fr.dauphine.javaavance.phineloops.model.Monde;

@SuppressWarnings("serial")

// Classe AffichageMondeConsole permettant l'affichage d'un monde en mode console
public class AffichageMondeConsole extends Affichage {

	private Monde m;

        // Constructure de la classe AffichageMondeConsole prenant en paramètre un monde
	public AffichageMondeConsole(Monde m) {
		this.m = m;
	}

        // Redéfinition de la méthode affichage() de la classe mère Affichage
	@Override
	public void afficher() {
		m.GetGrille().afficher();
	}
}
