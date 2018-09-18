package model.carte.combat;

import model.carte.stellaire.Systeme;
import model.entity.player.Joueur;
import model.entity.vaisseau.Flotte;

public class MapCombat {
	private Joueur joueur1;
	private Joueur joueur2;
	private Flotte flotteJ1;
	private Flotte flotteJ2;
	private CaseCombat[][] TMap;
	private Systeme systeme;
	
	public MapCombat(Joueur joueur1, Joueur joueur2, Flotte flotteJ1, Flotte flotteJ2,
			Systeme systeme) {
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.flotteJ1 = flotteJ1;
		this.flotteJ2 = flotteJ2;
		//TMap = new CaseCombat[][];
		this.systeme = systeme;
	}

	public Joueur getJoueur1() {
		return joueur1;
	}

	public void setJoueur1(Joueur joueur1) {
		this.joueur1 = joueur1;
	}

	public Joueur getJoueur2() {
		return joueur2;
	}

	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
	}

	public Flotte getFlotteJ1() {
		return flotteJ1;
	}

	public void setFlotteJ1(Flotte flotteJ1) {
		this.flotteJ1 = flotteJ1;
	}

	public Flotte getFlotteJ2() {
		return flotteJ2;
	}

	public void setFlotteJ2(Flotte flotteJ2) {
		this.flotteJ2 = flotteJ2;
	}

	public CaseCombat[][] getTMap() {
		return TMap;
	}

	public void setTMap(CaseCombat[][] tMap) {
		TMap = tMap;
	}

	public Systeme getSysteme() {
		return systeme;
	}

	public void setSysteme(Systeme systeme) {
		this.systeme = systeme;
	}
	
	
}
