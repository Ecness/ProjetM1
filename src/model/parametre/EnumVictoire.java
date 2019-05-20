package model.parametre;

public enum EnumVictoire {
	DOMINATION,
	SCIENTIFIQUE,
	EXPANSION;
	
	@Override
	public String toString() {
		return this.name();
	}
}
