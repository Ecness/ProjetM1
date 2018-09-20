package model.carte.stellaire;

import model.parametre.EnumAbondanceRessource;
import model.parametre.EnumTailleCarte;
import model.parametre.EnumTypeCarte;

public class Carte {

	private System[][] carte;
	
	public Carte(EnumTypeCarte typeCarte, EnumTailleCarte tailleCarte, EnumAbondanceRessource abondanceRessource) {
		// TODO Faire la fonction de génération
	}

	public System[][] getCarte() {
		return carte;
	}

	public void setCarte(System[][] carte) {
		this.carte = carte;
	} 
	
}
