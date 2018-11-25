package model.carte.stellaire;

public enum EnumTypeSysteme {
	NEBULEUSE(0),//5%
	TROU_NOIR(1),//1%
	SYSTEME_PLANETAIRE(2),//90%
	SYSTEME_DOUBLE_ETOILE(3);//4%
	
	private int numero;
	private static int nbPlanete = 4;
	
	private EnumTypeSysteme(int numero) {
		this.numero=numero;
	}
	
	public static EnumTypeSysteme type() {
		int type = (int) (100*Math.random()+1);
		if(type==100) {
			return EnumTypeSysteme.TROU_NOIR;
		}else if(type<6) {
			return EnumTypeSysteme.NEBULEUSE;
		}else if(type<10) {
			return EnumTypeSysteme.SYSTEME_DOUBLE_ETOILE;
		}else {
			return EnumTypeSysteme.SYSTEME_PLANETAIRE;
		}
	}
	
	public int getNumero() {
		return numero;
	}
	
	public int nbPlanete() {
		return nbPlanete;
	}
	
}
