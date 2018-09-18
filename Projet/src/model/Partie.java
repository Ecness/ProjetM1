package model;

import model.carte.stellaire.Carte;
import model.entity.player.Joueur;
import model.parametre.Parametre;

public class Partie {
	
	private Joueur[] TJoueur;
	private Parametre parametrPartie;
	private Carte galaxie;
	private int nbTour;
	
	public Partie(Parametre parametrPartie) {
		this.parametrPartie = parametrPartie;
		this.TJoueur = new Joueur[this.parametrPartie.getNbJoueur()];
		this.galaxie = new Carte(this.parametrPartie.getTypeCarte(),this.parametrPartie.getTailleCarte(),
				this.parametrPartie.getAbondanceRessource());
		this.nbTour = 0;
	}

	public Joueur[] getTJoueur() {
		return TJoueur;
	}

	public void setTJoueur(Joueur[] tJoueur) {
		TJoueur = tJoueur;
	}

	public Parametre getParametrPartie() {
		return parametrPartie;
	}

	public void setParametrPartie(Parametre parametrPartie) {
		this.parametrPartie = parametrPartie;
	}

	public Carte getGalaxie() {
		return galaxie;
	}

	public void setGalaxie(Carte galaxie) {
		this.galaxie = galaxie;
	}

	public int getNbTour() {
		return nbTour;
	}

	public void setNbTour(int nbTour) {
		this.nbTour = nbTour;
	}
}
