package model.carte.stellaire;

public enum EnumTypePlanete {
	
	GLACEE(1),
	TROPICAL(2),
	TELURIQUE(3),
	OCEANIQUE(4),
	GAZEUSE(5);
	
	private int numero;
	private static int nbPlanete = 5;
	
	private EnumTypePlanete(int numero) {
		this.numero=numero;
	}
	
	public static EnumTypePlanete type() {
		int type = (int) (nbPlanete*Math.random()+1);
		for(EnumTypePlanete e : values()) {
			if(type==e.getNumero()) {
				return e;
			}
		}
		return null;
	}
	
	public static EnumTypePlanete typeHabitable() {
		int type = (int) ((nbPlanete-1)*Math.random()+1);
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