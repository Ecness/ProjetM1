package model.entity.vaisseau;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import model.entity.general.General;

public class Flotte {

	private String nom;
	private int puissance;
	private Map<Integer, Vaisseau> TVaisseau;
	private General general;
	
	public Flotte() {
		this.puissance = 0;
		TVaisseau = new HashMap<Integer, Vaisseau>();
		this.general = null;
	}
	
	//------------------------------------------------------------------------------------------------------
	
	public void addVaisseau(int id, Vaisseau vaisseau) {
		
		TVaisseau.put(id, vaisseau);
		puissance += vaisseau.getPuissance();
	}
	
	@Override
	public String toString() {
		String string="Flotte de puissance :" + puissance + "\n";
		for (Entry<Integer, Vaisseau> vaisseau : TVaisseau.entrySet()) {
			string += vaisseau.getValue().toString() + "\n\n";	
		}
		return string;
	}
	
	//------------------------------------------------------------------------------------------------------

	public int getPuissance() {
		return puissance;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}

	public Map<Integer, Vaisseau> getTVaisseau() {
		return TVaisseau;
	}

	public void setTVaisseau(Map<Integer, Vaisseau> tVaisseau) {
		TVaisseau = tVaisseau;
	}

	public General getGeneral() {
		return general;
	}

	public void setGeneral(General general) {
		this.general = general;
	}
	
}
