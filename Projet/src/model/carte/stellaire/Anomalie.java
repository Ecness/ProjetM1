package model.carte.stellaire;

public class Anomalie {

	private boolean decouverte;
	private EnumAnomalie anomalie;
	
	public Anomalie() {
		super();
		this.decouverte = false;
		this.anomalie = EnumAnomalie.type();
		if(this.anomalie==EnumAnomalie.PIRATE)
			this.decouverte = true;
		// todo ajouter une flote de pirate sur le systéme 
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
