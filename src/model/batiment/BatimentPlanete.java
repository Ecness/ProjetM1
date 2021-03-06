package model.batiment;

import model.util.MapRessource;

public class BatimentPlanete {

	private String nom;
	private String description;
	private int techNecessaire;
	private MapRessource bonus;
	private MapRessource cout;
	private boolean construit;
	
	public BatimentPlanete() {
		this("Default", "", 0, new MapRessource(), new MapRessource());
	}
	
	public BatimentPlanete(String nom, String description, int techNecessaire, MapRessource bonus, MapRessource cout) {
		super();
		this.nom = nom;
		this.description = description;
		this.techNecessaire = techNecessaire;
		this.bonus = bonus;
		this.cout = cout;
	}
	
	public BatimentPlanete(BatimentPlanete batiment) {
		this.nom = new String(batiment.getNom());
		this.description = new String(batiment.getDescription());
		this.techNecessaire = batiment.getTechNecessaire();
		this.bonus = new MapRessource(batiment.getBonus());
		this.cout = new MapRessource(batiment.getCout());
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
	public MapRessource getBonus() {
		return bonus;
	}
	public void setBonus(MapRessource bonus) {
		this.bonus = bonus;
	}
	public MapRessource getCout() {
		return cout;
	}
	public void setCout(MapRessource cout) {
		this.cout = cout;
	}

	public boolean isConstruit() {
		return construit;
	}

	public void setConstruit(boolean construit) {
		this.construit = construit;
	}
}
