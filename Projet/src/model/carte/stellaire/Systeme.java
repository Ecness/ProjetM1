package model.carte.stellaire;

import java.util.ArrayList;
import java.util.List;

public class Systeme {
	private List<Planete> TPlanete;
	private int numJoueur;
	private List<EnumAnomalie> TAnomalie;
	
	
	
	public Systeme() {
		TPlanete = new ArrayList<Planete>();
		this.numJoueur = -1;
		TAnomalie = new ArrayList<EnumAnomalie>();
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
