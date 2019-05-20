package model.carte.stellaire;

public enum EnumAnomalie {

	NEBULEUSE(1,"Nebuleuse"),
	EPAVE(2,"Epave"),
	COMETE(3,"Comméte"),
	ASTEROIDE(4,"Asteroïde"),
	PETITE_FLOTTE_PIRATE(5,"Petite flotte de pirate"),
	MOYENNE_FLOTTE_PIRATE(6,"flotte de pirate"),
	GRANDE_FLOTTE_PIRATE(7,"Imposante flotte de pirate"),
	SOLEIL_SUPPLEMENTAIRE(8,"Systéme à double étoile"),
	TROU_NOIR(9,"Trou noir");
	
	private int numero;
	private String nom;
	private static int nbAnomalie = 9;
	
	private EnumAnomalie(int numero, String nom) {
		this.numero=numero;
		this.nom=nom;
	}
	
	public static EnumAnomalie type() {
		int type = (int) (nbAnomalie*Math.random()+1);
		for(EnumAnomalie e : values()) {
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
		return nom;
	}
}
