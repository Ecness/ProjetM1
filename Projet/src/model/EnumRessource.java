package model;


public enum EnumRessource {

	CREDIT(1),
	SCIENCE(2),
	GAZ(3),
	ACIER(4),
	CRISTAL(5);
	
	private int numero;
	
	private EnumRessource(int numero) {
		this.numero=numero;
	}
	
	public static EnumRessource renvoit() {
		int type = (int) (5*Math.random()+1);
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
	
}
