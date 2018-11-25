package model.carte.stellaire;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import model.EnumRessource;
import model.entity.player.Joueur;

public class Anomalie {

	private boolean decouverte;
	private EnumAnomalie anomalie;
	private Map<EnumRessource, Integer> bonus;
	
	public Anomalie(EnumAnomalie anomalie) {
		super();
		this.decouverte = false;
		this.anomalie = anomalie;
		if(this.anomalie==EnumAnomalie.PIRATE) {
			this.decouverte = true;
			// todo ajouter une flote de pirate sur le systéme 			
		}else {			
			bonus = new HashMap<EnumRessource, Integer>();
			bonus();
		}
	}
	
	private void bonus() {
		
		switch (anomalie) {
		case ASTEROIDE:
			if((int) (2*Math.random())==0) {
				bonus.put(EnumRessource.ACIER, (int) (30*Math.random()+10));
			}else {
				bonus.put(EnumRessource.CRISTAL, (int) (30*Math.random()+10));
			}
			break;
		case COMETE:
			if((int) (2*Math.random())==0) {
				bonus.put(EnumRessource.GAZ, (int) (10*Math.random()+10));
			}else {
				bonus.put(EnumRessource.SCIENCE, 1);
			}
			break;
		case EPAVE:
			bonus.put(EnumRessource.ACIER, (int) (20*Math.random()+5));
			bonus.put(EnumRessource.CRISTAL, (int) (10*Math.random()+5));
			bonus.put(EnumRessource.GAZ, (int) (5*Math.random()+5));
			bonus.put(EnumRessource.CREDIT, (int) (30*Math.random()+30));
			break;
		case NEBULEUSE:
			bonus.put(EnumRessource.GAZ, (int) (5*Math.random()+5));
			break;
		case SOLEIL_SUPPLEMENTAIRE:
			bonus.put(EnumRessource.SCIENCE, 5);
			break;
		case TROU_NOIR:
			bonus.put(EnumRessource.SCIENCE, 25);
			break;
		default:
			break;
		}
	}
	
	public void giveBonus(Joueur joueur) {
		
		for (Entry<EnumRessource, Integer> e : this.bonus.entrySet()) {
			if(e.getKey()==EnumRessource.SCIENCE) {
				joueur.setScienceDepart(e.getValue()+joueur.getScienceDepart());
			}else {				
				joueur.getTRessource().put(e.getKey(), e.getValue()+joueur.getTRessource().get(e.getKey()));
			}
		}
		
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
	public Map<EnumRessource, Integer> getBonus() {
		return bonus;
	}
	public void setBonus(Map<EnumRessource, Integer> bonus) {
		this.bonus = bonus;
	}
}
