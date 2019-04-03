package model.module;

import java.util.HashMap;
import java.util.Map;

import model.EnumRessource;
import model.util.MapRessource;

public class Chassie {

	private String nom;
	private String description;
	private int point;
	private int sante;
	private int santeMax;
	private int bouclier;
	private int bouclierMax;
	private int nbArme;
	private int nbBlindage;
	private int nbModule;
	private MapRessource cout;
	
	public Chassie() {
		this("Default","", 0, 0, 0, 0, 0, 0, 0, 0, new MapRessource());
	}
	
	public Chassie(String nom, String description, int point, int sante, int santeMax, int bouclier, int bouclierMax,
			int nbArme, int nbBlindage, int nbModule, MapRessource cout) {
		this.nom = nom;
		this.description = description;
		this.point = point;
		this.sante = sante;
		this.santeMax = santeMax;
		this.bouclier = bouclier;
		this.bouclierMax = bouclierMax;
		this.nbArme = nbArme;
		this.nbBlindage = nbBlindage;
		this.nbModule = nbModule;
		this.cout=cout;
	}

	//--------------------------------------------------------------------------------------------------------------------------------------------
	
	
	//--------------------------------------------------------------------------------------------------------------------------------------------
		
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getSante() {
		return sante;
	}
	public void setSante(int sante) {
		this.sante = sante;
	}
	public int getSanteMax() {
		return santeMax;
	}
	public void setSanteMax(int santeMax) {
		this.santeMax = santeMax;
	}
	public int getBouclier() {
		return bouclier;
	}
	public void setBouclier(int bouclier) {
		this.bouclier = bouclier;
	}
	public int getBouclierMax() {
		return bouclierMax;
	}
	public void setBouclierMax(int bouclierMax) {
		this.bouclierMax = bouclierMax;
	}
	public int getNbArme() {
		return nbArme;
	}
	public void setNbArme(int nbArme) {
		this.nbArme = nbArme;
	}
	public int getNbBlindage() {
		return nbBlindage;
	}
	public void setNbBlindage(int nbBlindage) {
		this.nbBlindage = nbBlindage;
	}
	public int getNbModule() {
		return nbModule;
	}
	public void setNbModule(int nbModule) {
		this.nbModule = nbModule;
	}
	public MapRessource getCout() {
		return cout;
	}
	public void setCout(MapRessource cout) {
		this.cout = cout;
	}
}
