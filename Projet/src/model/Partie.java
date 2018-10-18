package model;

import model.carte.stellaire.Carte;
import model.entity.player.Joueur;
import model.parametre.Parametre;

public class Partie {
	
	private Joueur[] TJoueur;
	private Parametre parametrePartie;
	private Carte galaxie;
	private int nbTour;
	
	public Partie(Parametre parametrePartie) {
		this.parametrePartie = parametrePartie;
		this.TJoueur = new Joueur[parametrePartie.getNbJoueur()];
		this.galaxie = new Carte(parametrePartie);
		this.nbTour = 0;
	}
	
	
	public static void main(String[] args) {
		System.out.println("main");

	}

	public Joueur[] getTJoueur() {
		return TJoueur;
	}

	public void setTJoueur(Joueur[] tJoueur) {
		TJoueur = tJoueur;
	}

	public Parametre getParametrPartie() {
		return parametrePartie;
	}

	public void setParametrPartie(Parametre parametrPartie) {
		this.parametrePartie = parametrPartie;
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
