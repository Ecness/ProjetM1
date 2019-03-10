package model.parametre;

public enum EnumTailleMapCombat {

	PETITE(3000,70),
	MOYENNE(5000,200),
	GRANDE(8000,500),
	IMMENSE(10000,800);
	
	private final int number;
	private final int nbObstacle;
	
	
	private EnumTailleMapCombat(int number,int nbObstacle) {
		this.number=number;
		this.nbObstacle=nbObstacle;
	}
	
	public int nbObstacle() {
		for (EnumTailleMapCombat t : values()) {
			if(t==this) {
				return (int)((nbObstacle)*Math.random()+1);
			}
		}
		return -1;
	}
	
	public int getTaille() {
		return number;
	}
	public int getNbObstacle() {
		return nbObstacle;
	}
}
