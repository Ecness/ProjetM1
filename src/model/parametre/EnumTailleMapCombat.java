package model.parametre;

public enum EnumTailleMapCombat {

	PETITE(3000,5,500),
	MOYENNE(5000,10,700),
	GRANDE(8000,20,1000),
	IMMENSE(10000,30,1000);
	
	private final int number;
	private final int nbObstacle;
	private final int zoneSpawn;
	
	private EnumTailleMapCombat(int number,int nbObstacle, int zoneSpawn) {
		this.number=number;
		this.nbObstacle=nbObstacle;
		this.zoneSpawn=zoneSpawn;
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
	public int getZoneSpawn() {
		return zoneSpawn;
	}
}
