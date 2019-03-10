package model.batiment;

import java.util.HashMap;
import java.util.Map;

import model.EnumRessource;

public class BatimentVille {

	private String nom;
	private String description;
	private int techNecessaire;
	private Map<EnumRessource, Integer> bonus;
	private int cout;
	
	public BatimentVille() {
		this("Default", "", 0, new HashMap<EnumRessource, Integer>(), 0);
	}
	
	public BatimentVille(String nom, String description, int techNecessaire, Map<EnumRessource, Integer> bonus, int cout) {
		super();
		this.nom = nom;
		this.description = description;
		this.techNecessaire = techNecessaire;
		this.bonus = bonus;
		this.cout = cout;
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
	public int getTechNecessaire() {
		return techNecessaire;
	}
	public void setTechNecessaire(int techNecessaire) {
		this.techNecessaire = techNecessaire;
	}
	
	public int getCout() {
		return cout;
	}
	public void setCout(int cout) {
		this.cout = cout;
	}

	public Map<EnumRessource, Integer> getBonus() {
		return bonus;
	}

	public void setBonus(Map<EnumRessource, Integer> bonus) {
		this.bonus = bonus;
	}

}
