package model.carte.stellaire;

import java.util.ArrayList;
import java.util.List;

import model.parametre.EnumAbondanceRessource;

public class Systeme {
	private List<Planete> TPlanete;
	private int numJoueur;
	private List<EnumAnomalie> TAnomalie;
	
	
	
	public Systeme(EnumAbondanceRessource nbRessource) {
		TPlanete = new ArrayList<Planete>();
		this.numJoueur = -1;
		TAnomalie = new ArrayList<EnumAnomalie>();
		generationSystem(nbRessource);
		generationAnomalie();
	}

	private void generationSystem(EnumAbondanceRessource nbRessource) {
		
		int nbPlanette = (int) (5*Math.random());
		
		for( int i=0; i<nbPlanette; i++) {
			TPlanete.add(new Planete(EnumTypePlanete.type(),nbRessource));
		}	
	}
	
	private void generationAnomalie() {
		
		
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

	public List<EnumAnomalie> getTAnomalie() {
		return TAnomalie;
	}

	public void setTAnomalie(List<EnumAnomalie> tAnomalie) {
		TAnomalie = tAnomalie;
	}
	
}
