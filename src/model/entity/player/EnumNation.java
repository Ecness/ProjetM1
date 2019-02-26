package model.entity.player;

public enum EnumNation {
	
	// TODO pas d'id�� de nom
	ETRE_CRISTALIN("Les mine de cristal offre un bonus de +1 en production et poss�de une meilleur pr�scision sur les armes laser.","Ressources/Nation/ETRE_CRISTALIN"),
	ALIEN("Les extracteur de gaz et les mine de cristal offre un bonus de +1 en science","Ressources/Nation/ALIEN"),
	HUMANOIDE("Posséde une sonde au début de la partie","Ressources/Nation/HUMANOIDE"),
	INSECTOIDE("le blindage des vaisseaux et réduit mais posséde la faculté de se régénéré","Ressources/Nation/INSECTOIDE");
	
	private String avantage;
	private String path;

	private EnumNation(String avantage,String path) {
		this.avantage = avantage;
		this.path=path;
	}
	
	public String getPath() {
		return path;
	}
	
	public String toString() {
		return this.name();
	}
}
