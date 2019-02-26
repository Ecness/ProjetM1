package model.batiment;

import java.util.HashMap;
import java.util.Map;

import model.EnumRessource;

public class BatimentPlanete {

	private String nom;
	private String description;
	private int techNecessaire;
	private Map<EnumRessource, Integer> bonus;
	private Map<EnumRessource, Integer> cout;
	
	public BatimentPlanete() {
		this("Default", "", 0, new HashMap<EnumRessource, Integer>(), new HashMap<EnumRessource, Integer>());
	}
	
	public BatimentPlanete(String nom, String description, int techNecessaire, Map<EnumRessource, Integer> bonus,
			Map<EnumRessource, Integer> cout) {
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
	public Map<EnumRessource, Integer> getBonus() {
		return bonus;
	}
	public void setBonus(Map<EnumRessource, Integer> bonus) {
		this.bonus = bonus;
	}
	public Map<EnumRessource, Integer> getCout() {
		return cout;
	}
	public void setCout(Map<EnumRessource, Integer> cout) {
		this.cout = cout;
	}
}
