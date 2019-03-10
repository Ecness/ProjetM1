package model.module;

import java.util.HashMap;
import java.util.Map;

import model.EnumRessource;

public class Blindage {
	
	private String nom;
	private String description;
	private int point;
	private int valeurBlindage;
	private int valeurBlouclier;
	private Map<EnumRessource, Integer> cout;
	
	
	public Blindage() {
		this("Default", "", 0, 0, 0, new HashMap<EnumRessource, Integer>());
	}
	
	public Blindage(String nom, String description, int point, int valeurBlindage, int valeurBlouclier,
			 Map<EnumRessource, Integer> cout) {
		this.nom = nom;
		this.description = description;
		this.point = point;
		this.valeurBlindage = valeurBlindage;
		this.valeurBlouclier = valeurBlouclier;
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
	public int getValeurBlindage() {
		return valeurBlindage;
	}
	public void setValeurBlindage(int valeurBlindage) {
		this.valeurBlindage = valeurBlindage;
	}
	public int getValeurBlouclier() {
		return valeurBlouclier;
	}
	public void setValeurBlouclier(int valeurBlouclier) {
		this.valeurBlouclier = valeurBlouclier;
	}
	public Map<EnumRessource, Integer> getCout() {
		return cout;
	}
	public void setCout(Map<EnumRessource, Integer> cout) {
		this.cout = cout;
	}
}
