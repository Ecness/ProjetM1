package model.carte.combat;

import model.entity.vaisseau.Vaisseau;

public class CaseCombat {
	private EnumCase Type;
	private boolean presenceTorpille;
	private Vaisseau vaisseau;
	
	public CaseCombat(){};
	
	public CaseCombat(EnumCase type) {
		Type = type;
		this.presenceTorpille = false;
		this.vaisseau = null;
	}
	
	
	public EnumCase getType() {
		return Type;
	}
	public void setType(EnumCase type) {
		Type = type;
	}
	public boolean isPresenceTorpille() {
		return presenceTorpille;
	}
	public void setPresenceTorpille(boolean presenceTorpille) {
		this.presenceTorpille = presenceTorpille;
	}
	public Vaisseau getVaisseau() {
		return vaisseau;
	}
	public void setVaisseau(Vaisseau vaisseau) {
		this.vaisseau = vaisseau;
	}
	
	
}
