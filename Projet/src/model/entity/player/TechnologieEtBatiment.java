package model.entity.player;

import java.util.HashMap;
import java.util.Map;

import model.batiment.BatimentPlanete;
import model.batiment.BatimentVille;

public class TechnologieEtBatiment {

	private Map<Integer,Science> science;
	private Map<Integer, BatimentVille> batimentVille;
	private Map<Integer, BatimentPlanete> batimentPlanete;

	public TechnologieEtBatiment() {
		this.science=new HashMap<Integer,Science>();
		this.batimentVille=new HashMap<Integer, BatimentVille>();
		this.batimentPlanete=new HashMap<Integer, BatimentPlanete>();
	}
	
	public TechnologieEtBatiment(Map<Integer,Science> science,Map<Integer, BatimentVille> batimentVille, Map<Integer, BatimentPlanete> batimentPlanete) {
		this.science = science;
		this.batimentVille = batimentVille;
		this.batimentPlanete = batimentPlanete;
	}

	public Map<Integer, Science> getScience() {
		return science;
	}

	public void setScience(Map<Integer, Science> science) {
		this.science = science;
	}

	public Map<Integer, BatimentVille> getBatimentVille() {
		return batimentVille;
	}

	public void setBatimentVille(Map<Integer, BatimentVille> batimentVille) {
		this.batimentVille = batimentVille;
	}

	public Map<Integer, BatimentPlanete> getBatimentPlanete() {
		return batimentPlanete;
	}

	public void setBatimentPlanete(Map<Integer, BatimentPlanete> batimentPlanete) {
		this.batimentPlanete = batimentPlanete;
	}
	
}
