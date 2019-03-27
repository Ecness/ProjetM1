package model.module;

import java.util.HashMap;
import java.util.Map;

import model.EnumRessource;

public class Arme{

	private String nom;
	private String description;
	private int point;
	private int dommage;
	private int precision;
	private int critique;
	private int nbTire;
	private int tauxFeu;
	private Map<EnumRessource, Integer> cout;
	private Boolean utilisable;
	private Boolean endomager;
	private EnumTypeArme typeArme;
	
	public Arme() {
		this("Default", "", 0, 0, 0, 0, 0, 0, new HashMap<EnumRessource, Integer>(), null);
		this.utilisable=true;
		this.endomager=false;
	}
	
	public Arme(String nom, String description, int dommage, int precision, int nbTire,
			int tauxFeu, int critique, int point, Map<EnumRessource, Integer> cout,
			EnumTypeArme typeArme) {
		this.nom = nom;
		this.description = description;
		this.dommage = dommage;
		this.precision = precision;
		this.nbTire = nbTire;
		this.critique = critique;
		this.tauxFeu = tauxFeu;
		this.point = point;
		this.cout=cout;
		this.utilisable=true;
		this.endomager=false;
		this.typeArme=typeArme;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------
	
	
	//--------------------------------------------------------------------------------------------------------------------------------------------
	
	public int getTauxFeu() {
		return tauxFeu;
	}
	public void setTauxFeu(int tauxFeu) {
		this.tauxFeu = tauxFeu;
	}
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
	public int getDommage() {
		return dommage;
	}
	public void setDommage(int dommage) {
		this.dommage = dommage;
	}
	public int getPrecision() {
		return precision;
	}
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	public int getNbTire() {
		return nbTire;
	}
	public void setNbTire(int nbTire) {
		this.nbTire = nbTire;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getCritique() {
		return critique;
	}
	public void setCritique(int critique) {
		this.critique = critique;
	}
	public Map<EnumRessource, Integer> getCout() {
		return cout;
	}
	public void setCout(Map<EnumRessource, Integer> cout) {
		this.cout = cout;
	}
	public Boolean getUtilisable() {
		return utilisable;
	}
	public void setUtilisable(Boolean utilisable) {
		this.utilisable = utilisable;
	}
	public Boolean getEndomager() {
		return endomager;
	}
	public void setEndomager(Boolean endomager) {
		this.endomager = endomager;
	}
	public EnumTypeArme getTypeArme() {
		return typeArme;
	}
	public void setTypeArme(EnumTypeArme typeArme) {
		this.typeArme = typeArme;
	}
}
