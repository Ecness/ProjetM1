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
	
	
}
