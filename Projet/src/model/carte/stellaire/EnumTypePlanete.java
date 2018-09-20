package model.carte.stellaire;

public enum EnumTypePlanete {
	
	GLACEE(1),
	TROPICAL(2),
	GAZEUSE(3),
	TELURIQUE(4),
	OCEANIQUE(5);
	
	private int numero;
	
	private EnumTypePlanete(int numero) {
		this.numero=numero;
	}
	
	public static EnumTypePlanete type() {
		int type = (int) (5*Math.random()+1);
		for(EnumTypePlanete e : values()) {
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