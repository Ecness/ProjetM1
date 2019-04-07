package model.batiment;

import model.util.MapRessource;

public class BatimentVille {

	private String nom;
	private String description;
	private int techNecessaire;
	private MapRessource bonus;
	private int cout, baseProdCost;
	private boolean construit, enConstruction;
	
	public BatimentVille() {
		this("Default", "", 0, new MapRessource(), 0);
	}
	
	public BatimentVille(String nom, String description, int techNecessaire, MapRessource bonus, int cout) {
		super();
		this.nom = nom;
		this.description = description;
		this.techNecessaire = techNecessaire;
		this.bonus = bonus;
		this.cout = this.baseProdCost = cout;
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
	
	public int getBaseProdCost() {
		return baseProdCost;
	}

	public MapRessource getBonus() {
		return bonus;
	}

	public void setBonus(MapRessource bonus) {
		this.bonus = bonus;
	}

	public boolean isConstruit() {
		return construit;
	}

	public void setConstruit(boolean construit) {
		this.construit = construit;
	}

	public boolean isEnConstruction() {
		return enConstruction;
	}

	public void setEnConstruction(boolean enConstruction) {
		this.enConstruction = enConstruction;
	}
}
