package model.parametre;

import java.util.List;

public class Parametre {
	private List<EnumVictoire> TVictoire;
	private EnumTypeCarte typeCarte;
	private EnumTailleCarte tailleCarte;
	private EnumAbondanceRessource abondanceRessource;
	private int nbJoueur;
	private int nbMaxPlanete;
	private int nbMaxAnomalie;

	public Parametre(List<EnumVictoire> tVictoire, EnumAbondanceRessource abondanceRessource, EnumTypeCarte typeCarte, 
			EnumTailleCarte tailleCarte, int nbJoueur, int nbMaxPlanete, int nbMaxAnomalie) {
		this.TVictoire = tVictoire;
		this.typeCarte = typeCarte;
		this.tailleCarte = tailleCarte;
		this.nbJoueur = nbJoueur;
		this.abondanceRessource = abondanceRessource;
		this.nbMaxAnomalie = nbMaxAnomalie;
		this.nbMaxPlanete = nbMaxPlanete;
	}

	
	public int getNbMaxPlanete() {
		return nbMaxPlanete;
	}

	public void setNbMaxPlanete(int nbMaxPlanete) {
		this.nbMaxPlanete = nbMaxPlanete;
	}

	public int getNbMaxAnomalie() {
		return nbMaxAnomalie;
	}

	public void setNbMaxAnomalie(int nbMaxAnomalie) {
		this.nbMaxAnomalie = nbMaxAnomalie;
	}

	public EnumAbondanceRessource getAbondanceRessource() {
		return abondanceRessource;
	}

	public void setAbondanceRessource(EnumAbondanceRessource abondanceRessource) {
		this.abondanceRessource = abondanceRessource;
	}

	public int getNbJoueur() {
		return nbJoueur;
	}

	public void setNbJoueur(int nbJoueur) {
		this.nbJoueur = nbJoueur;
	}

	public EnumTailleCarte getTailleCarte() {
		return tailleCarte;
	}


	public void setTailleCarte(EnumTailleCarte tailleCarte) {
		this.tailleCarte = tailleCarte;
	}


	public List<EnumVictoire> getTVictoire() {
		return TVictoire;
	}

	public void setTVictoire(List<EnumVictoire> tVictoire) {
		TVictoire = tVictoire;
	}

	public EnumTypeCarte getTypeCarte() {
		return typeCarte;
	}

	public void setTypeCarte(EnumTypeCarte typeCarte) {
		this.typeCarte = typeCarte;
	}
	
	
}
