package model.batiment;

import java.util.HashMap;
import java.util.Map;

import model.EnumRessource;
import model.util.MapRessource;

public class BatimentVille {

	private String nom;
	private String description;
	private int techNecessaire;
	private MapRessource bonus;
	private int cout;
	
	public BatimentVille() {
		this("Default", "", 0, new MapRessource(), 0);
	}
	
	public BatimentVille(String nom, String description, int techNecessaire, MapRessource bonus, int cout) {
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

	public MapRessource getBonus() {
		return bonus;
	}

	public void setBonus(MapRessource bonus) {
		this.bonus = bonus;
	}

//	@Override
//	public boolean equals(Object obj) {
//		return nom.equals(((BatimentVille) obj).getNom());
//	}

}
