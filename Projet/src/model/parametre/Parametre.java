package model.parametre;

import java.util.List;

public class Parametre {
	private List<EnumVictoire> TVictoire;
	private EnumTypeCarte typeCarte;
	private EnumTailleCarte tailleCarte;
	private EnumAbondanceRessource abondanceRessource;
	private int nbJoueur;

	public Parametre(List<EnumVictoire> tVictoire, EnumAbondanceRessource abondanceRessource, EnumTypeCarte typeCarte, EnumTailleCarte tailleCarte, int nbJoueur) {
		this.TVictoire = tVictoire;
		this.typeCarte = typeCarte;
		this.tailleCarte = tailleCarte;
		this.nbJoueur = nbJoueur;
		this.abondanceRessource = abondanceRessource;
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
