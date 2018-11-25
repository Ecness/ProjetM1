package model.entity.player;

public enum EnumNation {
	
	// TODO pas d'idéé de nom
	ETRE_CRISTALIN("Les mine de cristal offre un bonus de +1 en production et posséde une meilleur préscision sur les armes laser."),
	ALIEN("Les extracteur de gaz et les mine de cristal offre un bonus de +1 en science"),
	HUMANOIDE("Posséde une sonde au début de la partie"),
	INSECTOIDE("le blindage des vaisseaux et réduit mais posséde la faculté de se régénéré");
	
	private String avantage;

	private EnumNation(String avantage) {
		this.avantage = avantage;
	}
	
	public String toString() {
		return avantage;
	}
	
}
