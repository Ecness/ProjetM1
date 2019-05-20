package model;



public enum EnumRessource {

	CREDIT(1),
	SCIENCE(2),
	GAZ(3),
	ACIER(4),
	CRISTAL(5),
	PRODUCTION(6),
	PUISSANCE(7);
	
	private int numero;
	private static int nbRessource = 7;
	
	private EnumRessource(int numero) {
		this.numero=numero;
	}
	
	public static EnumRessource renvoit() {
		int type = (int) (nbRessource*Math.random()+1);
		for(EnumRessource e : values()) {
			if(type==e.getNumero()) {
				return e;
			}
		}
		return null;
	}
	
	public int getNumero() {
		return numero;
	}

	@Override
	public String toString() {
		return this.name();
	}
}
