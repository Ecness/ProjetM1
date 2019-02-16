package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.carte.stellaire.Carte;
import model.carte.stellaire.Ville;
import model.entity.player.Joueur;
import model.parametre.Parametre;

public class Partie {
	
	private Joueur[] TJoueur;
	private List<Ville> villes;
	private Parametre parametrePartie;
	private Carte galaxie;
	private int nbTour;
	
	public Partie(Parametre parametrePartie) {
		this.parametrePartie = parametrePartie;
		this.galaxie = new Carte(parametrePartie);
		this.nbTour = 0;
		this.villes = new ArrayList<Ville>();
		
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println("main");
	}

	public Joueur[] getTJoueur() {
		return TJoueur;
	}

	public void setTJoueur(Joueur[] tJoueur) {
		TJoueur = tJoueur;
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

	public List<Ville> getVilles() {
		return villes;
	}

	public void setVilles(List<Ville> villes) {
		this.villes = villes;
	}

	public Parametre getParametrePartie() {
		return parametrePartie;
	}

	public void setParametrePartie(Parametre parametrePartie) {
		this.parametrePartie = parametrePartie;
	}
}
