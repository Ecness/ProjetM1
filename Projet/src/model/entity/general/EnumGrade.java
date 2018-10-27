package model.entity.general;   

public enum EnumGrade {

	NOVIS(0,250,500),
	LIEUTENANT(1,500,1000),
	COMMANDANT(2,750,2000),
	AMIRAL(3,1000,4000);
	
	private int numero;
	private int maxPower;
	private int xpMax;
	
	private EnumGrade(int numero, int maxPower, int xpMax) {
		this.numero = numero;
		this.maxPower = maxPower;
		this.xpMax = xpMax;
	}

	public EnumGrade gradeSuiant(int num) {
		
		for(EnumGrade e : values()) {
			if((num+1)==e.getNumero()) {
				return e;
			}
		}
		return null;
	}
	
	public EnumGrade getGrade(int num) {
		
		for(EnumGrade e : values()) {
			if((num)==e.getNumero()) {
				return e;
			}
		}
		return null;
	}
	public int getXpMax() {
		return xpMax;
	}
	
	public int getNumero() {
		return numero;
	}
	public int getMaxPower() {
		return maxPower;
	}
}
