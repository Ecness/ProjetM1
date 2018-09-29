package model.carte.stellaire;

public class Anomalie {

	private boolean decouverte;
	private EnumAnomalie anomalie;
	
	public Anomalie() {
		super();
		this.decouverte = false;
		this.anomalie = EnumAnomalie.type();
	}
	
	public boolean isDecouverte() {
		return decouverte;
	}
	public void setDecouverte(boolean decouverte) {
		this.decouverte = decouverte;
	}
	public EnumAnomalie getAnomalie() {
		return anomalie;
	}
	public void setAnomalie(EnumAnomalie anomalie) {
		this.anomalie = anomalie;
	}
}
