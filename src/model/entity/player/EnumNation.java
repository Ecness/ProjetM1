package model.entity.player;

public enum EnumNation {
	
	// TODO pas d'id�� de nom
	ETRE_CRISTALIN("Les mine de cristal offre un bonus de +1 en production et poss�de une meilleur pr�scision sur les armes laser."),
	ALIEN("Les extracteur de gaz et les mine de cristal offre un bonus de +1 en science"),
	HUMANOIDE("Poss�de une sonde au d�but de la partie"),
	INSECTOIDE("le blindage des vaisseaux et r�duit mais poss�de la facult� de se r�g�n�r�");
	
	private String avantage;

	private EnumNation(String avantage) {
		this.avantage = avantage;
	}
	
	public String toString() {
		return this.name();
	}
}
