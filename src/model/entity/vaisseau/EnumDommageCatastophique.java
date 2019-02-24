package model.entity.vaisseau;

public enum EnumDommageCatastophique {

	EPAVE(0),
	EPAVE_EN_FLAMME(1),
	EXPLOSION(2),
	EFFONDREMENT_GRAVITATIONNEL(4);
	
	private int id;
	
	private EnumDommageCatastophique(int id) {
		this.id = id;
	}
	
	public EnumDommageCatastophique getDammage() {
		int type = (int) ((6*Math.random()+1)+(6*Math.random()+1));
		
		if(type == 12) {
			return EFFONDREMENT_GRAVITATIONNEL;
		}
		if(type > 8) {
			return EXPLOSION;
		}
		if(type > 6) {
			return EPAVE_EN_FLAMME;
		}
		return EPAVE;
	}
	
	public int getId() {
		return id;
	}
	
}
