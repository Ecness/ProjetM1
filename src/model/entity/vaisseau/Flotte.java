package model.entity.vaisseau;

import java.util.HashMap;
import java.util.Map;

import model.entity.general.General;

public class Flotte {

	private int puissance;
	private Map<Vaisseau, Integer> TVaisseau;
	private General general;
	
	public Flotte() {
		this.puissance = 0;
		TVaisseau = new HashMap<Vaisseau, Integer>();
		this.general = null;
	}

	public int getPuissance() {
		return puissance;
	}

	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}

	public Map<Vaisseau, Integer> getTVaisseau() {
		return TVaisseau;
	}

	public void setTVaisseau(Map<Vaisseau, Integer> tVaisseau) {
		TVaisseau = tVaisseau;
	}

	public General getGeneral() {
		return general;
	}

	public void setGeneral(General general) {
		this.general = general;
	}
	
}
