package model.parametre;

public enum EnumAbondanceRessource {

	FAIBLE(-2),
	NORMAL(0),
	ELEVE(2),
	ABONDANT(5);
	
	private int modificateur;
	
	private EnumAbondanceRessource(int modificateur) {
		this.modificateur=modificateur;
	}
	
	public int getmodificateur() {
		return this.modificateur;
	}
	
}
