package model.carte.stellaire;

import java.util.ArrayList;
import java.util.List;

import model.entity.vaisseau.Flotte;
import model.parametre.EnumAbondanceRessource;

public class Systeme {
	private List<Planete> TPlanete;
	private int numJoueur;
	private List<Anomalie> TAnomalie;
	private List<Flotte> flottes;
	
	
	
	public Systeme(EnumAbondanceRessource nbRessource, int maxPlanete, int maxAnomalie) {
		TPlanete = new ArrayList<Planete>();
		this.numJoueur = -1;
		TAnomalie = new ArrayList<Anomalie>();
		this.flottes = new ArrayList<Flotte>();
		generationSystem(nbRessource, maxPlanete);
		generationAnomalie(maxAnomalie);
	}

	private void generationSystem(EnumAbondanceRessource nbRessource,int maxPlanete) {
		
		int nbPlanette = (int) (maxPlanete*Math.random());
		
		for( int i=0; i<nbPlanette; i++) {
			TPlanete.add(new Planete(EnumTypePlanete.type(),nbRessource));
		}	
	}
	
	private void generationAnomalie(int maxAnomalie) {
		
		int nbAnomalie = (int) (maxAnomalie*Math.random());
		
		for( int i=0; i<nbAnomalie; i++) {
			TAnomalie.add(new Anomalie());
		}	
	}
	
	
	public List<Planete> getTPlanete() {
		return TPlanete;
	}

	public void setTPlanete(List<Planete> tPlanete) {
		TPlanete = tPlanete;
	}

	public int getNumJoueur() {
		return numJoueur;
	}

	public void setNumJoueur(int numJoueur) {
		this.numJoueur = numJoueur;
	}

	public List<Anomalie> getTAnomalie() {
		return TAnomalie;
	}

	public void setTAnomalie(List<Anomalie> tAnomalie) {
		TAnomalie = tAnomalie;
	}

	public List<Flotte> getFlottes() {
		return flottes;
	}

	public void setFlottes(List<Flotte> flottes) {
		this.flottes = flottes;
	}
	
	
}
