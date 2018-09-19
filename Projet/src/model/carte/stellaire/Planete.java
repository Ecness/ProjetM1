package model.carte.stellaire;

import java.util.Map;

import model.EnumRessource;
import model.batiment.EnumBatiment;

public class Planete {
	private EnumTypePlanete typePlanete;
	private Map<EnumRessource, Integer> TRessource;
	private EnumBatiment[] TBatiment;
	private Ville ville;
	
	public Planete(EnumTypePlanete typePlanete) {
		this.typePlanete = typePlanete;
		// TRessource = fonction generation ressource sur planete ;
		TBatiment = new EnumBatiment[2];
		this.ville = null;
	}
	
	
	
	public EnumTypePlanete getTypePlanete() {
		return typePlanete;
	}
	public void setTypePlanete(EnumTypePlanete typePlanete) {
		this.typePlanete = typePlanete;
	}
	public Map<EnumRessource, Integer> getTRessource() {
		return TRessource;
	}
	public void setTRessource(Map<EnumRessource, Integer> tRessource) {
		TRessource = tRessource;
	}
	public EnumBatiment[] getTBatiment() {
		return TBatiment;
	}
	public void setTBatiment(EnumBatiment[] tBatiment) {
		TBatiment = tBatiment;
	}
	public Ville getVille() {
		return ville;
	}
	public void setVille(Ville ville) {
		this.ville = ville;
	}
	
	
}
